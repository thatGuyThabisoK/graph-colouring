package src;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import java.util.Map;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Vertex implements Comparator<Vertex>{
	
	ArrayList<Color> myColors = new ArrayList<>();
	private int colour,vertexNumber = -1111;
	ArrayList<Vertex> adjacentList = new ArrayList<>();
	private Shapes myShapeObj = new Shapes();
	private Circle circleNode;
	
	HashMap<String, Line> links = new HashMap<>();	
	
	public Vertex(int vertexNumber) {
		
		this.vertexNumber = vertexNumber;
		colour = -1;
		circleNode = myShapeObj.createCircle(100,100,25);
		myShapeObj.setOnDrag(circleNode,vertexNumber);
		
		myColors.add(Color.BEIGE);
		myColors.add(Color.CRIMSON);
		myColors.add(Color.PINK);
		myColors.add(Color.BLACK);
		
		
	}
	
	public Vertex(double x, double y) {
		circleNode = myShapeObj.createCircle(x,y,25);
	}
	
	public void updateLink(Vertex node) {
		for(Map.Entry<String, Line> entry : links.entrySet()) {
			
			// for each line in the vertex we make sure that we update the correct points
			//each key in the map is an edge of the form "3,2" ,each vertex will have a key of this form
			// e.g vertex 3 will have an edge 3,2 in its links hash map and so will vertex 2
			String[] startOrEnd = entry.getKey().split(",");
			Line currLine = entry.getValue();
			
			
			if(startOrEnd[0].equals(String.valueOf(vertexNumber))) {
				
				currLine.setStartX(node.getCenterX());
				currLine.setStartY(node.getCenterY());
			}else {
			
				currLine.setEndX(node.getCenterX());
				currLine.setEndY(node.getCenterY());
			}
		}
	}//
	
	
	public void addAdjacency(Vertex v) {
		adjacentList.add(v);
	}
	
	
	public int getDegree() {
		return adjacentList.size();
	}
	
	
	
	public boolean isAdjancent(Vertex v){
		
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
		// of the correct end of the line for instance if there is a node  at the end of a line, then when we
		//move said node we know that we'll have to update the end of the line and we will keep the starting position the same
		
		if(!links.containsKey(edges)) {
			
			links.put(edges,line);
			return true;
			
		}
		
		return false;
		
	}
	
	public int getColour() { return colour;}
	
	public boolean hasNeighbour() {return (adjacentList.size() > 0);}
	
	public int getVertexNumber() { return vertexNumber;}
	public HashMap<String,Line> getLinks(){ return links;}
	
	public double getCenterX() { return circleNode.getCenterX();}
	
	public double getCenterY() { return circleNode.getCenterY();}
	
	public Circle myCircleNode() {return circleNode;}

	public double radius() { return myCircleNode().getRadius();}
	public Shapes myShape() { return myShapeObj;}

	public void changeColour(int dis) {
		
		circleNode.setFill(myColors.get(dis));
		
	}


	@Override
	public int compare(Vertex v1, Vertex v2) {
		
		return (v1.getDegree() > v2.getDegree())? 1 : 0;
	}

	public Vertex getNeighbour() {
		
		return adjacentList.get(0);
	}
	


	
}
