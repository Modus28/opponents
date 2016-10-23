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
    *
    */
  @Test
  def hashCodeOverrideTest(): Unit = {
    assertEquals(test.ninjaSet.head.hashCode(), test.objectWrapperSet.head.hashCode())
    assertNotEquals(test.ninjaSet.head.hashCode(), new Ninja().hashCode())
  }


  /** Tests getPair
    *
    * Good data, nominal case,
    * Dataflow: Container & Pair Defined-Used
    */
  @Test
  def testGetPairNominal(): Unit = {
    test.setWrapperFirst.appendSet(test.objectWrapperSet)
    assertEquals(test.objectWrapperSet.head.getPair, test.pairFirst)
  }

  /** Tests getOpponents
    *
    * Good data
    * Dataflow: Pair Killed-Used
    */
  @Test
  def testGetPairUninitializedValue(): Unit = {
    test.setWrapperFirst.appendSet(Set(test.objectWrapperSet.head))
    test.setWrapperFirst.setPair(null)
    assertEquals(null, test.objectWrapperSet.head.getPair)
  }

  /** Tests getOpponents
    *
    * Bad data
    * Dataflow: Container Used-Defined
    */
  @Test
  def testGetPairException(): Unit = {
    try{
      test.objectWrapperSet.head.getContainer.getPair
    } catch{
      case n: NullPointerException => assert(true)
      case e: Exception=> assert(false)
    }
  }
}
