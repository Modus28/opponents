package dbg28

import org.junit.Assert._
import org.junit.{Before, Test}

/**
  * EECS 293
  * Created by Daniel on 10/20/2016.
  * dbg28@case.edu
  */
class ObjectWrapperTest {

  // Fields
  var test: TestTool = _

  /**
    * Helper method: Sets up a database with test data
    */
  @Before
  def createDatabaseWithNinjas(): Unit = {
    test = new TestTool().createDatabaseWithNinjas()
  }

  /** Tests hashCode
    * Delegation: Doesn't really need to be tested
    * Nominal case: value is initialized (it always is)
    * Dataflow: Value Defined-Used
    */
  @Test
  def hashCodeOverrideTest(): Unit = {
    assertEquals(test.ninjaSet.head.hashCode(), test.objectWrapperSet.head.hashCode())
    assertNotEquals(test.ninjaSet.head.hashCode(), new Ninja().hashCode())
  }

  /** Tests getPair
    *
    * Good data, Input is a Set of ObjectWrappers
    * Nominal Min case: A set of size 1
    * Dataflow: Container & Pair Defined-Used
    */
  @Test
  def testGetPairNominal(): Unit = {
    test.sWrapOne.appendSet(test.objectWrapperSet)
    assertEquals(test.objectWrapperSet.head.getPair, test.pairFirst)
  }

  /** Tests getPair
    *
    * Good data: Input is a Set of ObjectWrappers
    * Nominal Max case: Set of size 10
    * Dataflow: Pair Killed-Used
    */
  @Test
  def testGetPairUninitializedValue(): Unit = {
    test.sWrapOne.appendSet(Set(test.objectWrapperSet.head))
    test.sWrapOne.setPair(null)
    assertEquals(null, test.objectWrapperSet.head.getPair)
  }

  /** Tests getPair
    *
    * Dataflow: Container Used-Defined
    */
  @Test
  def testGetPairContainerNotInitialized(): Unit = {
    try {
      test.objectWrapperSet.head.getContainer.getPair
    } catch {
      case n: NullPointerException => assert(true)
      case e: Exception => fail("Incorrect Exception")
    }
  }
}
