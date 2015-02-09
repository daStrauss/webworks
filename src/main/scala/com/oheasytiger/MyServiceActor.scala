package com.oheasytiger

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

import spray.http._
import HttpMethods._

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
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Future
import scala.concurrent.Await


import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with HttpService{

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
  import context.dispatcher
  implicit val timeout = Timeout(5 seconds)

  val myRoute =
    path("ping") {
      get {
        complete {

          val num = 10000
          val xx : Future[(Int,BigInt)] = ask(actorRefFactory.actorOf(Props(new FactorialCalculator)), num).mapTo[(Int,BigInt)]
          xx.map(y=>"%s %s".format(y._1,y._2))
        }
      }
    }

  def cpux(num: Int) : Future[String] = Future { fkx.factor(num).toString }


}


// // this trait defines our service behavior independently from the service actor
// trait MyService extends HttpService {
//   implicit val timeout = Timeout(5 seconds)

// }