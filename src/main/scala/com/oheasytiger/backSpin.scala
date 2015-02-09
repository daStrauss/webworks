package com.oheasytiger

import akka.actor._

class backSpin extends Actor with ActorLogging {

  
  def receive = {
    case "ping" => {
    	println("bkbk")
    	Thread sleep 1000
    	println("gotbkbk")
    	sender ! "back spin"
    }
    case _ => {
    	println("errx")
    }

    }
}