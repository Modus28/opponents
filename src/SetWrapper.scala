/**
  * EECS 293
  * Created by Daniel on 10/16/2016.
  * dbg28@case.edu
  *
  * SetWrapper:  Contains a Set of ObjectWrappers that are not opponents of each other
  */
class SetWrapper[N] {

  // Fields

  private var objects: Set[ObjectWrapper[N]] = Set[ObjectWrapper[N]]()
  private var pair: Pair[N] = _

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

  /**
    * Set the pair containing this to the input
    *
    * @param pair the pair to update the pair field to
    */
  def setPair(pair: Pair[N]): Unit = this.pair = pair

  /**
    * Return the set of ObjectWrappers inside this
    *
    * @return set of ObjectWrappers this contains
    */
  def getObjects: Set[ObjectWrapper[N]] = this.objects

  /**
    * Set the container pointer for a set of objects to this
    *
    * @param objects objects to set container pointer for
    */
  private def setContainers(objects: Set[ObjectWrapper[N]]): Unit = objects foreach (_.setContainer(this))

  /**
    * Set the set of ObjectWrappers to the input
    *
    * @param obj the set of ObjectWrappers to set
    */
  private def setObjects(obj: Set[ObjectWrapper[N]]): Unit = this.objects = obj


  /** Append a set to this SetWrapper's contained set
    *
    * @param toAppend the set to add to our contained set
    */
  def appendSet(toAppend: Set[ObjectWrapper[N]]): Unit = {
    setObjects(objects ++ toAppend)
    setContainers(toAppend)
  }

  //  Constructors

  /** Set each ObjectWrapper's container field to input
    * And initialize a SetWrapper
    *
    * @param objectList the objects to insert into this
    */
  def this(objectList: Set[ObjectWrapper[N]]) = {
    this
    if (Option(objectList).isDefined) {
      appendSet(objectList)
    }
    else {
      setObjects(Set[ObjectWrapper[N]](null))
    }
  }
}
