package src;

import java.util.ArrayList;

import javafx.scene.shape.Circle;

public class Vertex {
	
	private int colour,vertexNumber;
	ArrayList<Vertex> adjacentList = new ArrayList<>();
	Shapes myShapeObj = new Shapes();
	Circle circleNode;
	
	public int getColour() { return colour;}
	public void setC(int colour) {this.colour = colour;}
	
	public int getVertexNumber() { return vertexNumber;}
	
	public Vertex(int vertexNumber) {
		
		this.vertexNumber = vertexNumber;
		colour = -1;
		circleNode = myShapeObj.createCircle(100,100,25);
		myShapeObj.setOnDrag(circleNode,vertexNumber);
		
	}
	
	
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
	

}
