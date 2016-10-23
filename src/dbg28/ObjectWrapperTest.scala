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


  /** Helper method to setup a database with test data
    *
    */
  @Before
  def createDatabaseWithNinjas(): Unit = {
    test = new TestTool().createDatabaseWithNinjas()
  }

  /**
    * Tests the Hashcode delegation for ObjectWrapper to its contained value
    *  Satisfies: Dataflow, Branching
    *  N/A: Boundary, Compound Boundary,
    *  N/A: Bad Data, Good Data, Boundary Analysis
    *
    */
  @Test
  def hashCodeOverrideTest(): Unit = {
    val ninja = new Ninja()
    val objectWrapper = new ObjectWrapper(ninja)
    assertEquals(ninja.hashCode(), objectWrapper.hashCode())
    assertNotEquals(ninja.hashCode(), new Ninja().hashCode())
  }


  /** Tests getOpponents
    *
    * Good data, nominal case, Pair not null
    */
  @Test
  def testGetPairGoodDataNotNull(): Unit = {
    test.setWrapperFirst.appendSet(test.objectWrapperSet)
    assertEquals(test.objectWrapperSet.head.getPair, test.pairFirst)
  }

  /** Tests getOpponents
    *
    * Good data, nominal case, Pair not null
    */
  @Test
  def testGetPairGoodDataNull(): Unit = {
    test.setWrapperFirst.appendSet(test.objectWrapperSet)
    test.setWrapperFirst.setPair(null)
    assertEquals(null, test.objectWrapperSet.head.getPair)
  }

  /** Tests getOpponents
    *
    * Bad data, ObjectWrapper has no SetWrapper
    */
  @Test
  def testGetPairBadData(): Unit = {
    try{
      test.objectWrapperSet.head.getContainer.getPair
    } catch{
      case n: NullPointerException => assert(true)
      case e: Exception=> assert(false, println(e))
    }
  }
}
