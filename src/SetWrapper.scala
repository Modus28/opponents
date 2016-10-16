/**
  * EECS 293
  * Created by Daniel on 10/16/2016.
  * dbg28@case.edu
  *
  * SetWrapper:  Contains a Set of ObjectWrappers that are not opponents of each other
  */
class SetWrapper[N] {


  // Fields
  val objects: Set[ObjectWrapper[N]] = new Set[ObjectWrapper[N]]()
  val pair: Pair = new Pair()

  // Methods

   // Algorithm 8 Get the SetWrapper that opposes this SetWrapper
  def getOpponents: SetWrapper[N] =  getPair.getOpponentSet(this)
  }

  //Algorithm 9 Append a set to this SetWrapper's contained set
  def appendSet(toAppend: SetWrapper[N]): Unit = {
    for(objWrapper <- setWrapper.getObjects){
      objWrapper.setContainer(this)
    }
    setObjects(objects ++ toAppend)
  }

  def getPair(): Pair = this.pair
  def getObjects: Set[ObjectWrapper[N]] = this.objects

  //  Constructors
  // Set each ObjectWrapper's container field to this
  def this(objects: Set[ObjectWrapper[N]]): Unit = {
    for(objWrapper <- objects){
      objWrapper.setContainer(this)
    }
    this
  }
  //   Set operations, such as contains, will be delegated to 'objects'
  implicit def delegateToSet(d: SetWrapper): Set[ObjectWrapper] = d.getObjects

}
