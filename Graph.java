package src;

import java.util.ArrayList;
import java.util.Random;

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
	Button removeNode = new Button("Remove node");
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
				
				getVertex(nodeAt).updateLink(droppedEvent.getSceneX(), droppedEvent.getSceneY());
				
				
				
				
				
				droppedEvent.setDropCompleted(true);
			}else {
				droppedEvent.setDropCompleted(false);
			}
			droppedEvent.consume();
		});
		
		
		setDimentions(generate,200,400);
		setDimentions(removeNode,280,400);
		setDimentions(enterEdge,550, 400);
		setDimentions(edgeField,400, 400);
		
		
		
		edgeField.setPromptText("Enter as such e.g 2,3 ");
		
		generate.setOnAction(actionEvent -> {
			
			//Each time the generate button is pressed
			//It should create a new vertex object containing a circle object that will be added to the scene
			Vertex circleChild = new Vertex(vertices.size());
			vertices.add(circleChild);
			
			pane.getChildren().add(circleChild.myCircleNode());
		});
		
		
		enterEdge.setOnAction(actionEvent -> {
			System.out.println(pane.getC);
			String edges = edgeField.getText();
			if(!edges.isEmpty() || !edges.isBlank()) {
					String nodes[] = edges.split(",");
					
					int nodeAt1 = Integer.parseInt(nodes[0]);
					int nodeAt2 = Integer.parseInt(nodes[1]);
					
					addEdge(nodeAt1,nodeAt2);
					
					
					Line edgeLine = new Line();
					
					edgeLine.setStartX(getVertex(nodeAt1).getCenterX());
					edgeLine.setStartY(getVertex(nodeAt1).getCenterY());
					
					edgeLine.setEndX(getVertex(nodeAt2).getCenterX());
					edgeLine.setEndY(getVertex(nodeAt2).getCenterY());
					
					getVertex(nodeAt1).addLink("start".concat(Integer.toString(new Random().nextInt())),edgeLine);
					getVertex(nodeAt2).addLink("end".concat(Integer.toString(nodeAt2)),edgeLine);
					//
					pane.getChildren().add(edgeLine);

					
					edgeField.setText("");
			}else {
				
				Alert error = new Alert(AlertType.ERROR);
				error.setContentText("Please check that you have entered, if at all, the edges in the correct format e.g 4,5");
				error.show();
				
				
			}
			
			
			
			
		});
		
		removeNode.setOnAction(remove->{
			if(showAlert) {
				Alert warning = new Alert(AlertType.WARNING);
				warning.setContentText("The last vertex to be added will be removed along with any edges associated with it");
				warning.show();
				
				showAlert = false;
			}
			
			
			// TODO add the code to remove last vertex and all its associated edges here
			
			
		});
		
		
		
		pane.getChildren().add(generate);
		pane.getChildren().add(edgeField);
		pane.getChildren().add(enterEdge);
		pane.getChildren().add(removeNode);
		Scene myScene = new Scene(pane,900,500,true);
		
		primaryStage.setScene(myScene);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.show();
	}
	
	
	
	public void addEdge(int vertex1,int vertex2) {
		getVertex(vertex1).addAdjacency(getVertex(vertex2));
		getVertex(vertex2).addAdjacency(getVertex(vertex1));

	}
	

	public void setDimentions(Object btn,int centerX, int centerY) {
	
			((Node) btn).setLayoutX(centerX);
			((Node) btn).setLayoutY(centerY);
		
	}
	
	public Vertex getVertex(int vertexIndex) {
		return vertices.get(vertexIndex);
	}

}
