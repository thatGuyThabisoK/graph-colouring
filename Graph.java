package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class graph extends Application{

	Button generate = new Button("Generate");
	
	
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
			//	System.out.println("event triggered -> "+droppedBoard.getString());
				System.out.println("The mouse coordinates -> "+droppedEvent.getSceneX()+" , "+droppedEvent.getSceneY());
				droppedEvent.setDropCompleted(true);
			}else {
				droppedEvent.setDropCompleted(false);
			}
			droppedEvent.consume();
		});
		
		generate.setLayoutX(400);
		generate.setLayoutY(400);
		generate.setOnAction(actionEvent -> {
			
			Circle circle1 = new Shapes().createCircle(100, 100, 40);
			
			circle1.setOnDragDetected( dragEvent -> {

	            Dragboard db = circle1.startDragAndDrop(TransferMode.ANY);

	            ClipboardContent content = new ClipboardContent();
	            content.putString("Circle source text");
	            db.setContent(content);
			});
			
			circle1.setOnMouseDragged(mouseDragged ->{
				mouseDragged.setDragDetect(true);
				
			});
			
			pane.getChildren().add(circle1);
		});
		
		
		pane.getChildren().add(generate);
		Scene myScene = new Scene(pane,900,500,true);
		
		primaryStage.setScene(myScene);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.show();
	}
	

	
	

}
