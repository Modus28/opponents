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

  var tester: TestTool = _


  /** Helper method to setup a database with test data
    *
    */
  @Before
  def createDatabaseWithNinjas(): Unit = {
    tester = new TestTool().createDatabaseWithNinjas()
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
    tester.setWrapperFirst.appendSet(tester.objectWrapperSet)
    assertEquals(tester.objectWrapperSet.head.getPair, tester.pairFirst)
  }

  /** Tests getOpponents
    *
    * Good data, nominal case, Pair not null
    */
  @Test
  def testGetPairGoodDataNull(): Unit = {
    tester.setWrapperFirst.appendSet(tester.objectWrapperSet)
    tester.setWrapperFirst.setPair(null)
    assertEquals(null, tester.objectWrapperSet.head.getPair)
  }

  /** Tests getOpponents
    *
    * Bad data, ObjectWrapper has no SetWrapper
    */
  @Test
  def testGetPairBadData(): Unit = {
    try{
      tester.objectWrapperSet.head.getContainer.getPair
    } catch{
      case n: NullPointerException => assert(true)
      case e: Exception=> assert(false, println(e))
    }
  }
}
