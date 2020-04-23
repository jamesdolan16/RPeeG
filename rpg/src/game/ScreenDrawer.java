package game;

import java.util.Scanner;

public class ScreenDrawer {
	
	private static Scanner sysIn = new Scanner(System.in);
	
	public ScreenDrawer() {

	}
	
	public static void print(String msg) {
		System.out.print(msg);
	}
	
	public static void printCell(char c) {
		print(String.valueOf(c));
	}
	
	public void drawCell(Area currentArea, int x, int y) {
		if(currentArea.getDoorByPos(new Vec2(x, y)) != null) {
			printCell('H');
		}
		
		else if(currentArea.getItemByPos(new Vec2(x, y)) != null) {
			printCell('*');
		}
		
		else {
			printCell(currentArea.Map[y][x]);
		}
		
	}
	
	public void drawPlayer() {
		print("0");
	}
	
	public static void printError(String msg) {
		print("ERROR: "+msg+"\n");
	}
	
	public static String input() {
		System.out.print(">");
		String toRet = sysIn.nextLine();
		return toRet;
	}
	
	public String drawMenu() {
		print("MENU\n1. Play\n2. Quit\n");
		while(true) {
			String choice = input();
			switch(choice) {
			case "1":
				return "loadWorld";
			case "2":
				return "closeApplication";
			default:
				printError("Invalid Option.");
			}
		}
	}
	
	public void drawScreen(Player player, World world) {
		//Draw area
		Area area = world.getAreaByID(player.currentArea);
		for (int y = 0; y < area.Map.length; y++) {
			for (int x = 0; x < area.Map[y].length; x++) {
				if(y == player.pos.y && x == player.pos.x)
					drawPlayer();
				else
				{
					
					drawCell(area, x,y);
				}
			}
			print("\n");
		}
		
		//Prompt shit, item here and such
		if(area.getItemByPos(player.pos) != null) {
			print("On the ground: "+area.getItemByPos(player.pos).ID+"\n");
		}
	}
	
	public void drawInventory(Player player) {
		String format = "%-18s %3d %3ddmg %3fkg\n";			//Format float refuses to work properly!!!
		ScreenDrawer.print("- Inventory -\n");
		for(Item i : player.inventory) {
			System.out.format(format, i.ID, i.Quantity, i.Damage, i.Weight);
		}
	}
}
