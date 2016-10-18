import org.junit.Test
import org.junit.Assert._

/**
  * EECS 293
  * Created by Daniel on 10/16/2016.
  * dbg28@case.edu
  */
class OpponentsTest {
  @Test
  def foo {
    val database = new OpposingGroups[Ninja]()
    val n1 = new Ninja()
    val n2 = new Ninja()
    val n1Wrap = database.create(n1)
    val n2Wrap = database.create(n2)
    assertEquals(database.opponents(n1Wrap, n2Wrap), None)
    database.oppose(n1Wrap,n2Wrap)
    assertEquals(database.opponents(n1Wrap, n2Wrap), Some(true))
  }
}

