/**
 * @author Francisco de Freitas
 *
 * This is a microbenchmark to test Akka throughput.
 *
 * Bandwidth limits: (Akka was able to sustain message sending in a 100Mbit network)
 *
 * Two machines and one thread sends messages to the actors on the other side.
 *
 * One actor at each side:
 * - Message throughput per second: 24.4 K messages / second / actor (peak) with ~ 22-23 K sustained
 *
 * Two actors at each side:
 * - Sequential message sending: 11.5 K messages / second / actor  (peak) with ~ 10-11 K sustained
 * - Parallel message sending: 10.4 K messages / second / actor  (peak) with ~ 9-10 K sustained
 *
 * Four actors at each side:
 * - Sequential message sending: 5.5 K messages / second / actor  (peak) with ~ 5-6 sustained
 * - Parallel message sending: 7.4 K messages / second / actor  (peak) with ~ 6-7 K sustained
 *
 * Eight actors at each side:
 * - Sequential message sending: 2.6 K messages / second / actor (peak) with > 2 K sustained
 * - Parallel message sending: 4.4 K messages / second / actor  (peak) with ~ 3-4 K sustained
 *
 *
 *
 * USAGE: (akka.conf used provided in root folder)
 *
 * Define IPs, start machine 1 first then start machine 0.
 *
 * machine1$: java -jar test.jar 1
 *
 * machine0$: java -jar test.jar 0
 *
 */
package akka_microbench.remote

import akka.actor.Actor
import Actor._
import akka.actor.ActorRef
import akka.dispatch._

import java.util.Date
import java.net.InetAddress

import scala.util.Random

/**
 * Receives ping messages and sends out another ping, decreasing the hop counter at receive.
 */
class LightWorker(id: Int) extends Actor with IpDefinition {

  self.dispatcher = Dispatchers.newThreadBasedDispatcher(self)

  var received = 0

  var lastTime = 0l

  override def preStart {

    lastTime = System.nanoTime

  }

  def receive = {

    case "hello" =>

      var now = System.nanoTime

      received = received + 1

      if (now - lastTime > 1000000000) {
        println("After one second, received = " + received)
        received = 0
        lastTime = System.nanoTime
      }

  }

}

/**
 * Start this using arguments 1 first in one machine
 * Then, start this using arguments 0 in another machine
 *
 * e.g.:
 *
 * machine1$: java -jar test.jar 1 01 a -1
 *
 * machine0$: java -jar test.jar 0 01 a -1
 *
 */
object RemoteThroughputTest extends IpDefinition {

  def main(args: Array[String]): Unit = {

    remote.start(InetAddress.getLocalHost.getHostAddress, 2552)

    val array: Array[ActorRef] = new Array[ActorRef](8)

    /** MACHINE 0 */
    if (args(0) equals "0") {

      for (i <- 0 until 8)
        remote.register("worker-service" + i, actorOf(new LightWorker(i)))

      /*      for (i <- 0 until numWorkersPerMachine)
        remote.register("worker-service" + i, actorOf(new ReplyWorker(i , coord, numWorkersTotal, sharedDispatcher )))

      for (i <- 0 until numWorkersPerMachine)
        array(i) = remote.actorFor("worker-service" + (i + numWorkersPerMachine), machine1, 2552)*/

      array(0) = remote.actorFor("worker-service8", machine1, 2552)
      array(1) = remote.actorFor("worker-service9", machine1, 2552)
      array(2) = remote.actorFor("worker-service10", machine1, 2552)
      array(3) = remote.actorFor("worker-service11", machine1, 2552)
      array(4) = remote.actorFor("worker-service12", machine1, 2552)
      array(5) = remote.actorFor("worker-service13", machine1, 2552)
      array(6) = remote.actorFor("worker-service14", machine1, 2552)
      array(7) = remote.actorFor("worker-service15", machine1, 2552)

    } else {

      /** MACHINE 1 */

      for (i <- 8 until 16)
        remote.register("worker-service" + i, actorOf(new LightWorker(i)))

      array(0) = remote.actorFor("worker-service0", machine0, 2552)
      array(1) = remote.actorFor("worker-service1", machine0, 2552)
      array(2) = remote.actorFor("worker-service2", machine0, 2552)
      array(3) = remote.actorFor("worker-service3", machine0, 2552)
      array(4) = remote.actorFor("worker-service4", machine0, 2552)
      array(5) = remote.actorFor("worker-service5", machine0, 2552)
      array(6) = remote.actorFor("worker-service6", machine0, 2552)
      array(7) = remote.actorFor("worker-service7", machine0, 2552)

      Thread.sleep(3000)

    }

    val parArray = array.par

    for (i <- 0 to 100000000)
      array foreach (_ ! "hello")

  }

}