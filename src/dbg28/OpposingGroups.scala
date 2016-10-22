package dbg28

import scala.collection.mutable

/** EECS 293: Opposing Groups
  * Daniel Grigsby
  * October 11, 2016
  *
  * OpposingGroups is a data storage system that keeps track of what objects are the opponents
  * of other objects, and groups them to maximize our knowledge of that. Performs similarly to a
  * Set of HashSets, where the total number of HashSets is minimized by merging when possible
  *
  * Contains a set of Pairs of Opposing Objects, supports adding opposition information
  */
class OpposingGroups[N] {

  // Fields
  // The Database: A HashSet of Pairs
  private val opposingDatabase: mutable.HashSet[Pair[N]] = new mutable.HashSet[Pair[N]]()

  // Methods

  /** Create: Add x to the database inside a new Group - O(1)
    *
    *
    * @param x the object to add to the known objects list
    * @return the ObjectWrapper containing the input, x
    */
  def create(x: N): ObjectWrapper[N] = {
    val wrapper: ObjectWrapper[N] = new ObjectWrapper[N](x)
    val set: SetWrapper[N] = new SetWrapper[N](Set(wrapper))
    val emptySet: SetWrapper[N] = new SetWrapper[N]()
    val newPair: Pair[N] = new Pair(set, emptySet)
    set.setPair(newPair)
    emptySet.setPair(newPair)
    addPair(newPair)
    wrapper
  }

  /** Add Pair to Database - O(1)
    *
    * @param toAdd the pair to add to the database
    */
  private[dbg28] def addPair(toAdd: Pair[N]): Unit = opposingDatabase += toAdd

  /** Oppose: Updates the database with a new Opposition
    * O(n) worst case, O(1) average case
    *
    * @param x the object that opposes y
    * @param y the object that opposes x
    */
  def oppose(x: ObjectWrapper[N], y: ObjectWrapper[N]): Unit = {
    if (!(x.getPair equals y.getPair)) {
      merge(x.getPair, x.getContainer, y.getPair, y.getContainer)
    }
    else {
      throw new IllegalArgumentException("X and Y already have known opponents status")
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
  private[dbg28] def merge(xp: Pair[N], xs: SetWrapper[N], yp: Pair[N], ys: SetWrapper[N]): Unit = {
    xp.getOpponentSet(xs).appendSet(ys.getObjects)
    xs.appendSet(ys.getOpponents.getObjects)
    removePair(Set(yp))
  }

  /** Remove Pairs from Database - O(1)
    *
    * @param toDel the pairs to remove from the database
    */
  private[dbg28] def removePair(toDel: Set[Pair[N]]): Unit = opposingDatabase --= toDel

  /** Check if ObjectWrappers are Opponents - O(1)
    *
    * @param x the object that may be an opponent of y
    * @param y the object that may be an opponent of x
    * @return Success containing the truth value of the check, or Failure if impossible to do so
    */
  def opponents(x: ObjectWrapper[N], y: ObjectWrapper[N]): Option[Boolean] = {
    if (x.getPair equals y.getPair) {
      Option(!(x.getContainer equals y.getContainer))
    }
    else {
      None
    }
  }

  /** Return the oppose HashSet
    *
    * @return the opposing group hashset for this object
    */
  def getOpposingDatabase: mutable.HashSet[Pair[N]] = this.opposingDatabase
}
