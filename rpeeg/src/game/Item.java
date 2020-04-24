/*
 * TODO:	- Naming rework
 * 
 */

package game;

public class Item {
	
	public String ID;
	public Double Weight;
	public int Type;
	public int SubType;
	public Vec2 pos = new Vec2();
	public int Quantity;
	public int Damage;
	
	public Item() {
	}
	
	public Item(ScreenDrawer out, String id, Vec2 pos) {
		ID = id;
		this.pos.x = pos.x; this.pos.y = pos.y;
		Item target = FileHandler.LoadItem(out, id);
		Weight = target.Weight;
		Type = target.Type;
		SubType = target.SubType;
		Quantity = target.Quantity;
		Damage = target.Damage;
	}
	
	@Override
	public String toString() {
		return "ID: "+ID+" Weight: "+Weight+" Type: "+Type+" SubType: "+SubType+" pos: "+pos+" Quantity: "+Quantity+" Damage: "+Damage;
	}
}
