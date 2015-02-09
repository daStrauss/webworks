
package com.oheasytiger

import akka.actor.{ActorSystem, Props}
import spray.servlet.WebBoot

// This class is instantiated by the servlet initializer.
// It can either define a constructor with a single
// `javax.servlet.ServletContext` parameter or a
// default constructor.
// It must mplement the spray.servlet.WebBoot trait.
class BootFoo extends WebBoot {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem()

  // the service actor replies to incoming HttpRequests
  val serviceActor = system.actorOf(Props[MyServiceActor])

  system.registerOnTermination {
    // put additional cleanup code here
    system.log.info("Application shut down")
  }
}

