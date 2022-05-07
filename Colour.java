package src;

import java.util.*;

public class Colour {
	
	public Colour(ArrayList<Vertex> vertices) {
	
		
		colourVertices(vertices);
		
		//
		
		
		//System.out.println("**************");
		/*for(int i = 0; i < vertices.size(); ++i) {
			int vertex = getBiggestVertex();
			if(vertex == -1) {
				break;
			}
			vertices.get(vertex).setC(2);
			System.out.println(vertex);
		}
		System.out.println("**************");*/

	//	displayDegree(vertices);
	}
	
	private void colourVertices(ArrayList<Vertex> vertices) {
		
		while(true) {
			int vertex1 = getBiggestVertex(vertices);
			if(vertex1 == -1) {
				break;
			}else {
				vertices.get(vertex1).setColor(vertices.size());
			}
		
		}
		
	}
	
	
	
	private void displayDegree(ArrayList<Vertex> vertices) {
		
		for(Vertex v : vertices) {
			System.out.println(v.getVertexNumber() +" : "+ v.getColour());
		}
	}
	
	public void addVertex(ArrayList<Vertex> vertices) {
		vertices.add(new Vertex(vertices.size()));
	}
	
	public Vertex getVertex(ArrayList<Vertex> vertices, int vertexIndex) {
		return vertices.get(vertexIndex);
	}
	
	public void addEdge(int vertex1,int vertex2, ArrayList<Vertex> vertices) {
		getVertex(vertices,vertex1).addAdjacency(getVertex(vertices,vertex2));
		getVertex(vertices,vertex2).addAdjacency(getVertex(vertices,vertex1));

	}
	
	public int getBiggestVertex(ArrayList<Vertex> vertices) {
		
			Vertex curr = vertices.get(0);
			for(int i = 0; i < vertices.size(); ++i) {
				
				if(curr.getColour() != -1) {
					if(i == vertices.size()-1) {
						return -1;
					}
					curr = vertices.get(i+1);
					continue;
				}
				
				if(curr.getDegree() < vertices.get(i).getDegree() && vertices.get(i).getColour() == -1) {
					curr = vertices.get(i);
				}
				
			}
			return curr.getVertexNumber();
		
		
	}

}
