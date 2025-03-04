# Graph-colouring
graph colouring algorithm, it colours the nodes in the given graph the minimum amount of colours possible
## Interactive GUI
# Insert gif here*****
## Walk through
1. Pressing the generate button, a node will be populated on the scene at a specific area.
 1.1 Initially nodes are white  
 1.2 Continuously pressing the button will generate nodes at that specific area overalying the objects.
2. Drag and drop the nodes on the scene, making sure to space them out.
3. Enter a link between nodes in the provided Textfield in the specified format e.g "2,1"
 3.1 Each link is not directional meaning 1,2 and 2,1 are considered the same, in this case you will be shown a error message if you tried to enter the same link more than once.
4. After you've finished entering your links press the color button.

## How it works 
for each graph the Algorithm will look through the list of nodes in the scene looking for the node with the highest number of neighbours and it will check if it is colored, if not it will color the node with 

 first line  is an integer to indicating the amount of nodes to generate
 the following lines of input will indicate the edges between the edges e.g 5,6
 to end the input enter the integer -> -1

## Output format:
the output of the algo will be the list of all nodes followed by a colon then a number indicating the colour

