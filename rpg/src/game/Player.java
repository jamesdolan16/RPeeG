package game;

import java.util.ArrayList;

public class Player {
	
	public String currentArea;
	public Vec2 pos;
	public ArrayList<Item> inventory;
	
	public Player() {
		pos = new Vec2();
		inventory = new ArrayList<Item>();
	}
	
	public void moveNorth() { pos.y--; }
	
	public void moveSouth() { pos.y++; }
	
	public void moveWest() { pos.x--; }
	
	public void moveEast() { pos.x++; }
	
	public boolean pickUp(Item i) {
		/*
		 * TODO:	- Add weight checking
		 */
		ScreenDrawer.print("You have picked up "+i.Quantity+" "+i.ID+"\n");
		if(inventoryContains(i.ID)) {
			inventory.get(inventoryItemIndexByID(i.ID)).Quantity += i.Quantity;
		}
		else {
			inventory.add(i);
		}
		return true;
	}
	
	public int inventoryItemIndexByID(String itemID) {
		int it = 0;
		for(Item i : inventory) {
			if(i.ID.equals(itemID)) {
				return it;
			}
			else {
				it++;
			}
		}
		
		return 0;
	}
	
	public boolean inventoryContains(String itemID) {
		for(Item i : inventory) {
			if(i.ID.equals(itemID)) {
				return true;
			}
		}
		return false;
	}
	
}
