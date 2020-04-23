package game;

import java.util.ArrayList;

public class World {
	
	private ArrayList<Area> Areas = new ArrayList<Area>();
	
	public World() {
		LoadWorld();
	}
	
	public void LoadWorld() {
		//Load Areas
		ArrayList<String> areasList = FileHandler.LoadAreaList();
		for(String areaID : areasList) {
			Area curArea = FileHandler.LoadArea(areaID); curArea.LoadMap();
			Areas.add(curArea);
		}
		
	}
	
	public Area getAreaByID(String ID) {
		for(Area a : this.Areas) {
			if(a.ID.equals(ID)) {
				return a;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		String toRet = "";
		for(Area a : Areas) {
			toRet += a.toString()+"\n";
		}
		return toRet;
	}
}
