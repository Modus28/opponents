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
    assertEquals(Some(true), test.database.opponents(ninja1, ninja2))
  }

  /**
    * Adds the fact that two Ninjas are opponents, and verifies this fact against the database
    */
  @Test
  def testOppose(): Unit = {
    assertEquals(None, test.database.opponents(ninja1, ninja2))
    test.database.oppose(ninja1, ninja2)
    assertEquals(Some(true), test.database.opponents(ninja1, ninja2))
    try {
      test.database.oppose(ninja1, ninja2)
    } catch {
      case i: IllegalArgumentException =>
      case e: Exception => fail("Incorrect Exception")
    }
  }


  /** Tests addPair
    *
    * Good data: Input is a Pair
    */
  @Test
  def testAddPairGoodData(): Unit = {
    test.testHook.addPairTest(test.pairFirst)
    assert(test.database.getOpposingDatabase.contains(test.pairFirst))
  }

  /** Tests addPair
    *
    * Bad Data: No input
    */
  @Test
  def testAddPairBadDataNull(): Unit = {
    val currentSize = test.database.getOpposingDatabase.size
    try {
      test.testHook.addPairTest(null)
    } catch {
      case i: IllegalArgumentException =>
      case e: Exception => fail("Incorrect Exception")
    }
    assertEquals(currentSize, test.database.getOpposingDatabase.size)
  }


  /** Tests removePair
    *
    * Good Data: Input is a Pair
    */
  @Test
  def testRemovePairGoodData(): Unit = {
    assertFalse(test.database.getOpposingDatabase.contains(test.pairFirst))
    test.testHook.addPairTest(test.pairFirst)
    assert(test.database.getOpposingDatabase.contains(test.pairFirst))
    test.testHook.removePairTest(Set(test.pairFirst))
    assertFalse(test.database.getOpposingDatabase.contains(test.pairFirst))
  }

  /** Tests removePair
    *
    * Bad Data: Input is not a pair
    */
  @Test
  def testRemovePairBadData(): Unit = {
    try{
      test.testHook.removePairTest(null)
    } catch{
      case i: NullPointerException =>
      case e: Exception => fail("Incorrect Exception")
    }
  }
}
