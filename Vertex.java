package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Vertex {
	
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
			//by first checking 
			
			if(entry.getKey().contains("start")) {
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
	
	public void addLink(String startOrEnd,Line line) { 
	
		if(!links.containsKey(startOrEnd)) {
			
			links.put(startOrEnd,line);
			
		}else {
				
			addLink(startOrEnd.concat(Integer.toString(new Random().nextInt())), line);
		
		}
		
	}
	
	
	public int getColour() { return colour;}
	public void setC(int colour) {this.colour = colour;}
	
	public int getVertexNumber() { return vertexNumber;}
	
	public double getCenterX() { return circleNode.getCenterX();}
	
	public double getCenterY() { return circleNode.getCenterY();}
	
	public Circle myCircleNode() {return circleNode;}

}
