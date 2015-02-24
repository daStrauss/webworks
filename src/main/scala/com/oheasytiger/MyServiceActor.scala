package com.oheasytiger

import spray.routing._
import spray.http._
import MediaTypes._

import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import akka.actor._
import spray.can.Http
import spray.can.server.Stats
import spray.util._
import HttpMethods._

// import akka.util.Timeout

import scala.concurrent.Future
import scala.concurrent.Await

import org.json4s._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{read, write}
import spray.httpx.Json4sSupport

case class person(name: String, age: Int)

object Json4sProtocol extends Json4sSupport {
  implicit def json4sFormats: Formats = DefaultFormats
}


// bring in the route structure so that we get access to the 
// thread context
class MyServiceActor extends Actor with dRoutes {
  import Json4sProtocol._
  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
  import context.dispatcher
  

  }

trait dRoutes extends HttpService {
  import Json4sProtocol._
  val order = path("pong") & parameters('size.as[Int]) & entity(as[person])
  val ordx = path("pong") & parameters('size.as[Int])
  implicit val timeout = Timeout(5 seconds)
  implicit def executionContext = actorRefFactory.dispatcher

  val myRoute =
    path("ping") {
      get {
        complete {
          val num = 10000
          val xx : Future[(Int,BigInt)] = ask(actorRefFactory.actorOf(Props(new FactorialCalculator)), num).mapTo[(Int,BigInt)]
          xx.map(y=>"%s %s".format(y._1,y._2))
        }
      }
    } ~ 
    order {
      (y, ppx) => 
      complete {
        "ah posted %s, %s".format(y, ppx)
      }
    } ~
    path("catch") {
      requestInstance { request => 
        complete {
          "request = %s".format(request.entity)
        }
      }
    } ~
    path("even" / IntNumber) { i =>
      complete {
        // returns Some(evenNumberDescription) or None
        Option(i).filter(_ % 2 == 0).map { num =>
          s"Number $num is even."
        }
      }
    } ~
    path(Rest) { y=>
      requestInstance { request => {
        complete("bad req = %s".format(request))
      }
    }
    } 
}

// Get("/even/23") ~> sealRoute(route) ~> check {
//   status === StatusCodes.NotFound
// }
// Get("/even/28") ~> route ~> check {
//   responseAs[String] === "Number 28 is even."
// }


// }

