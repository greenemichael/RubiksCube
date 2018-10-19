# Rubiks Cube "solver"

# Class structure:
Rubiks cube is represented by the Cube class. A Cube has 6 faces represented by the Face class. 
Each Face holds a color attribute and a rotate (helper) method. A Face rotates itself as a subroutine of the methods a Cube can invoke: 
- right (right Face clockwise)
- left (left Face clockwise)
- top (top Face clockwise if looking down at the cube)
- bottom (bottom Face clockwise if looking *__down__* at the cube, counterclockwise if looking up)
- and the inverse of all the above

A Face also has a method to score itself based on the number of same-colored tiles it contains. Each score contributes to a heuristic for the entire Cube.

# Search:
An initial search node is created, represented by the Node class. Each search Node contains a Cube (a search state), a string representing the move which produced the cube, and a pointer to the search Node preceding it.

BeamSearch is used to find a solution by expanding a Node (one child for each method a Cube can invoke), choosing its M lowest scoring children, and repeating the expansion/selection for each of the M children until a node is found with a score of 0 (solved).

The M lowest scoring children are found at each level of the tree by performing Quickselect on an array of the child nodes. Doing this to select the Mth smallest element will produce a partitioned array where the first M nodes are the M lowest scoring ones (however they may be out of order within these first M).
