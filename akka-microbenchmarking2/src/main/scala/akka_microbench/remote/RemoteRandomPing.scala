/**
 * @author Francisco de Freitas
 *
 * This is a microbenchmark to test Akka
 *
 * The algorithm sends 'ping' messages to all the workers (akka-actors).
 * These then randomly choose a worker to send another 'ping' message to.
 * At each worker, the message hop is decreased until it reaches zero.
 * Each benchmark run ends when all initial messages have reached their maximum number of hops.
 *
 * USAGE: (akka.conf used provided in root folder)
 *
 * Define IPs, start machine 1 first then start machine 0. Machine 1 is the slave, machine 0 is the master
 *
 * Machine 0 is where the master worker stays and is the one collecting results.
 *
 * Both machines have 4 workers each, summing a total of 8 workers
 *
 */
package akka_microbench.remote

import akka.actor.Actor
import Actor._
import akka.actor.ActorRef
import akka.actor.PoisonPill
import akka.actor.ReceiveTimeout
import akka.dispatch._

import java.util.Date
import java.net.InetAddress

import scala.util.Random

sealed trait PingMessage
case object Start extends PingMessage
case class PingMsg(hops: Int) extends PingMessage
case object End extends PingMessage

trait IpDefinition {
  
  def machine0 = "130.60.157.52"

  def machine1 = "130.60.157.139"
}

/**
 * Receives ping messages and sends out another ping, decreasing the hop counter at receive.
 */
class Worker(coordRef: ActorRef, numWorkers: Int) extends Actor with IpDefinition {

  self.dispatcher = Dispatchers.newThreadBasedDispatcher(self)

  var workers: Array[ActorRef] = new Array[ActorRef](numWorkers)

  override def preStart {

    for (i <- 0 until 4)
      workers(i) = remote.actorFor("worker-service" + i, machine0, 2552)

    for (i <- 4 until 8)
      workers(i) = remote.actorFor("worker-service" + i, machine1, 2552)

  }

  def receive = {

    case PingMsg(hops) =>
      if (hops == 0)
        coordRef ! End
      else
        workers(Random.nextInt(numWorkers)) ! PingMsg(hops - 1)

    case End =>
      self.stop()
  }

}

/**
 * Coordinates initial ping messages and receive messages from workers when they are finished for time calculation
 */
class Master(numWorkers: Int, numMessages: Int, numHops: Int, repetitions: Int) extends Actor with IpDefinition {

  self.dispatcher = Dispatchers.newThreadBasedDispatcher(self)

  var start: Long = 0
  var end: Long = 0
  var receivedEnds: Int = 0
  var reps: Int = 1

  var runs: List[Long] = List()
  var workers: Array[ActorRef] = new Array[ActorRef](numWorkers)

  def receive = {

    case Start =>

      receivedEnds = 0

      // create the workers
      for (i <- 0 until 4)
        workers(i) = remote.actorFor("worker-service" + i, machine0, 2552)

      for (i <- 4 until 8)
        workers(i) = remote.actorFor("worker-service" + i, machine1, 2552)

      println("Master start run #" + reps)

      start = System.nanoTime

      // send to all of the workers 'numMessages' messages
      for (i <- 0 until numWorkers)
        for (j <- 0 until numMessages)
          workers(i) ! PingMsg(numHops)

    case End =>
      receivedEnds += 1

      // all messages have reached 0 hops
      if (receivedEnds == numWorkers * numMessages) {
        end = System.nanoTime

        println("Run #" + reps + " ended! Time = " + ((end - start) / 1000000.0) + "ms")

        runs = (end - start) :: runs

        if (reps != repetitions) {
          reps += 1
          self ! Start
        } else {
          println("Repetitions reached. Broadcasting shutdown...")
          workers.foreach { x => x ! PoisonPill }
          self.stop()
        }
      }

  }

  override def preStart {
    println("Start pinging around @ " + new Date(System.currentTimeMillis))
  }

  override def postStop {
    println("End: " + new Date(System.currentTimeMillis))
    val avg = runs.foldLeft(0l)(_ + _) / runs.size
    println("Average execution time = " + avg / 1000000.0 + " ms")
    System.exit(0)
  }

}
/**
 * Start this after you started Machine 1
 */
object RemoteRandomPingMachine0 extends IpDefinition {

  def main(args: Array[String]): Unit = {

    val numWorkersTotal = 8

    remote.start(InetAddress.getLocalHost.getHostAddress, 2552)

    val coord = remote.actorFor("coord-service", machine0, 2552)

    remote.register("worker-service0", actorOf(new Worker(coord, numWorkersTotal)))
    remote.register("worker-service1", actorOf(new Worker(coord, numWorkersTotal)))
    remote.register("worker-service2", actorOf(new Worker(coord, numWorkersTotal)))
    remote.register("worker-service3", actorOf(new Worker(coord, numWorkersTotal)))

    val workers = 8
    val messages = 10000
    val hops = 100
    val repetitions = 5

    println("Workers: " + workers)
    println("Messages: " + messages)
    println("Hops: " + hops)
    println("Repetitions: " + repetitions)

    // create the master
    remote.register("coord-service", actorOf(new Master(workers, messages, hops, repetitions)))

    val coordRef = remote.actorFor("coord-service", machine0, 2552)

    // start the calculation
    coordRef ! Start

  }

}
/**
 * Start this first and then start Machine 0
 */
object RemoteRandomPingMachine1 extends IpDefinition {

  def main(args: Array[String]): Unit = {

    val numWorkersTotal = 8

    remote.start(InetAddress.getLocalHost.getHostAddress, 2552)

    val coord = remote.actorFor("coord-service", machine0, 2552)

    remote.register("worker-service4", actorOf(new Worker(coord, numWorkersTotal)))
    remote.register("worker-service5", actorOf(new Worker(coord, numWorkersTotal)))
    remote.register("worker-service6", actorOf(new Worker(coord, numWorkersTotal)))
    remote.register("worker-service7", actorOf(new Worker(coord, numWorkersTotal)))

  }

}