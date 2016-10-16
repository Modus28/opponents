/**
  * EECS 293
  * Created by Daniel on 10/16/2016.
  * dbg28@case.edu
  *
  * Pair: Contains two SetWrappers that are in diferent groups
  */
class Pair[N] {
  // Fields
  var primarySet: SetWrapper[N] = new SetWrapper[N]()
  // First SetWrapper
  var secondarySet: SetWrapper[N] = new SetWrapper[N]() // The direct opponent of primarySet

  // Methods
  //Algorithm 7 getOpponentSet: Get the SetWrapper that is the opponent of the argument
  def getOpponentSet(x: SetWrapper[N]): SetWrapper[N] = {
    /* 2: if x = primarySet then
       3: return secondarySet
     4: if x = secondarySet then
       5: return primarySet
     6: else
     7: throw Exception*/
    new SetWrapper[N]()
  }


  // Constructors
  def this(primary: SetWrapper[N], secondary: SetWrapper[N]) = {
    this
    this.primarySet = primary
    this.secondarySet = secondary
  }
}