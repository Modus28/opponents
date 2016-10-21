package dbg28

import org.junit.Assert._
import org.junit.Test

/**
  * EECS 293
  * Created by Daniel on 10/20/2016.
  * dbg28@case.edu
  */
class ObjectWrapperTest {

  // Hashcode method: Requires 1 test

  /**
    * Tests the Hashcode delegation for ObjectWrapper to its contained value
    *  Satisfies: Dataflow
    *  N/A: Branching, Boundary, Compound Boundary,
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
}
