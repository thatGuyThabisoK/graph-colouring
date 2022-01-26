package src;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class graph extends Application{

	Button generate = new Button("Generate");
	ArrayList<Vertex> vertices = new ArrayList<>();
	TextField edgeField = new TextField();
	Button enterEdge = new Button("Enter edge");
	
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
				
				vertices.get(Integer.parseInt(droppedBoard.getString())).circleNode.setCenterX(droppedEvent.getSceneX());
				vertices.get(Integer.parseInt(droppedBoard.getString())).circleNode.setCenterY(droppedEvent.getSceneY());
				
				droppedEvent.setDropCompleted(true);
			}else {
				droppedEvent.setDropCompleted(false);
			}
			droppedEvent.consume();
		});
		
		generate.setLayoutX(200);
		generate.setLayoutY(400);
		
		enterEdge.setLayoutX(550);
		enterEdge.setLayoutY(400);
		
		edgeField.setLayoutX(400);
		edgeField.setLayoutY(400);
		edgeField.setPromptText("Enter as such e.g 2,3 ");
		
		generate.setOnAction(actionEvent -> {
			
			//Each time the generate button is pressed
			//It should create a new vertex object contain a circle object that will be added to the scene
			Vertex circleChild = new Vertex(vertices.size());
			vertices.add(circleChild);
			pane.getChildren().add(circleChild.circleNode);
		});
		
		
		enterEdge.setOnAction(actionEvent -> {
			System.out.println(edgeField.getText());
			edgeField.setText("");
		});
		
		pane.getChildren().add(generate);
		pane.getChildren().add(edgeField);
		pane.getChildren().add(enterEdge);
		Scene myScene = new Scene(pane,900,500,true);
		
		primaryStage.setScene(myScene);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.show();
	}
	

	
	

}
