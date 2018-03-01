package Levski;


object GenerateAllCoins {

  def process(List[List[Int]] result, List[Int] coins, int target, List[Int] accu) {

    if (accu.sum < target) {
      process(result, coins, target, accu :: coins.head)
      process(result, coins.tail, target, accu)
    } else if (accu.sum == target){
      result :: accu;
    }

  }

  def generate(Int target): List[List[Int]] = {
    var result = new List[List[Int]]()
    List[Int]()
    // define the available coins here
    val coins = List[Int](1, 2, 5, 10)
    var accu = new List[Int]();
    process(result, coins, target, accu);
    result;
  }

}
