/**
 * @author Francisco de Freitas
 *
 * This is a microbenchmark to test Akka
 *
 * USAGE: (akka.conf used provided in root folder)
 *
 * Define IPs, start machine 1 first then start machine 0. Machine 1 is the slave, machine 0 is the master
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
import akka.actor.PoisonPill
import akka.actor.ReceiveTimeout
import akka.dispatch._
import akka.dispatch.MailboxType

import java.util.Date
import java.net.InetAddress

import scala.util.Random

/**
 * Receives ping messages and sends out another ping, decreasing the hop counter at receive.
 */
class ReplyWorker(id: Int) extends Actor with IpDefinition {

  //self.dispatcher = dispatcher

  self.dispatcher = Dispatchers.newThreadBasedDispatcher(self)

  def receive = {

    case "Hello" => self.reply("World")

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
object RemoteRTTTest extends IpDefinition {

  def main(args: Array[String]): Unit = {

    /** MACHINE 0 */
    if (args(0) equals "0") {

      remote.start(InetAddress.getLocalHost.getHostAddress, 2552)

      remote.register("worker-service0", actorOf(new ReplyWorker(0 /*, coord, numWorkersTotal, sharedDispatcher )*/ )))


    } else {

      /** MACHINE 1 */

      val remoteActor = remote.actorFor("worker-service0", machine0, 2552)

      for (i <- 0 to 100000000) {
        val sendTime = System.nanoTime
        val result = remoteActor !! "Hello"
        result match {
          case Some(reply) =>
            val responseTime = System.nanoTime
            val rtt = (responseTime - sendTime) / 1000000.0
            println("RTT = " + rtt  + " ms")

          case None => sys.error("timeout waiting for reply")
        }
      }

    }

  }

}