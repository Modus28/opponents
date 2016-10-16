/**
  * EECS 293: Opposing Groups
  * Daniel Grigsby
  * October 11, 2016
  * OpposingGroups is a data storage system that keeps track of what objects are the opponents
  * of other objects, and groups them to maximize our knowledge of that. Performs similarly to a
  * Set of HashSets, where the total number of HashSets is minimized by merging when possible
  *
  */

class OpposingGroups {

/*
    Contains a set of Pairs of Opposing Objects, supports adding opposition information
      N: Generic type parameter for the Object in question
      1.1 Fields
      opposingGroups: A Set of Pairs of Opposing Sets of N
    1.2 Methods
      Algorithm 1 Create: Add N to the database inside a new Group - O(1)
    1: procedure create(x : N)
    2: wrapper : ObjectW rapper   newObjectW rapper(x)
    3: set : SetWrapper   new SetWrapper(new Set(wrapper))
    4: newPair : Pair   Pair(set; fg)
    5: addPair(pair)
    Algorithm 2 Oppose: Updates the database with a new Opposition - O(1)
    1: procedure oppose(x : ObjectW rapper; y : ObjectW rapper)
    2: if x; y are not in the same Pair then
      3: merge(x:getP air(); x:getContainer(); y:getP air(); y:getContainer())
    4: else
    5: Report error . Opposition already known
    1.3 Wrapper Methods
    A Method to allow direct N-type input to oppose and opponent could be added, but they would
    have a far worse time complexity.
    It makes more sense to require that queries are only made on the N-types that have been wrapped,
    as there is zero loss in functionality.
    1
    Algorithm 3 Merge: Combine Pairs - O(1)
    1: procedure merge(xp : Pair; xs : SetWrapper; yp : Pair; ys : SetWrapper)
    2: xs:appendSet(ys)
    3: xp:getOpponentSet(xs).appendSet(ys:getOpponents())
    4: removePair(Set(yp))
    Algorithm 4 Add Pair to Database - O(1)
    1: procedure addPair(toAdd : Pair)
    2: globalGroups   globalGroups + toAdd
      Algorithm 5 Remove Pairs from Database - O(1)
    1: procedure removePair(toDel : Set[Pair]])
    2: globalPairs   globalGroups ? toDel
    Algorithm 6 Check if ObjectWrappers are Opponents - O(1)
    1: function opponents(x : ObjectW rapper; y : ObjectW rapper])
    2: if x:getP air() = y:getP air() then
    3: if x:getContainer() = y:getContainer() then
    4: report false
    5: else
    6: report true
    7: else
    8: report unknown . Can be any status variable
    2
    2 class Pair
    Contains two SetWrappers that are in dierent groups
      2.1 Fields
      primarySet: First SetWrapper
      secondarySet: Second SetWrapper that is the direct opponent of primarySet
    2.2 Methods
      Algorithm 7 getOpponentSet: Get the SetWrapper that is the opponent of the argument
    1: function getOpponentSet(x : SetWrapper)
    2: if x = primarySet then
      3: return secondarySet
    4: if x = secondarySet then
      5: return primarySet
    6: else
    7: throw Exception
    2.3 Auxiliary Constructors
    One Constructor that accepts two SetWrappers will be enabled
    - The constructor will set the SetWrappers pair eld to this
    2.4 Getters/Setters
      Getter method for primarySet
    Getter method for secondarySet
    3 class SetWrapper
    Contains a Set of ObjectWrappers that are not opponents of each other
      3.1 Fields
      objects: Set of ObjectWrappers
    pair: Pair that contains this SetWrapper
    3.2 Methods
      Algorithm 8 Get the SetWrapper that opposes this SetWrapper
    1: function getOpponents
    2: return getPair.getOpposite(this)
    3
    Algorithm 9 Append a set to this SetWrapper's contained set
    1: function appendSet(toAppend: SetWrapper)
    2: set the ObjectWrappers' container eld to this object
    3: this:objects   this:objects + toAppend:getObjects
    3.3 Getters/Setters
      Getter method for pair
    Getter method for objects
    3.4 Auxiliary Constructors
    There will be one constructor that accepts a set of ObjectWrappers
    - It will set each ObjectWrapper's container eld to this
    3.5 Denitions
    Set operations, such as contains, will be delegated to 'objects'
    4 class ObjectWrapper
    Adds functionality to the generic type N
    4.1 Fields
      value: the generic object that this ObjectWrapper contains
      container: The SetWrapper that holds this ObjectWrapper
    4.2 Methods
      Algorithm 10 Delegate hashcode to the contained value
      1: function hashCode
    2: return this:getV alue():hashCode()
    4.3 Getters/Setters
      Getter method for value
    Getter Method for container
    Setter Method for container
    Getter Method for pair that returns getContainer().getPair()
    4*/

    def main(args: Array[String]): Unit = {
        println("We are compiling")
    }
}
