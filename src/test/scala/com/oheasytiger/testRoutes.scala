package com.oheasytiger


import org.scalatest._
import spray.testkit.ScalatestRouteTest
import spray.http._
import StatusCodes._


class dRoutesTesting extends FreeSpec with ScalatestRouteTest with dRoutes {
  
  def actorRefFactory = system

  "dRoutes" - {
  	"return a not found for an odd number" in {
  		println("foo")
		Get("/even/23") ~> sealRoute(myRoute) ~> check {
	  		status === StatusCodes.NotFound
		}
  	}
  }
}