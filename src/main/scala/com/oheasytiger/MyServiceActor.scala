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
import spray.http._
import HttpMethods._
import MediaTypes._
import spray.can.Http.RegisterChunkHandler

import akka.actor._
import spray.http._
import HttpMethods._
import akka.pattern.ask
// import akka.util.Timeout

import scala.concurrent.Future
import scala.concurrent.Await

import spray.routing._
import spray.http._
import MediaTypes._

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
class MyServiceActor extends Actor with HttpService {
  import Json4sProtocol._
  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
  import context.dispatcher
  implicit val timeout = Timeout(5 seconds)

  val order = path("pong") & parameters('size.as[Int]) & entity(as[person])
  val ordx = path("pong") & parameters('size.as[Int])

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
    }
}

