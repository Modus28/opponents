package dbg28

import org.junit.Assert._
import org.junit.{Before, Test}

/**
  * EECS 293
  * Created by Daniel on 10/20/2016.
  * dbg28@case.edu
  */
class SetWrapperTest {

  // Fields

  var test: TestTool = _

  /**
    * Helper method: Sets up a database with test data
    */
  @Before
  def createDatabaseWithNinjas(): Unit = {
    test = new TestTool().createDatabaseWithNinjas()
  }

  /** Tests getOpponents
    *
    * Nominal case, Good Data: SetWrapper has opponents and containing Pair
    *
    */
  @Test
  def testGetOpponentsGoodData(): Unit = {
    assertEquals(test.setWrapperFirst.getOpponents, test.setWrapperSecond)
  }

  /** Tests getOpponents
    *
    * Bad Data: No Opponents
    */
  @Test
  def testGetOpponentsBadData(): Unit = {
    val badSetWrapper = new SetWrapper[Ninja]()
    assertEquals(null,badSetWrapper.getOpponents)
  }


  /** Tests appendSet
    *
    * Bad Data: Append nothing
    *
    */
  @Test
  def testAppendSetBadData(): Unit = {
    try {
      test.setWrapperFirst.appendSet(null)
    } catch{
      case n: NullPointerException => assert(true)
      case e: Exception => println(e.printStackTrace())
    }
  }

  /** Tests appendSet
    *
    * Good Data: Append a Set of ObjectWrappers
    *
    */
  @Test
  def testAppendSetGoodData(): Unit = {
    test.setWrapperFirst.appendSet(test.objectWrapperSet)
    assert(test.objectWrapperSet.forall(_.getContainer.equals(test.setWrapperFirst)))
  }
}
