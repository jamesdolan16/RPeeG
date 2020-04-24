/*
 * 	TODO: 	- Make generic list loader to stop reusing code 					[/]
 *			- Add proper exception handling for loaded values					[]
 * 			- Implement a logger that logs information about file io			[]
 * 			- Objectify items													[]
 */

package game;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class FileHandler {
	
	/*public static void main(String[] args) {
		ArrayList<String> areas = LoadAreaList();
		for(String s : areas) {
			out.print(s+"\n");
		}
		Area a = LoadArea(areas.get(1));
		out.print(a.toString());
	}*/
	
	@SuppressWarnings("rawtypes")
	public static ArrayList<String> LoadAreaList(ScreenDrawer out) {
		String areaListPath = "src/files/areas/areaList.json";
		ArrayList<String> toRet = new ArrayList<String>();
		Object parseObj;
		try {
			parseObj = new JSONParser().parse(new FileReader(areaListPath));
		}catch(Exception e) {
			out.printError("Unable to open "+areaListPath+" for reading.\n");
			return null;
		}
		JSONObject jsonObj = (JSONObject) parseObj;
		JSONArray areaList = (JSONArray) jsonObj.get("areaList");
		Iterator itr = areaList.iterator();
		
		while(itr.hasNext()) {
			toRet.add((String) itr.next());
		}
		return toRet;
	}
	
	public static Area LoadElementList(ScreenDrawer out, String listName, Area pLoaded, JSONObject jsonObj) {
		Area toRet = pLoaded;
		//ScreenDrawer.print(toRet.ID+"\n");
		try {
			JSONArray elementsJson = (JSONArray) jsonObj.get(listName);
			Iterator itr = elementsJson.iterator();
			JSONObject element;
			JSONArray posJson;
			int i = 0;
			String id;
			Vec2 pos = new Vec2();
			while(itr.hasNext()) {
				element = (JSONObject) itr.next();
				id = (String)element.get("id");
				posJson = (JSONArray) element.get("pos");
				pos.x = (int)(long)posJson.get(0);
				pos.y = (int)(long)posJson.get(1);
				
				switch(listName) {
				case "doors":
					toRet.Doors.add(new Door(out, id, pos));
					break;
				case "items":
					toRet.Items.add(new Item(out, id, pos));
					break;
				case "actors":
					toRet.Actors.add(new Actor(out, id, pos));
				}
			}
			return toRet;
		}catch(Exception e) {
			out.printError("Failed to load element list "+listName+"\n"+e);
			return pLoaded;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static Area LoadArea(ScreenDrawer out, String areaID) {
		String path = "src/files/areas/"+areaID+".json";
		Area toRet = new Area();
		Object parseObj;
		try {
			parseObj = new JSONParser().parse(new FileReader(path));
		}catch(Exception e) {
			out.printError("Unable to open area file "+path+" for reading.\n");
			return null;
		}
		JSONObject jsonObj = (JSONObject) parseObj;
		
		//Load ID
		toRet.ID = areaID; 	//Not sure about this one!
		
		//Load Map
		JSONArray mapJson = (JSONArray) jsonObj.get("map");
		Iterator itr = mapJson.iterator(); 
		while(itr.hasNext()) {
			toRet.MapList.add(((String) itr.next()));
		}
		
		toRet.LoadMap();
	
		toRet = LoadElementList(out,"doors", toRet, jsonObj);
		toRet = LoadElementList(out,"items", toRet, jsonObj);
		toRet = LoadElementList(out,"actors", toRet, jsonObj);
		
		return toRet;
	}
	
	public static Vec2 Scan(ArrayList<String> map, char c, int i) {
		Vec2 toRet = new Vec2();
		int o = 0;
		for (int y = 0; y < map.size(); y++) {
			for(int x = 0; x < map.get(y).length(); x++) {
				if(map.get(y).charAt(x) == c) {
					if(o == i) {
						toRet.x = x; toRet.y = y;
						return toRet;
					}
					else {
						o++;
					}
				}
			}
		}
		return null;
	}
	
	public static Door LoadDoor(ScreenDrawer out, String doorID) {
		String path = "src/files/doors/"+doorID+".json";
		Door toRet = new Door();
		Object parseObj;
		try {
			parseObj = new JSONParser().parse(new FileReader(path));
		}catch(Exception e) {
			out.printError("Unable to open door file "+path+" for reading.\n");
			return null;
		}
		JSONObject jsonObj = (JSONObject) parseObj;
		
		toRet.ID = doorID;
		toRet.DestAreaID = (String) jsonObj.get("destAreaID");
		
		JSONArray destCoord = (JSONArray) jsonObj.get("destCoord");
		long x = (long) destCoord.get(0);
		long y = (long) destCoord.get(1);
		toRet.DestCoord.x = (int) x; 
		toRet.DestCoord.y = (int) y;
		
		return toRet;
	}
	
	public static Item LoadItem(ScreenDrawer out, String itemID) {
		String path = "src/files/items/"+itemID+".json";
		Item toRet = new Item();
		Object parseObj;
		try {
			parseObj = new JSONParser().parse(new FileReader(path));
		}catch(Exception e) {
			out.printError("Unable to open item file "+path+" for reading.\n");
			return null;
		}
		JSONObject jsonObj = (JSONObject) parseObj;
		
		toRet.ID = itemID;
		try { toRet.Weight = ((Number)jsonObj.get("weight")).doubleValue(); } catch(NullPointerException e) { toRet.Weight = 0.0;}
		try { toRet.Type = (int)(long)jsonObj.get("type"); } catch(NullPointerException e) { toRet.Type = 0;}
		try { toRet.Quantity = (int)(long)jsonObj.get("quantity"); } catch(NullPointerException e) { toRet.Quantity = 1;}
		try { toRet.SubType = (int)(long)jsonObj.get("subType"); } catch(NullPointerException e) { toRet.SubType = 0;}
		try { toRet.Damage = (int)(long)jsonObj.get("damage"); } catch(NullPointerException e) { toRet.Damage = 0;}
	
		
		return toRet;
	}
	
}
