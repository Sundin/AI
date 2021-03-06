package group3.world.definitions;

public enum Shape {
	BALL("ball"), BOX("box"), BRICK("brick"), FLOOR("floor"), PYRAMID("pyramid"), TABLE("table"), PLANK("plank"), UNSPECIFIED("anyform");
	
	private String shapeStringDescription;
	
	private Shape(String shapeStringDescription) {
		this.shapeStringDescription = shapeStringDescription; 
	}
	
	public static Shape getShapeValueFromString(String shapeString) {
		if(shapeString != null) {
			for(Shape shape : Shape.values()) {
				if(shapeString.equalsIgnoreCase(shape.shapeStringDescription)) {
					return shape;
				}
			}
		}
		throw new IllegalArgumentException("No shape with description " + shapeString + " found");
	}
}
