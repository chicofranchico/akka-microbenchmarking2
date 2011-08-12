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
 */
package akka_microbench.local

import akka.actor.Actor
import akka.actor.Actor._
import akka.actor.ActorRef
import akka.actor.PoisonPill
import akka.dispatch._

import scala.util.Random

import java.util.Date

import akka.actor.ReceiveTimeout

object LocalRandomPing extends App {

  startPinging(workers = 8, messages = 10000, hops = 100, repetitions = 5)

  sealed trait PingMessage
  case class Start extends PingMessage
  case class Ping(hops: Int) extends PingMessage
  case class End extends PingMessage

  var workers: Vector[ActorRef] = _

  var runs: List[Long] = List()

  /**
   * Receives ping messages and sends out another ping, decreasing the hop counter at receive.
   */
  class Worker(coordRef: ActorRef, numWorkers: Int) extends Actor {

    self.dispatcher = Dispatchers.newThreadBasedDispatcher(self)

    def receive = {

      case Ping(hops) =>
        if (hops == 0)
          coordRef ! End
        else
          workers(Random.nextInt(numWorkers)) ! Ping(hops - 1)

      case End =>
        self.stop()
    }

  }

  /**
   * Coordinates initial ping messages and receive messages from workers when they are finished for time calculation
   */
  class Master(numWorkers: Int, numMessages: Int, numHops: Int, repetitions: Int) extends Actor {

    self.dispatcher = Dispatchers.newThreadBasedDispatcher(self)

    var start: Long = 0
    var end: Long = 0
    var receivedEnds: Int = 0
    var reps: Int = 1

    def receive = {

      case Start =>

        workers = Vector()
        receivedEnds = 0
        // create the workers
        workers = Vector.fill(numWorkers)(actorOf(new Worker(self, numWorkers)).start())

        println("Master start run #" + reps)

        start = System.nanoTime

        // send to all of the workers some messages
        for (i <- 0 until numWorkers)
          for (j <- 0 until numMessages)
            workers(Random.nextInt(numWorkers)) ! Ping(numHops)

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
      println("Start pinging around: " + new Date(System.currentTimeMillis))
    }

    override def postStop {
      println("End: " + new Date(System.currentTimeMillis))
      val avg = runs.foldLeft(0l)(_ + _) / runs.size
      println("Average execution time = " + avg / 1000000.0 + " ms")
      System.exit(0)
    }

  }

  def startPinging(workers: Int, messages: Int, hops: Int, repetitions: Int) {

    println("Workers: " + workers)
    println("Messages: " + messages)
    println("Hops: " + hops)
    println("Repetitions: " + repetitions)

    // create the master
    val coordRef = actorOf(new Master(workers, messages, hops, repetitions)).start()

    // start the calculation
    coordRef ! Start

  }

}