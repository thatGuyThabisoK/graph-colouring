package src;

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

}
