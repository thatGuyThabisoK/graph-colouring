package src;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Vertex{
	
	private int colour,vertexNumber;
	ArrayList<Vertex> adjacentList = new ArrayList<>();
	private Shapes myShapeObj = new Shapes();
	private Circle circleNode;
	private HashMap<String, Line> links = new HashMap<>();	
	
	public Vertex(int vertexNumber) {
		
		this.vertexNumber = vertexNumber;
		colour = -1;
		circleNode = myShapeObj.createCircle(100,100,25);
		myShapeObj.setOnDrag(circleNode,vertexNumber);
		
	}
	
	public void updateLink(double d, double e) {
		for(Map.Entry<String, Line> entry : links.entrySet()) {
			
			// for each line in the vertex we make sure that we update the correct points
			//each key in the map is an edge of the form "3,2" ,each vertex will have a key of this form
			// e.g vertex 3 will have an edge 3,2 in its links hash map and so will vertex 2
			String[] startOrEnd = entry.getKey().split(",");
			
			if(startOrEnd[0].equals(String.valueOf(this.vertexNumber))) {
				entry.getValue().setStartX(d);
				entry.getValue().setStartY(e);
			}else {
				entry.getValue().setEndX(d);
				entry.getValue().setEndY(e);
			}
		}
	}//
	
	
	public void addAdjacency(Vertex v) {
		adjacentList.add(v);
	}
	
	
	public int getDegree() {
		return adjacentList.size();
	}
	
	
	
	public boolean isAdjancent(Vertex v) {
		
		for(Vertex e : adjacentList) {
			if(e.vertexNumber == v.vertexNumber) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public void setColor(int degree) {
		
		int[] used = new int[degree];
		int index = 0;
		for(Vertex curr : adjacentList) {
			if(curr.getColour() != -1) {
				used[curr.getColour()] = 1;
			}
			
		}
		
		for(int i = 0; i < degree; ++i) {
			if(used[i] == 0) {
				index = i;
				break;
			}
		}
		
		colour = index;
		
	}
	
	public boolean addLink(String edges,Line line) { 
	
		//this function adds the line that links this vertex to other vertices to its links hash map 
		//the point of the edges string is for us to be able to update the position
		// of the correct end of the line for instance if the is a node  at the end of a line, then when we
		//move said node we know that we'll have to update the end of the line and we will keep the starting position the same
		
		if(!links.containsKey(edges)) {
			
			links.put(edges,line);
			return true;
			
		}
		
		return false;
		
	}
	
	private boolean isNeighbour(int node) {
		
		//TODO this method will check if the node that is parsed is adjacent to the present node 
		//adjacentList.
		
		return true;
	}
	
	
	public int getColour() { return colour;}
	public void setC(int colour) {this.colour = colour;}
	
	public int getVertexNumber() { return vertexNumber;}
	
	public double getCenterX() { return circleNode.getCenterX();}
	
	public double getCenterY() { return circleNode.getCenterY();}
	
	public Circle myCircleNode() {return circleNode;}

	
}