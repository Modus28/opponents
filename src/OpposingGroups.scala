import scala.collection.mutable
import scala.util.{Failure, Success, Try}

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

  val opposingGroups: mutable.HashSet[Pair[N]] = new mutable.HashSet[Pair[N]]()

  // Methods

  /** Create: Add x to the database inside a new Group - O(1)
    *
    * @param x the object to add to the known objects list
    * @return the ObjectWrapper containing the input, x
    */
  def create(x: N): ObjectWrapper[N] = {
    val wrapper: ObjectWrapper[N] = new ObjectWrapper[N](x)
    val set: SetWrapper[N] = new SetWrapper[N](Set(wrapper))
    val newPair: Pair[N] = new Pair(set, new SetWrapper[N](null))
    addPair(newPair)
    wrapper
  }

  /** Add Pair to Database - O(1)
    *
    * @param toAdd the pair to add to the database
    */
  def addPair(toAdd: Pair[N]): Unit = opposingGroups += toAdd

  /** Remove Pairs from Database - O(1)
    *
    * @param toDel the pairs to remove from the database
    */
  def removePair(toDel: Set[Pair[N]]): Unit = opposingGroups --= toDel


  /*
   A Method to allow direct N-type input to oppose and opponent could be added, but they would
   have a far worse time complexity.
   It makes more sense to require that queries are only made on the N-types that have been wrapped,
   as there is zero loss in functionality.
   */

  /** Oppose: Updates the database with a new Opposition - O(1)
    *
    * @param x the object that opposes y
    * @param y the object that opposes x
    */
  def oppose(x: ObjectWrapper[N], y: ObjectWrapper[N]): Unit = {
     if (!(x.getPair equals y.getPair)){
       merge(x.getPair, x.getContainer, y.getPair, y.getContainer)
     }
     else{
       throw new IllegalArgumentException
     }
  }

  /** Combine Pairs - O(1)
    * Adds the values of one pair to another, and removes the original one.
    *
    * @param xp The pair that will envelop another pair
    * @param xs The set in xp that will have a set added to it
    * @param yp The pair to be merged into xp
    * @param ys The set in yp that will be added inside xs
    */
  def merge(xp: Pair[N], xs: SetWrapper[N], yp: Pair[N], ys: SetWrapper[N]): Unit = {
    xs.appendSet(ys)
    xp.getOpponentSet(xs).appendSet(ys.getOpponents)
    removePair(Set(yp))
  }

  /** Check if ObjectWrappers are Opponents - O(1)
    *
    * @param x
    * @param y
    * @return
    */
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
