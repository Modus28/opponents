/**
  * EECS 293
  * Created by Daniel on 10/16/2016.
  * dbg28@case.edu
  *
  * Object Wrapper: Adds functionality to the generic type N
  */
class ObjectWrapper[N](value: N) {
  // Fields
  var container: SetWrapper[N] = _ // The SetWrapper that holds this ObjectWrapper

  // Methods

  /** Delegate hashcode to the contained value
    * This allows us to change pointers without breaking hashing
    *
    * Because the contained value is either generic or a Ninja,
    * hashcode will default the object's location in memory.
    *
    * @return hashcode of the contained value
    */
  override def hashCode(): Int = this.getValue.hashCode()


  /** Returns the value contained in this ObjectWrapper
    *
    * @return the value
    */
  def getValue: N = this.value

  /** Returns the pair that holds this ObjectWrapper's container
    *
    * @return the pair of this ObjectWrapper
    */
  def getPair: Pair[N] = getContainer.getPair

  /** Return the SetWrapper that contains this
    *
    * @return the containing SetWrapper
    */
  def getContainer: SetWrapper[N] = this.container

  /** Sets the pointer to the SetWrapper containing this
    *
    * @param s the new SetWrapper that contains this
    */
  def setContainer(s: SetWrapper[N]): Unit = this.container = s


}
