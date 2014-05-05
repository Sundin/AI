package group3.planning;
import group3.definitions.ObjectInWorld;
import group3.definitions.RelativePosition;
import group3.definitions.Rules;
import group3.definitions.World;

public class Goal extends CompositeGoal{
	
	private ObjectInWorld objectToMove;
	private RelativePosition relativePosition;
	private ObjectInWorld otherObject;
	private String outputString = "";
	
	public Goal() {
		
	}
	
	public Goal(ObjectInWorld objectToMove, RelativePosition relativePosition, ObjectInWorld otherObject) {
		this.objectToMove = objectToMove;
		this.relativePosition = relativePosition;
		this.otherObject = otherObject;
	}
	
	public Goal(ObjectInWorld objectToMove, RelativePosition relativePosition) {
		this.objectToMove = objectToMove;
		this.relativePosition = relativePosition;
	}
	
	public ObjectInWorld GetTargetToMove() {
		return this.objectToMove;
	}
	
	public void setString(String s) {
		this.outputString = s;
	}
	
	/**
	 * Goals must have a string, surrounded with "fnutts".
	 * This method is called by the method put in JSONObject.
	 */
	@Override
	public String toString() {
		return "\""+ outputString +"\"";
	}

	/**
	 * Return true if the goal is fulfilled in the world given as parameter
	 */
	public boolean isFulfilled(World world) {
		if (relativePosition.equals(RelativePosition.HOLDING) && world.getHoldingObject() != null) {
			return world.getHoldingObject().equals(objectToMove);
		} else if (relativePosition.equals(RelativePosition.ONFLOOR)) {
			return world.isOnFloor(objectToMove);
		} else if (relativePosition.equals(RelativePosition.ONTOP)) {
			return world.isOnTopOf(objectToMove, otherObject);
		} else if (relativePosition.equals(RelativePosition.INSIDE)) {
			return world.isInside(objectToMove, otherObject);
		} else if (relativePosition.equals(RelativePosition.ABOVE)) {
			return world.isAbove(objectToMove, otherObject);
		} else if (relativePosition.equals(RelativePosition.UNDER)) {
			return world.isUnder(objectToMove, otherObject);
		} else if (relativePosition.equals(RelativePosition.BESIDE)) {
			return world.isBeside(objectToMove, otherObject);
		} else {
			return false;
		}
	}

	/**
	 * Returns true if this goal is possible to fulfill according to the rules of the world.
	 * @return
	 */
	public boolean isAllowed() {
		return Rules.allowedMove(objectToMove, relativePosition, otherObject);
	}
}
