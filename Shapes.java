package src;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Shapes {
	
	public Circle createCircle(int centerX,int centerY, int radius) {
		Circle circle = new Circle();
		circle.setCenterX(centerX);
		circle.setCenterY(centerY);
		circle.setRadius(radius);
		circle.setStroke(Color.valueOf("#000000"));
		circle.setStrokeWidth(2);
		circle.setFill(Color.TRANSPARENT);
		return circle;
	}
	
	public void setOnDrag(Circle circle1,int vertexNumber) {
		
		circle1.setOnDragDetected( dragEvent -> {

            Dragboard db = circle1.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString(Integer.toString(vertexNumber));
            db.setContent(content);
		});
		
		circle1.setOnMouseDragged(mouseDragged ->{
			mouseDragged.setDragDetect(true);
			
		});
		
	}

}
