package com.oheasytiger
import scala.annotation.tailrec

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

/** embbeds factorial in an actor */
class FactorialCalculator extends Actor {
  def receive = {
    case num: Int => sender ! (num, fkx.factor(num))
  }
}

/** computes factorials of given numbers */
object fkx {

  def factor(num: Int) = factorTail(num, 1)

  @tailrec private def factorTail(num: Int, acc: BigInt): BigInt = {
    (num, acc) match {
      case (0, a) => a
      case (n, a) => factorTail(n - 1, n * a)
    }
  }
}