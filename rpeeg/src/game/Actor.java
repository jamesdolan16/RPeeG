package game;

public class Actor {
	public Vec2 pos;
	public String ID;
	
	public Actor(ScreenDrawer out, String id, Vec2 pos) {
		ID = id;
		this.pos.x = pos.x; this.pos.y = pos.y;
	}
}
