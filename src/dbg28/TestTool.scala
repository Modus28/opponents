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
  var n1: Ninja = _
  var n2: Ninja = _
  var ninjaSet: Set[Ninja] = _
  var n1Wrap: ObjectWrapper[Ninja] = _
  var n2Wrap: ObjectWrapper[Ninja] = _
  var objectWrapperSet: Set[ObjectWrapper[Ninja]] = _
  var setWrapperFirst: SetWrapper[Ninja] = _
  var setWrapperSecond: SetWrapper[Ninja] = _
  var setWrapperThird: SetWrapper[Ninja] = _
  var setWrapperFourth: SetWrapper[Ninja] = _
  var pairFirst: Pair[Ninja] = _
  var pairSecond: Pair[Ninja] = _

  /**
    *  Setup: Initialize a database with two Pairs of single Ninjas
    */
  def createDatabaseWithNinjas(): TestTool = {
    // Start new Database
    database = new OpposingGroups[Ninja]()
    initializeSets()
    initializeSetWrappers()
    initializePairs()
    this
  }

  // Initialize a Set of 10 Ninjas and their set of ObjectWrappers
  def initializeSets(): Unit = {
    ninjaSet = (1 to 10).map(_ => new Ninja()).toSet
    objectWrapperSet = ninjaSet.map(database.create(_))
  }

  // Initialize four SetWrappers
  def initializeSetWrappers(): Unit = {
    setWrapperFirst = new SetWrapper[Ninja](null)
    setWrapperSecond = new SetWrapper[Ninja](null)
    setWrapperThird = new SetWrapper[Ninja](null)
    setWrapperFourth = new SetWrapper[Ninja](null)
    //List(setWrapperFirst,setWrapperSecond, setWrapperThird, setWrapperFourth).foreach(_= new SetWrapper(null))
  }

  // Initialize two Pairs
  def initializePairs(): Unit = {
    pairFirst = new Pair[Ninja](setWrapperFirst, setWrapperSecond)
    List(setWrapperFirst, setWrapperSecond).foreach(_.setPair(pairFirst))
    pairSecond = new Pair[Ninja](setWrapperThird, setWrapperFourth)
    List(setWrapperThird, setWrapperFourth).foreach(_.setPair(pairSecond))
  }
}
