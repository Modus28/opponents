import scala.util.{Try,Success,Failure}

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

      var opposingGroups: Set[Pair[N]] = new Set[Pair[N]]()

    // Methods

    /** Create: Add N to the database inside a new Group - O(1)

      * @param x the object to add to the known objects list
      * @return
      */
    def create(x: N): ObjectWrapper[N] = {
        val wrapper : ObjectWrapper[N] = new ObjectWrapper[N](x)
        val set: SetWrapper[N] = new SetWrapper[N](new Set[ObjectWrapper[N]](wrapper))
        val newPair: Pair[N] = new Pair(set, new SetWrapper())
        addPair(newPair)
    }

    // Algorithm 2 Oppose: Updates the database with a new Opposition - O(1)
    def oppose(x : ObjectWrapper[N], y : ObjectWrapper[N]): Boolean = {
      /*  2: if x; y are not in the same Pair then
          3: merge(x:getP air(); x:getContainer(); y:getP air(); y:getContainer())
        4: else
        5: Report error . Opposition already known*/
    }

   /*
    A Method to allow direct N-type input to oppose and opponent could be added, but they would
    have a far worse time complexity.
    It makes more sense to require that queries are only made on the N-types that have been wrapped,
    as there is zero loss in functionality.
    */


    //Algorithm 3 Merge: Combine Pairs - O(1)
    def merge(xp : Pair[N], xs : SetWrapper[N], yp : Pair[N], ys : SetWrapper[N]): Unit = {
        /*
            2: xs:appendSet(ys)
    3: xp:getOpponentSet(xs).appendSet(ys:getOpponents())
    4: removePair(Set(yp))
         */
    }


   // Algorithm 4 Add Pair to Database - O(1)
    def addPair(toAdd : Pair[N]): Unit = {
        /*
        2: globalGroups   globalGroups + toAdd
      Algorithm 5 Remove Pairs from Database - O(1)
    1: procedure removePair(toDel : Set[Pair]])
    2: globalPairs   globalGroups ? toDel
         */
    }

    // Algorithm 6 Check if ObjectWrappers are Opponents - O(1)
    def opponents(x : ObjectWrapper[N], y : ObjectWrapper[N]): Try[Boolean] = {
       /* 2: if x: getP air () = y: getP air () then
        3: if x: getContainer () = y: getContainer () then
          4: report false
        5: else
        6: report true
        7: else
        8: report unknown.Can be any status variable*/

    }


    def main(args: Array[String]): Unit = {
        println("We are compiling")
    }
}
