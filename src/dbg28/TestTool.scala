package dbg28

/**
  * EECS 293
  * Created by Daniel on 10/20/2016.
  * dbg28@case.edu
  *
  * The purpose of this class is to provide other tester classes with
  * easy access to fresh instances of default data, random data, and other testing data
  */
class TestTool {

  // Global fields

  var database: OpposingGroups[Ninja] = _
  var ninjaSet: Set[Ninja] = _
  var objectWrapperSet: Set[ObjectWrapper[Ninja]] = _
  var sWrapOne, sWrapTwo, sWrapThree, sWrapFour: SetWrapper[Ninja] = new SetWrapper[Ninja](null)
  var pairFirst, pairSecond: Pair[Ninja] = _
  var testHook: OpposingGroups[Ninja]#TestHook = _

  /**
    *  Setup: Initialize a database with two Pairs of single Ninjas
    */
  def createDatabaseWithNinjas(): TestTool = {
    // Start new Database. Double variable declaration is necessary for stable outer-inner class identifier
    val data = new OpposingGroups[Ninja]()
    val tester = new data.TestHook()
    database = data
    testHook = tester
    // Call other initialization methods
    initializeSets()
    initializePairs()
    this
  }

  // Initialize a Set of 10 Ninjas and their set of ObjectWrappers
  def initializeSets(): Unit = {
    ninjaSet = (1 to 10).map(_ => new Ninja()).toSet
    objectWrapperSet = ninjaSet.map(new ObjectWrapper[Ninja](_))
  }

  // Initialize two Pairs
  def initializePairs(): Unit = {
    pairFirst = new Pair[Ninja](sWrapOne, sWrapTwo)
    List(sWrapOne, sWrapTwo).foreach(_.setPair(pairFirst))
    pairSecond = new Pair[Ninja](sWrapThree, sWrapFour)
    List(sWrapThree, sWrapFour).foreach(_.setPair(pairSecond))
    database.getOpposingDatabase += (pairFirst, pairSecond)
  }
}
