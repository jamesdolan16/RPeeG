package game;

import java.util.ArrayList;

public class Area {
	
	public String ID;
	public char[][] Map;
	public ArrayList<String> MapList = new ArrayList<String>();
	public ArrayList<Door> Doors = new ArrayList<Door>();
	public ArrayList<Item> Items = new ArrayList<Item>();
	public ArrayList<Actor> Actors = new ArrayList<Actor>();
	
	public Area() {
		
	}
	
	public void LoadMap() {
		Map = new char[MapList.size()][MapList.get(0).length()];
		for(int y = 0; y<Map.length; y++) {
			for(int x = 0; x<Map[y].length; x++) {
				Map[y][x] = MapList.get(y).charAt(x);
			}
		}
	}
	
	public void removeItem(Vec2 pos) {
		Items.remove(getItemIndexByPos(pos));
		Map[pos.y][pos.x] = ' ';
	}
	
	public Door getDoorByID(String doorID) {
		for(Door d : Doors) {
			if(d.ID.equals(doorID)) {
				return d;
			}
		}
		return null;
	}
	
	public Door getDoorByPos(Vec2 pos) {
		for(Door d : Doors) {
			if(d.pos.x == pos.x && d.pos.y == pos.y) {
				return d;
			}
		}
		return null;
	}
	
	public int getItemIndexByPos(Vec2 pos) {
		int it = 0;
		for(Item i : Items) {
			if(i.pos.x == pos.x && i.pos.y == pos.y) {
				return it;
			}
			it++;
		}
		return 0;
	}
	
	public Item getItemByPos(Vec2 pos) {
		for(Item i : Items) {
			if(i.pos.x == pos.x && i.pos.y == pos.y) {
				return i;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		String toRet = "";
		toRet += "ID: "+this.ID+"\n";
		for(char[] line : Map) {
			for(char cell : line) {
				toRet += cell;
			}
			toRet += "\n";
		}
		toRet += "Doors: ";
		for(Door d : this.Doors) {
			toRet += d.ID+" ";
		}
		toRet += "\nItems: ";
		for(Item i : this.Items) {
			toRet += i.ID+" ";
		}
		toRet += "\nActors: ";
		for(Actor a : this.Actors) {
			toRet += a.ID+" ";
		}
		toRet += "\n";
		
		return toRet;
	}
}
