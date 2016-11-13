# EECS 293: opponents
### Software Craftsmanship Project for determining what Ninjas oppose each other in a war between clans
The two opposing clans of Nobunaga and Hideyoshi are locked in a war for supremacy. They have recently started to hire ninja warriors to launch covert operations against each other, and the shogun is worried. He has asked you to find out more about the ninjas, not an easy task given their methods and secrecy. However, your informants occasionally report that two ninjas belong to different factions. At that point, although you are still in the dark as to whether a ninja fights for Nobunaga or for Hideyoshi, at least you know that they are on different sides. Furthermore, the shogun often asks you if you have any information on whether two warriors are on the same side.
To get a handle on the ongoing war, you will design a data structure that supports the following operations:

* CREATE(x): add object x to the set of known objects.
* OPPOSE(x, y): add the fact that objects x and y belong to different sides, or report an error if x and y are already known to be on the same side.
* OPPONENTS(x, y): return whether x and y belong to different sides, or if this property cannot be established.

Here are some general considerations about this data structure:
* Although the shogun is mostly interested in the case when x and y are of type Ninja, the data structure should be generic.
* You should come up with the fastest design for these methods.
