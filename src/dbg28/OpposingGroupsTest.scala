package dbg28


import org.junit.Assert._
import org.junit.{Before, Test}

/**
  * EECS 293
  * Created by Daniel on 10/20/2016.
  * dbg28@case.edu
  */
class OpposingGroupsTest {

  // Global fields
  var test: TestTool = _
  var ninja1: ObjectWrapper[Ninja] = _
  var ninja2: ObjectWrapper[Ninja] = _
  // Test Methods


  /**
    * Helper method: Sets up a database with test data
    */
  @Before
  def setup(): Unit = {
    test = new TestTool().createDatabaseWithNinjas()
    ninja1 = test.objectWrapperSet.head
    ninja2 = test.objectWrapperSet.tail.head
  }


  /**
    * Checks for correct opponent status for two ninjas, in the three states of rivalry
    * Three States: Unknown Rivalry, allies, opponents
    */
  @Test
  def testOpponents() {
    assertEquals(test.database.opponents(ninja1, ninja2), None)
    test.database.oppose(ninja1, ninja2)
    assertEquals(Some(false), test.database.opponents(ninja2, ninja2))
    assertEquals(Some(true),test.database.opponents(ninja1, ninja2))
  }

  /**
    * Adds the fact that two Ninjas are opponents, and verifies this fact against the database
    */
  @Test
  def testOppose(): Unit = {
    assertEquals(None,test.database.opponents(ninja1, ninja2))
    test.database.oppose(ninja1, ninja2)
    assertEquals(Some(true), test.database.opponents(ninja1, ninja2))
    try {
      test.database.oppose(ninja1, ninja2)
    } catch {
      case i: IllegalArgumentException =>
      case e: Exception => fail("Incorrect Exception")
    }
  }


  @Test
  def testAddPairGoodData(): Unit = {
    test.testHook.addPairTest(test.pairFirst)
    assert(test.database.getOpposingDatabase.contains(test.pairFirst))
  }

  @Test
  def testRemovePairGoodData(): Unit = {
    test.testHook.addPairTest(test.pairFirst)
    test.testHook.removePairTest(Set(test.pairFirst))
    assertFalse(test.database.getOpposingDatabase.contains(test.pairFirst))
  }


}
