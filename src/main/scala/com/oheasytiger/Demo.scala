package com.oheasytiger

import akka.actor._
import spray.http._
import HttpMethods._
import akka.pattern.ask
// import akka.util.Timeout
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Future
import scala.concurrent.Await

class DemoService extends Actor with ActorLogging {
  // implicit val timeout = Timeout(5 seconds)

  // val bkx = context.actorOf(Props[backSpin])
  // val spn = context.actorOf(Props[backSpin].withDispatcher("my-dispatcher"))

  def receive = {
    case HttpRequest(GET, Uri.Path("/ping"), _, _, _) => {

      // val q: Future[String] = ask(bkx, "ping").mapTo[String]
      // val r: Future[String] = ask(spn, "ping").mapTo[String]

      // val result : String = Await.result(q, timeout.duration)
      // val otr : String = Await.result(r, timeout.duration)

      sender ! HttpResponse(entity = "PONG!") // , %s, %s".format(result, "oo"))
    }
  }
}