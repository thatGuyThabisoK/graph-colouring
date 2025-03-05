# Graph-colouring
graph colouring algorithm, it colours the nodes in the given graph the minimum amount of colours possible
## Walk through
1. Pressing the generate button, a node will be populated on the scene at a specific area.
<p>  1.1 Initially nodes are white.</p>
<p>  1.2 Continuously pressing the button will generate nodes at that specific area overalying the objects.</p>
2. Drag and drop the nodes on the scene, making sure to space them out.
<img src="https://github.com/thatGuyThabisoK/graph-colouring/blob/main/res/GenerateNodes.gif"/>
3. Enter a link between nodes in the provided Textfield in the specified format e.g "2,1".
<img src="https://github.com/thatGuyThabisoK/graph-colouring/blob/main/res/links.gif"/>
 <p>3.1 Each link is not directional meaning 1,2 and 2,1 are considered the same, in this case you will be shown a error message if you tried to enter the same link more than once. </p>
4. After you've finished entering your links press the color button.
<img src="https://github.com/thatGuyThabisoK/graph-colouring/blob/main/res/coloring.gif"/>

## How it works 
for each graph the Algorithm will look through the list of nodes in the scene looking for the node with the highest number of neighbours and it will check if it is colored, if not it will color the node with 
