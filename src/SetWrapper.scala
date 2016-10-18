import scala.language.implicitConversions
/**
  * EECS 293
  * Created by Daniel on 10/16/2016.
  * dbg28@case.edu
  *
  * SetWrapper:  Contains a Set of ObjectWrappers that are not opponents of each other
  */
class SetWrapper[N]  {

  // Fields

  var objects: Set[ObjectWrapper[N]] = Set[ObjectWrapper[N]]()
  var pair: Pair[N] = _

  // Methods

  /** Return the SetWrapper that opposes this SetWrapper
    *
    * @return the opponents of this SetWrapper
    */
  def getOpponents: SetWrapper[N] = getPair.getOpponentSet(this)

  /** Returns the pair that contains this
    *
    * @return pair holding this
    */
  def getPair: Pair[N] = this.pair

  /** Append a set to this SetWrapper's contained set
    *
    * @param toAppend the set to add to our contained set
    */
  def appendSet(toAppend: SetWrapper[N]): Unit = {
    if (Option(toAppend.getObjects).isDefined) {
      for (objWrapper <- toAppend.getObjects) {
        objWrapper.setContainer(this)
      }
      setObjects(objects ++ toAppend.getObjects)
    }
    else{
      // We append nothing
    }
  }

  /** Return the set of ObjectWrappers inside this
    *
    * @return set of ObjectWrappers this contains
    */
  def getObjects: Set[ObjectWrapper[N]] = this.objects

  /** Set the pair containing this to the input
    *
    * @param pair the pair to update the pair field to
    */
  def setContainer(pair: Pair[N]): Unit = this.pair = pair


  /** Set the set of ObjectWrappers to the input
    *
    * @param obj the set of ObjectWrappers to set
    */
  def setObjects(obj: Set[ObjectWrapper[N]]): Unit = this.objects = obj

  //  Constructors

  /** Set each ObjectWrapper's container field to input
    * And initialize a SetWrapper
    *
    * @param objects the objects to insert into this
    */
  def this(objects: Set[ObjectWrapper[N]]) = {
    this
    if(Option(objects).isDefined && objects.nonEmpty) {
      for (objWrapper <- objects) {
        objWrapper.setContainer(this)
      }
      setObjects(objects)
    }
    else {
      setObjects(Set[ObjectWrapper[N]](null))
    }
  }


}
