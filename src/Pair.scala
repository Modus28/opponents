/**
  * EECS 293
  * Created by Daniel on 10/16/2016.
  * dbg28@case.edu
  *
  * Pair: Contains two SetWrappers that are in diferent groups
  */
class Pair[N] (primarySet: SetWrapper[N], secondarySet: SetWrapper[N]) {

  // Methods
  //Algorithm 7: getOpponentSet: Get the SetWrapper that is the opponent of the argument
  def getOpponentSet(x: SetWrapper[N]): SetWrapper[N] = {
     if (x equals primarySet) {
       secondarySet
     }
     if (x equals secondarySet) {
        primarySet
     }
     else {
       throw new IllegalArgumentException
     }
  }
}