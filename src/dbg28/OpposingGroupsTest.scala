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
  var tester: TestTool = _
  // Test Methods


  @Before
  def setup(): Unit = {
    tester = new TestTool().createDatabaseWithNinjas()
  }

  /**
    * Attempts to create the database and check that values were added to it
    */
  @Test
  def testCreate(): Unit = {
    assertEquals(12, tester.database.getOpposingDatabase.size)
  }

  /**
    * Checks for correct opponent status for two ninjas, in the three states of rivalry
    * Three States: Unknown Rivalry, allies, opponents
    */
  @Test
  def testOpponents() {
    assertEquals(tester.database.opponents(tester.n1Wrap, tester.n2Wrap), None)
    tester.database.oppose(tester.n1Wrap, tester.n2Wrap)
    assertEquals(Some(false), tester.database.opponents(tester.n2Wrap, tester.n2Wrap))
    assertEquals(Some(true),tester.database.opponents(tester.n1Wrap, tester.n2Wrap))
  }

  /**
    * Adds the fact that two Ninjas are opponents, and verifies this fact against the database
    */
  @Test
  def testOppose(): Unit = {
    assertEquals(None,tester.database.opponents(tester.n1Wrap, tester.n2Wrap))
    tester.database.oppose(tester.n1Wrap, tester.n2Wrap)
    assertEquals(Some(true), tester.database.opponents(tester.n1Wrap, tester.n2Wrap))
    try {
      tester.database.oppose(tester.n1Wrap, tester.n2Wrap)
      assert(false) // Exception should be thrown before this would occur
    } catch {
      case i: IllegalArgumentException => assert(true)
      case e: Exception => assert(false)
    }
  }
}
