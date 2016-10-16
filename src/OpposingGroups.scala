import scala.util.{Try, Success, Failure}

/** EECS 293: Opposing Groups
  * Daniel Grigsby
  * October 11, 2016
  * OpposingGroups is a data storage system that keeps track of what objects are the opponents
  * of other objects, and groups them to maximize our knowledge of that. Performs similarly to a
  * Set of HashSets, where the total number of HashSets is minimized by merging when possible
  *
  * Contains a set of Pairs of Opposing Objects, supports adding opposition information
  */
class OpposingGroups[N] {

  // Fields

  var opposingGroups: Set[Pair[N]] = ???

  // Methods

  /** Create: Add N to the database inside a new Group - O(1)
    *
    * @param x the object to add to the known objects list
    * @return
    */
  def create(x: N): ObjectWrapper[N] = {
    val wrapper: ObjectWrapper[N] = new ObjectWrapper[N](x)
    val set: SetWrapper[N] = new SetWrapper[N](Set(wrapper))
    val newPair: Pair[N] = new Pair(set, new SetWrapper())
    addPair(newPair)
    wrapper
  }

  // Algorithm 4 Add Pair to Database - O(1)
  def addPair(toAdd: Pair[N]): Unit = opposingGroups += toAdd

  //Algorithm 5 Remove Pairs from Database - O(1)
  def removePair(toDel: Set[Pair[N]]): Unit = opposingGroups --= toDel


  /*
   A Method to allow direct N-type input to oppose and opponent could be added, but they would
   have a far worse time complexity.
   It makes more sense to require that queries are only made on the N-types that have been wrapped,
   as there is zero loss in functionality.
   */

  // Algorithm 2 Oppose: Updates the database with a new Opposition - O(1)
  def oppose(x: ObjectWrapper[N], y: ObjectWrapper[N]): Unit = {
     if (!(x.getPair equals y.getPair)){
       merge(x.getPair, x.getContainer, y.getPair, y.getContainer)
     }
     else{
       throw new IllegalArgumentException
     }
  }

  //Algorithm 3: Merge: Combine Pairs - O(1)
  def merge(xp: Pair[N], xs: SetWrapper[N], yp: Pair[N], ys: SetWrapper[N]): Unit = {
    xs.appendSet(ys)
    xp.getOpponentSet(xs).appendSet(ys.getOpponents)
    removePair(Set(yp))
  }

  // Algorithm 6: Check if ObjectWrappers are Opponents - O(1)
  def opponents(x: ObjectWrapper[N], y: ObjectWrapper[N]): Try[Boolean] = {
    if (x.getPair equals y.getPair) {
      if (x.getContainer equals y.getContainer) {
        Success(true)
      }
      else {
        Success(false)
      }
    }
    else {
      Failure(new NoSuchFieldException)
    }
  }


  def main(args: Array[String]): Unit = {
    println("We are compiling")
  }
}
