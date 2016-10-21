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
    * Satisfies: Dataflow, Bad Data, Good Data, Nominal case
    * N/A: Branching, Boundary, Compound Boundary,
    */
  @Test
  def testSetContainers(): Unit = {
    tester = new TestTool().createDatabaseWithNinjas()

    try{
      tester.n1Wrap.getContainer
    } catch{
      case n: NullPointerException => assert(true)
      case e: Exception => assert(false)
    }

    tester.setWrapperFirst.setContainers(Set(tester.n1Wrap))
    assertEquals(tester.setWrapperFirst, tester.n1Wrap.getContainer)
  }


  /** Tests getOpponents, as is not an standard getter
    *
    * Satisfies: Dataflow
    * N/A: Branching, Boundary, Compound Boundary,
    * N/A: Bad Data, Good Data, Boundary Analysis
    */
  @Test
  def testGetOpponents(): Unit = {
    assert(tester.setWrapperFirst.getOpponents.equals(tester.setWrapperSecond))
  }
}
