package com.oheasytiger

import akka.actor._

class processing extends Actor with ActorLogging {

  
  def receive = {
    case "ping" => {
    	println("gpx")
    	Thread sleep 1000
    	println("got a ping!")
    	sender ! "a"
    }
    case _ => {
    	println("errx")
    }

    }
}