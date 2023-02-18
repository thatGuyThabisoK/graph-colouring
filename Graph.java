package src;

import java.util.ArrayList;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class graph extends Application{

	Button generate = new Button("Generate");
	ArrayList<Vertex> vertices = new ArrayList<>();
	TextField edgeField = new TextField();
	Button enterEdge = new Button("Enter edge");
	Button removeNode = new Button("Clear node");
	Button colourVertices  = new Button("Colour");
	boolean showAlert = true;
	public static void main(String[] args) {

			Application.launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("First FX");
		
		Pane pane = new Pane();	
		
		pane.setOnDragOver(dragOverEvent -> {
			if(dragOverEvent.getGestureSource() != pane && dragOverEvent.getDragboard().hasString()) {
				dragOverEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			
			dragOverEvent.consume();
		});
		
		pane.setOnDragDropped(droppedEvent -> {
			
			Dragboard droppedBoard = droppedEvent.getDragboard();
			
			if(droppedBoard.hasString()) {				
				
				int nodeAt = Integer.parseInt(droppedBoard.getString());
				
				
				getVertex(nodeAt).myCircleNode().setCenterX(droppedEvent.getSceneX());
				getVertex(nodeAt).myCircleNode().setCenterY(droppedEvent.getSceneY());
				
				
				//
				getVertex(nodeAt).updateLink(droppedEvent.getSceneX(), droppedEvent.getSceneY());
				
				//getVertex(nodeAt).myShape().getDirection(getVertex(nodeAt).myCircleNode().);
				
				
				
				droppedEvent.setDropCompleted(true);
			}else {
				droppedEvent.setDropCompleted(false);
			}
			droppedEvent.consume();
		});
		
		setDimentions(generate,200,400);
		setDimentions(removeNode,280,400);
		setDimentions(edgeField,400, 400);
		setDimentions(enterEdge,550, 400);
		setDimentions(colourVertices,650,400);
		
		
		edgeField.setPromptText("Enter as such e.g 2,3 ");
		
		generate.setOnAction(actionEvent -> {
			
			//Each time the generate button is pressed
			//It should create a new vertex object containing a circle object that will be added to the scene
			Vertex circleChild = new Vertex(vertices.size());
			vertices.add(circleChild);
			
			pane.getChildren().add(circleChild.myCircleNode());
		});
		
		
		enterEdge.setOnAction(actionEvent -> {
			//oSystem.out.println();
			String edges = edgeField.getText();
			if(!edges.isEmpty() && edges.split(",").length == 2) {
					String nodes[] = edges.split(",");
					System.out.println(edges);
					int nodeAt1 = Integer.parseInt(nodes[0]);
					int nodeAt2 = Integer.parseInt(nodes[1]);
					
					
					if(!getVertex(nodeAt1).isAdjancent(getVertex(nodeAt2))) {
						
						addEdge(nodeAt1,nodeAt2);
						
						
						
						Line edgeLine = new Line();
					//	edgeLine.
						edgeLine.setStartX(getVertex(nodeAt1).getCenterX());
						edgeLine.setStartY(getVertex(nodeAt1).getCenterY());
						
						edgeLine.setEndX(getVertex(nodeAt2).getCenterX());
						edgeLine.setEndY(getVertex(nodeAt2).getCenterY());
						
						
						if(getVertex(nodeAt1).addLink(edges,edgeLine) && getVertex(nodeAt2).addLink(edges,edgeLine))
							pane.getChildren().add(edgeLine);
							
						
						
						edgeField.setText("");
						
					}else {
						System.err.println("ERROR this vertices are already neibours");
					}
									
				
					
			}else {
				
				showError("Please check that you have entered, if at all, the edges in the correct format e.g 4,5");
				
			}
			
			
			
			
		});
		
		removeNode.setOnAction(remove->{
	
				
			///showWarning("The last vertex to be added will be removed along with any edges associated with it");
			
			for(int i = 0; i < vertices.size(); ++i) {
				
				if(pane.contains(vertices.get(i).getCenterX(), vertices.get(i).getCenterY()) ) {
					pane.getChildren().remove(vertices.get(i).myCircleNode());
					
					for(Map.Entry<String, Line> entry : vertices.get(i).getLinks().entrySet()) {
						
						
						pane.getChildren().remove(entry.getValue());
						
						
					}
					
					
				}
				
			}
			
			vertices.clear();
			
			
			
		});
		
		
		
		colourVertices.setMnemonicParsing(true);
		colourVertices.setText("_Colour");
		
		colourVertices.setOnAction(colourAction->{
		
			int length = vertices.size();
			
			if(length == 0) { showWarning("No vertices to colour");}
			else
				new Colour(vertices);
			
			
			
			
		});
		
		
		
		pane.getChildren().add(generate);
		pane.getChildren().add(edgeField);
		pane.getChildren().add(enterEdge);
		pane.getChildren().add(removeNode);
		pane.getChildren().add(colourVertices);
		
		
		Scene myScene = new Scene(pane,900,500,true);
		
		primaryStage.setScene(myScene);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.show();
	}
	
	
	public void addEdge(int vertex1,int vertex2) {
		getVertex(vertex1).addAdjacency(getVertex(vertex2));
		getVertex(vertex2).addAdjacency(getVertex(vertex1));

	}
	

	public <T extends Node> void setDimentions(T btn,int centerX, int centerY) {
	
			 btn.setLayoutX(centerX);
			 btn.setLayoutY(centerY);
		
	}
	
	public Vertex getVertex(int vertexIndex) {
		
		try {
			
			return vertices.get(vertexIndex);
			
		}catch(IndexOutOfBoundsException e) {
			
			showError("One of the vertices you have entered has does not exist!");
			
			return null;
		}
		
		
	}
	
	
	private void showError(String message) {
		Alert myWarning = new Alert(AlertType.ERROR);
		myWarning.setContentText(message);
		myWarning.show();
	}
	
	
	private void showWarning(String warning) {
		
		Alert myWarning = new Alert(AlertType.WARNING);
		myWarning.setContentText(warning);
		myWarning.show();
		
	}
	
	
	
	
	
	

}
