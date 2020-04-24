/*
 * 	TODO:	- Work on player command system
 * 			- Actual real time-edness
 * 			- Rework everything to use new Vec2 object
 * 			- Rework positioning and maps to allow mulitple things in same cell
 * 			- Add proper command engine
 */
package game;

import java.util.Timer;
import java.util.TimerTask;

public class GameRuntime {
	
	ScreenDrawer out;
	World world;
	Player player;
	ViewKeyListener viewKeys;

	private Player testPlayer() {
		Player toRet = new Player(out);
		toRet.currentArea = "main_room";
		toRet.pos.x = 1;
		toRet.pos.y = 1;
		
		return toRet;
	}
	
	public GameRuntime() {
		
		String currentEvent = "menu";
		boolean runtime = true;
		out = new ScreenDrawer();
		viewKeys = new ViewKeyListener(out);
		player = testPlayer();
		
		while(runtime) {
			switch(currentEvent) {
			case "menu":
				currentEvent = "loadWorld";
				break;
				
			case "closeApplication":
				runtime = false;
				break;
				
			case "loadWorld":
				out.print("Loading...\n");
				world = new World(out);					//Load World
				currentEvent = "run";
				break;

			case "run":
				Run();
				currentEvent = "closeApplication";
				break;
			}
		}
		
	}
	
	public void Run() {
		Boolean run = true;
		String playerCommand;
		Timer timer = new Timer(true);
		GameLoop gl = new GameLoop(out, world, player, viewKeys, run);
		timer.scheduleAtFixedRate(gl, 0, 500);
		while(run){
			//Just wait
		}
		out.guiFrame.dispose();
	}

}
