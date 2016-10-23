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
    * Nominal case, Good Data: SetWrapper has opponents
    *
    */
  @Test
  def testGetOpponentsGoodData(): Unit = {
    assertEquals(test.sWrapOne.getOpponents, test.sWrapTwo)
  }

  /** Tests getOpponents
    *
    * Bad Data: No Opponents
    */
  @Test
  def testGetOpponentsBadData(): Unit = {
    val badSetWrapper = new SetWrapper[Ninja](null)
    assertEquals(null, badSetWrapper.getOpponents)
  }

  /** Tests appendSet
    *
    * Bad Data: Append nothing, Set of Nothing
    *
    */
  @Test
  def testAppendSetBadData(): Unit = {
    test.sWrapOne.appendSet(Set[ObjectWrapper[Ninja]]())
    assert(test.sWrapOne.getObjects.isEmpty)
    try {
      test.sWrapOne.appendSet(null)
    } catch {
      case n: NullPointerException =>
      case e: Exception => fail("Incorrect Exception")
    }
  }

  /** Tests appendSet
    *
    * Good Data: Append a Set of ObjectWrappers
    * Min nominal case: Set of size one
    *
    */
  @Test
  def testAppendSetGoodDataMinNominal(): Unit = {
    test.sWrapOne.appendSet(Set(test.objectWrapperSet.head))
    assert(test.objectWrapperSet.head.getContainer.equals(test.sWrapOne))
  }

  /** Tests appendSet
    *
    * Good Data: Append a Set of ObjectWrappers
    * Max nominal case: Set of size ten
    *
    */
  @Test
  def testAppendSetGoodDataMaxNominal(): Unit = {
    test.sWrapOne.appendSet(test.objectWrapperSet)
    assert(test.objectWrapperSet.forall(_.getContainer.equals(test.sWrapOne)))
  }

  /** Tests Constructor
    *
    * Branching: If condition is true
    * Good Data: Data is an Object List
    */
  @Test
  def testConstructorBranchingTrue(): Unit = {
    val set = new SetWrapper[Ninja](Set(test.objectWrapperSet.head))
    assert(test.objectWrapperSet.head.getContainer.equals(set))
  }

  /** Tests Constructor
    *
    * Branching: If condition is false
    * Bad Data: Input is null
    */
  @Test
  def testConstructorBranchingFalse(): Unit = {
    val set = new SetWrapper[Ninja](null)
    assert(set.getObjects.isEmpty)
  }
}
