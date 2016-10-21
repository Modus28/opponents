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
  var ninjaSetOfTen: Set[Ninja] = _
  var n1Wrap: ObjectWrapper[Ninja] = _
  var n2Wrap: ObjectWrapper[Ninja] = _
  var objectWrapperSetOfTen: Set[ObjectWrapper[Ninja]] = _


  /**
    *  Setup: Initialize a database with two Pairs of single Ninjas
    */
  def createDatabaseWithNinjas(): TestTool = {
    database = new OpposingGroups[Ninja]()
    n1 = new Ninja()
    n2 = new Ninja()
    ninjaSetOfTen = Set.empty[Ninja]
    1 to 10 foreach { _ => ninjaSetOfTen + new Ninja() }
    n1Wrap = database.create(n1)
    n2Wrap = database.create(n2)
    ninjaSetOfTen foreach {objectWrapperSetOfTen + new ObjectWrapper(_)}
    this
  }
}
