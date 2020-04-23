/*
 * 	TODO:	- Work on player command system
 * 			- Actual real time-edness
 * 			- Rework everything to use new Vec2 object
 * 			- Rework positioning and maps to allow mulitple things in same cell
 * 			- Add proper command engine
 */
package game;

public class GameRuntime {
	
	ScreenDrawer out;
	World world;
	Player player;
	
	
	private Player testPlayer() {
		Player toRet = new Player();
		toRet.currentArea = "main_room";
		toRet.pos.x = 1;
		toRet.pos.y = 1;
		
		return toRet;
	}
	
	public GameRuntime() {
		
		String currentEvent = "menu";
		boolean runtime = true;
		out = new ScreenDrawer();
		player = testPlayer();
		
		while(runtime) {
			switch(currentEvent) {
			case "menu":
				currentEvent = out.drawMenu();
				break;
				
			case "closeApplication":
				ScreenDrawer.print("See ya!\n");
				runtime = false;
				break;
				
			case "loadWorld":
				ScreenDrawer.print("Loading...\n");
				world = new World();					//Load World
				ScreenDrawer.print(world.toString());
				currentEvent = "gameLoop";
				break;
			
			case "gameLoop":
				GameLoop();
				currentEvent = "menu";
				break;
			}
		}
		
	}
	
	public void GameLoop() {
		
		boolean gameloop = true;
		String playerCommand;
		
		while(gameloop) {
			Update();
			out.drawScreen(this.player, this.world);
			
			playerCommand = ScreenDrawer.input().toLowerCase();
			
			switch(playerCommand) {						//Just for now
			case "w":
				if(!WallCollision(0,-1)) {
					player.moveNorth();
				}
				break;
			case "s":
				if(!WallCollision(0,1)) {
					player.moveSouth();
				}
				break;
			case "a":
				if(!WallCollision(-1,0)) {
					player.moveWest();
				}
				break;
			case "d":
				if(!WallCollision(1,0)) {
					player.moveEast();
				}
				break;
			case "p":
				//Dont worry about weight just yet
				try {
					player.pickUp(world.getAreaByID(player.currentArea).getItemByPos(player.pos));
					world.getAreaByID(player.currentArea).removeItem(player.pos);
				}catch(NullPointerException e) {
					ScreenDrawer.print("Nothing to pick up!\n");
				}
				break;
			case "i":	//Inventory
				out.drawInventory(player);
				break;
			case "quit":
				gameloop = false;
				break;
			}
		}
		
	}
	
	public void Update() {
		
		//Door stuff
		DoorCollision(player.pos);
		
	}
	
	private void DoorCollision(Vec2 pos) {
		Area currentArea = world.getAreaByID(player.currentArea);
		Door currentDoor = currentArea.getDoorByPos(pos);
		if(currentDoor != null) {	//Go through door
			player.currentArea = currentDoor.DestAreaID;
			player.pos.x = currentDoor.DestCoord.x;
			player.pos.y = currentDoor.DestCoord.y;
		}
	}
	
	private boolean WallCollision(int dx, int dy) {
		Vec2 dpos = new Vec2();
		dpos.x = this.player.pos.x+dx; dpos.y = this.player.pos.y+dy;
		char edge = this.world.getAreaByID(player.currentArea).Map[dpos.y][dpos.x];
		if(edge == '#') {
			return true;
		}
		else {
			return false;
		}
	}
}
