package game;

public class Door {
	
	public String ID;
	public Vec2 pos = new Vec2();
	public String DestAreaID;
	public Vec2 DestCoord = new Vec2();
	
	public Door() {

	}
	
	public Door(ScreenDrawer out, String id, Vec2 pos) {
		ID = id;
		this.pos.x = pos.x; this.pos.y = pos.y;
		Door target = FileHandler.LoadDoor(out, id);
		DestAreaID = target.DestAreaID;
		DestCoord = target.DestCoord; 
	}
	
	@Override
	public String toString() {
		String toRet = "";
		toRet += "ID: "+ID;
		toRet += "DestAreaID: "+DestAreaID;
		toRet += "DestCoord: "+DestCoord;
		
		return toRet;
	}
}
