package dbg28

import org.junit.Assert._
import org.junit.{Before, Test}
import scala.util.Random

/**
  * EECS 293
  * Created by Daniel on 10/20/2016.
  * dbg28@case.edu
  */
class OpposingGroupsTest {

  // Global fields
  var test: TestTool = _
  var ninja1: ObjectWrapper[Ninja] = _
  var ninja2: ObjectWrapper[Ninja] = _
  // Test Methods


  /**
    * Helper method: Sets up a database with test data
    */
  @Before
  def setup(): Unit = {
    test = new TestTool().createDatabaseWithNinjas()
    ninja1 = test.database.create(test.ninjaSet.head)
    ninja2 = test.database.create(test.ninjaSet.tail.head)
  }


  /** Tests opponents
    *
    * Branching: First condition true
    */
  @Test
  def testOpponentsBranchingFirstTrue(): Unit = {
    test.database.oppose(ninja1, ninja2)
    assertEquals(Some(false), test.database.opponents(ninja2, ninja2))
    assertEquals(Some(true), test.database.opponents(ninja1, ninja2))
  }

  /** Tests opponents
    *
    * Branching: First condition false
    */
  @Test
  def testOpponentsBranchingFirstFalse(): Unit = {
    assertEquals(test.database.opponents(ninja1, ninja2), None)
  }

  /** Tests oppose
    *
    * Good Data: Both inputs are different Ninjas
    * Branching: First condition true
    */
  @Test
  def testOpposeGoodData(): Unit = {
    assertEquals(None, test.database.opponents(ninja1, ninja2))
    test.database.oppose(ninja1, ninja2)
    assertEquals(Some(true), test.database.opponents(ninja1, ninja2))
  }

  /** Tests oppose
    *
    * Bad data: Both inputs already have oppose status known
    * Branching: First condition false
    */
  @Test
  def testOpposeBadDataAlreadyKnown(): Unit = {
    test.database.oppose(ninja1, ninja2)
    try {
      test.database.oppose(ninja1, ninja2)
    } catch {
      case i: IllegalArgumentException =>
      case e: Exception => fail("Incorrect Exception")
    }
  }


  /** Tests oppose
    *
    * Bad data: Inputs are null
    *
    */
  def testOpposeBadDataNullInput(): Unit = {
    try {
      test.database.oppose(null, null)
    } catch {
      case i: NullPointerException =>
      case e: Exception => fail("Incorrect Exception")
    }
  }


  /** Tests addPair
    *
    * Good data: Input is a Pair
    */
  @Test
  def testAddPairGoodData(): Unit = {
    test.testHook.removePairTest(Set(test.pairFirst))
    assertFalse(test.database.getOpposingDatabase.contains(test.pairFirst))
    test.testHook.addPairTest(test.pairFirst)
    assert(test.database.getOpposingDatabase.contains(test.pairFirst))
  }

  /** Tests addPair
    *
    * Bad Data: No input
    */
  @Test
  def testAddPairBadDataNull(): Unit = {
    val currentSize = test.database.getOpposingDatabase.size
    try {
      test.testHook.addPairTest(null)
    } catch {
      case i: IllegalArgumentException =>
      case e: Exception => fail("Incorrect Exception")
    }
    assertEquals(currentSize, test.database.getOpposingDatabase.size)
  }


  /** Tests removePair
    *
    * Good Data: Input is a Pair
    */
  @Test
  def testRemovePairGoodData(): Unit = {
    assert(test.database.getOpposingDatabase.contains(test.pairFirst))
    test.testHook.removePairTest(Set(test.pairFirst))
    assertFalse(test.database.getOpposingDatabase.contains(test.pairFirst))
  }

  /** Tests removePair
    *
    * Bad Data: Input is not a pair
    */
  @Test
  def testRemovePairBadData(): Unit = {
    try {
      test.testHook.removePairTest(null)
    } catch {
      case i: NullPointerException =>
      case e: Exception => fail("Incorrect Exception")
    }
  }


  /** Tests merge
    *
    * Good data: Two sets that in different Pairs
    */
  @Test
  def testMergeGoodData(): Unit = {
    val pairsInDatabase = test.database.getOpposingDatabase.size
    val ninjasInSetOne = test.sWrapOne.getObjects
    val ninjasInSetTwo = test.sWrapThree.getObjects

    test.testHook.mergeTest(test.sWrapOne, test.sWrapThree)

    assertEquals(pairsInDatabase - 1, test.database.getOpposingDatabase.size) // Check that a pair was removed
    assertFalse(test.database.getOpposingDatabase.contains(test.pairSecond)) // Check that the right pair was removed
    assertEquals(ninjasInSetOne ++ ninjasInSetTwo, test.sWrapOne.getObjects) // Check that the sets were appended
  }


  /** Tests merge
    *
    * Bad data: No valid inputs
    */
  @Test
  def testMergeBadDataNoInput(): Unit = {
    try {
      test.testHook.mergeTest(null, null)
    } catch {
      case n: NullPointerException =>
      case e: Exception => fail("Wrong exception thrown")
    }
  }

  /** Tests merge
    *
    * Bad data: Two different sets that are in the same Pair
    */
  @Test
  def testMergeBadDataSamePair(): Unit = {
    // Check that a pair is in the database
    assert(test.database.getOpposingDatabase.contains(test.pairFirst))
    test.testHook.mergeTest(test.sWrapOne, test.sWrapTwo)
    // Check that the Pair was removed
    assertFalse(test.database.getOpposingDatabase.contains(test.pairFirst))
    // Check that the sets contain the same objects
    assertEquals(test.sWrapOne.getObjects, test.sWrapTwo.getObjects)
  }

  /** Tests merge
    *
    * Bad data: Two sets that are the same
    */
  @Test
  def testMergeBadDataSameSet(): Unit = {
    // Record old object sets
    val oldObjectSetOne = test.sWrapOne.getObjects
    val oldObjectSetTwo = test.sWrapTwo.getObjects

    test.testHook.mergeTest(test.sWrapOne, test.sWrapTwo)

    // Verify that the sets are unchanged
    assertEquals(oldObjectSetOne, test.sWrapOne.getObjects)
    assertEquals(oldObjectSetTwo, test.sWrapTwo.getObjects)
  }

  /** Stress Test: Test the whole data structure on 500 Ninjas
    * Creates two Sides of 250 Ninjas, and adds them to the database.
    * The database does not know the true side of each ninja
    * The end Pairs count in the database is checked against what we predict
    */
  @Test
  def stressTest(): Unit = {
    // Setup database with a number of Ninjas
    val database = new OpposingGroups[Ninja]()
    val size = 500
    val ninjaSideA, ninjaSideB = (1 to size / 2).map(_ => new Ninja()).toArray
    val objectSideA = ninjaSideA.map(database.create)
    val objectSideB = ninjaSideB.map(database.create)

    // Randomly oppose Ninjas between the sides
    val r = new Random(System.currentTimeMillis())
    val opposes = 250 // Number of times to execute Oppose randomly
    var doubleCounter = 0 // Counts the number of randomly occurring duplicates
    for (_ <- 1 to opposes) {
      try {
        database.oppose(objectSideA(r.nextInt(size / 2)), objectSideB(r.nextInt(size / 2)))
      } catch {
        case e: IllegalArgumentException => doubleCounter += 1 // Exceptions are normal and ignored
      }
    }
    /* The number of Pairs should be the number of original Pairs minus the number of
     * oppose calls, plus the number of duplicate opposes that were ignored */
    assertEquals(size - opposes + doubleCounter, database.getOpposingDatabase.size)
  }
}
