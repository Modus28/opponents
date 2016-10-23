package dbg28

import org.junit.Assert._
import org.junit.{Before, Test}

/**
  * EECS 293
  * Created by Daniel on 10/20/2016.
  * dbg28@case.edu
  */
class PairTest {

  // Fields
  var test: TestTool = _

  /**
    * Helper method: Sets up a database with test data
    */
  @Before
  def createDatabaseWithNinjas(): Unit = {
    test = new TestTool().createDatabaseWithNinjas()
  }

  /** Tests getOpponentSet
    *
    * Good data: SetWrapper object in the Pair as input
    * Branching: First condition is true, second is false
    * Dataflow: First condition is true, second is false
    */
  @Test
  def testGetOpponentSetOnlyFirstBranch(): Unit = {
    assertEquals(test.pairFirst.getOpponentSet(test.sWrapOne), test.sWrapTwo)
  }

  /** Tests getOpponentSet
    *
    * Good data: SetWrapper object in the Pair as input
    * Branching: First condition is false, second is true
    * Dataflow: First condition is false, second is true
    */
  @Test
  def testGetOpponentSetOnlySecondBranch(): Unit = {
    assertEquals(test.pairFirst.getOpponentSet(test.sWrapTwo), test.sWrapOne)
  }

  /** Tests getOpponentSet
    *
    * Bad data: SetWrapper object not in the Pair as input
    * Dataflow: Both conditions are false
    */
  @Test
  def testGetOpponentSetOnlyThirdBranch(): Unit = {
    try {
      test.pairFirst.getOpponentSet(test.sWrapFour)
    }
    catch {
      case n: IllegalArgumentException => assert(true)
      case e: Exception => fail("Incorrect Exception")
    }
  }

  /** Tests getOpponentSet
    *
    * Bad Data: Malformed Pair with same primary and secondary SetWrappers
    * Dataflow: Both conditions are true
    */
  @Test
  def testGetOpponentSetAllStatementsTrue(): Unit = {
    test.pairFirst = new Pair(test.sWrapOne, test.sWrapOne)
    assertEquals(test.pairFirst.getOpponentSet(test.sWrapOne), test.sWrapOne)
  }

  /** Tests getOpponentSet
    *
    * Branching: Third branch used
    * Bad data: No input
    */
  @Test
  def testGetOpponentSetInputNull(): Unit = {
    try {
      test.pairFirst.getOpponentSet(null)
    }
    catch {
      case n: IllegalArgumentException => assert(true)
      case e: Exception => fail("Incorrect Exception")
    }
  }
}
