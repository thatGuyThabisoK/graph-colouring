package src;

import java.util.*;

public class Graph {

	LinkedList<Vertex> vertices = new LinkedList<>();
	
	public Graph() {
		
		Scanner in  = new Scanner(System.in);
		
		String numVertices,edges;
		
		numVertices = in.nextLine();
		
		for(int i = 0; i < Integer.parseInt(numVertices); ++i ) {
			addVertex();
		}
		
		edges = in.nextLine();
		while( !edges.equals("-1")) {
			String[] nums = edges.split(",");
			addEdge(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
			edges = in.nextLine();
		}
		in.close();
		
		colourVertices();
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

		displayDegree();
	}
	
	private void colourVertices() {
		
		while(true) {
			int vertex1 = getBiggestVertex();
			if(vertex1 == -1) {
				break;
			}else {
				vertices.get(vertex1).setColor(vertices.size());
			}
		
		}
		
	}
	
	private void displayDegree() {
		
		for(Vertex v : vertices) {
			System.out.println(v.getVertexNumber() +" : "+ v.getColour());
		}
	}
	
	public void addVertex() {
		vertices.add(new Vertex(vertices.size()));
	}
	
	public Vertex getVertex(int vertexIndex) {
		return vertices.get(vertexIndex);
	}
	
	public void addEdge(int vertex1,int vertex2) {
		getVertex(vertex1).addAdjacency(getVertex(vertex2));
		getVertex(vertex2).addAdjacency(getVertex(vertex1));

	}
	
	public int getBiggestVertex() {
		
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
