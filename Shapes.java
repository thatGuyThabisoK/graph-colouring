package src;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Shapes {
	
	public Circle createCircle(double centerX,double centerY, int radius) {
		Circle circle = new Circle();
		circle.setCenterX(centerX);
		circle.setCenterY(centerY);
		circle.setRadius(radius);
		circle.setStroke(Color.valueOf("#000000"));
		circle.setStrokeWidth(2);
		circle.setFill(Color.WHITE);
		return circle;
	}
	
	public Line createEdge(int startX,int startY, int endX, int endY) {
		 return new Line(startX,startY,endX,endY);
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
	
	public void getDirection(Line line) {
		
		double diffX = line.getStartX() - line.getEndX();
		double diffY = line.getStartY() - line.getEndY();
	
		quad(diffY,diffX);	
		
	}
	
	
	
	
	public int setLineLength(int center,int radius) {
		
		
		
		
		return 0;
	}
	
	private void quad(double diffY, double diffX) {
		
		if(diffY > 0) {
			
			System.out.print("lower part ");
			x_Sector(diffX);
			
		}else if(diffY < 0) {
			System.out.print("upper part ");
			x_Sector(diffX);
		}
		
	}
	
	private void x_Sector(double diffX) {
		
		if(diffX < 0) {
			System.out.println("right sector");				
		}else if(diffX > 0 ) {
			System.out.println("left sector");	
		}else {
			
			System.out.println("lie on the same point");
		}
		
	}
	

}
