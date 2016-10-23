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

  var tester: TestTool = _

  /** Helper method to setup a database with test data
    *
    */
  @Before
  def createDatabaseWithNinjas(): Unit = {
    tester = new TestTool().createDatabaseWithNinjas()
  }

  /** Tests setContainer
    *
    * Bad Data: Null Input
    */
  @Test
  def testSetContainerBadData(): Unit = {
    try{
      tester.setWrapperFirst.setContainers(Set(null))
    } catch{
      case n: NullPointerException => assert(true)
      case e: Exception => assert(false)
    }
  }

  /** Tests setContainer
    *
    * Good Data & Nominal case: Set of Objects
    */
  @Test
  def testSetContainerGoodData(): Unit = {
    tester.setWrapperFirst.setContainers(Set(tester.n1Wrap))
    assertEquals(tester.setWrapperFirst, tester.n1Wrap.getContainer)
  }


  /** Tests getOpponents
    *
    * Good data, nominal case: SetWrapper has opponents and containing Pair
    */
  @Test
  def testGetOpponentsGoodData(): Unit = {
    assertEquals(tester.setWrapperFirst.getOpponents, tester.setWrapperSecond)
  }

  /** Tests getOpponents
    *
    * Bad Data: No Pair to get opponents from
    */
  @Test
  def testGetOpponentsBadData(): Unit = {
    val badSetWrapper = new SetWrapper[Ninja]()
    try{
      badSetWrapper.getOpponents
    } catch{
      case n: NullPointerException => assert(true)
      case e: Exception => assert(false)
    }
  }



  /** Tests appendSet
    *
    * Bad Data: Append nothing
    *
    */
  @Test
  def testAppendSetBadData(): Unit = {
    try {
      tester.setWrapperFirst.appendSet(null)
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
    tester.setWrapperFirst.appendSet(tester.objectWrapperSet)
    assert(tester.objectWrapperSet.forall(_.getContainer.equals(tester.setWrapperFirst)))
  }
}
