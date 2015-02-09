package com.oheasytiger
import scala.annotation.tailrec

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

// object Factorial extends App {
//   val factorials = List(20, 18, 32, 28, 22, 42, 55, 48).map(y=>y*10000)

//   val system = ActorSystem("factorial")

//   val collector = system.actorOf(Props(new FactorialCollector(factorials)), "collector")
// }

// class FactorialCollector(factorials: List[Int]) extends Actor with ActorLogging {
//   var list: List[BigInt] = Nil
//   var size = factorials.size

//   for (num <- factorials) {
//     context.actorOf(Props(new FactorialCalculator)) ! num
//   }

//   def receive = {
//     case (num: Int, fac: BigInt) => {
//       // log.info(s"factorial for $num is $fac")

//       list = num :: list
//       size -= 1

//       if (size == 0) {
//         context.system.shutdown()
//       }
//     }
//   }
// }

class FactorialCalculator extends Actor {
  def receive = {
    case num: Int => sender ! (num, fkx.factor(num))
  }

}

object fkx {
  def factor(num: Int) = factorTail(num, 1)

  @tailrec private def factorTail(num: Int, acc: BigInt): BigInt = {
    (num, acc) match {
      case (0, a) => a
      case (n, a) => factorTail(n - 1, n * a)
    }
  }
}