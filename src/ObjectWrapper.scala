/**
  * EECS 293
  * Created by Daniel on 10/16/2016.
  * dbg28@case.edu
  *
  * Object Wrapper: Adds functionality to the generic type N
  */
class ObjectWrapper[N] {
  // Fields
    val value: N = ??? //the generic object that this ObjectWrapper contains
    var container: SetWrapper[N] = ??? // The SetWrapper that holds this ObjectWrapper

  // Methods

  // Algorithm 10 Delegate hashcode to the contained value
  override def hashCode(): Int = this.getValue.hashCode()


  def getValue: N = this.value
  def getContainer: SetWrapper[N] = this.container
  def setContainer(s: SetWrapper[N]): Unit = this.container = s
  def getPair: Pair = getContainer.getPair()

  4
}
