package Levski
import scala.collection.mutable.HashMap


object DynamicProgramming {
  // Dynamic programming is a technique used to avoid computing multiple time the same subproblem in a recursive algorithm.


  // The heavy recursion approach
  def fibonacci(n: Int): Int = {
    n match {
      case 0 => 0;
      case 1 => 1;
      case _ => fibonacci(n - 1) + fibonacci(n - 2);
    }
  }

  // A Bottom-up approach, using caching to eliminate the uncessary calculation
  def fibonacciBU(n: Int): Int = {
    val cache = new HashMap[Int, Int]()

    cache(0) = 0;
    cache(1) = 1;

    for (current <- 2 to n) {
      cache(current) = cache(current-1) + cache(current-2)
    }

    cache(n)
  }

  //Top Down - Memoization
  val cacheTD = new HashMap[Int, Int]()
  cacheTD(0) = 0;
  cacheTD(1) = 1;

  def fibonacciTD(n: Int): Int = {

    n match {
      case 0 => cacheTD(0);
      case 1 => cacheTD(1);
      case w if cacheTD.contains(w) => cacheTD(w);
      case g => cacheTD(g) = fibonacciTD(g - 1) + fibonacciTD(g - 2);
    }

    cacheTD(n)
  }

}
