package game;

import javax.swing.*;
import java.util.Scanner;

public class ScreenDrawer {
	
	private static Scanner sysIn = new Scanner(System.in);

	GraphicInterface gui;
	JFrame guiFrame;

	public ScreenDrawer(){
		gui = new GraphicInterface();
		guiFrame = new JFrame("RPG");
		guiFrame.setContentPane(gui.MainPanel());
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.pack();
		guiFrame.setVisible(true);


	}

	public void print(String msg){
		gui.PromptPane().setText(msg);
	}

	public void printCell(char c){
		gui.ViewPane().setText(gui.ViewPane().getText()+String.valueOf(c));
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
		printCell('0');
	}
	
	public void printError(String msg) {
		print("ERROR: "+msg+"\n");
	}

	public void drawScreen(Player player, World world) {
		//Clear Screen
		gui.ViewPane().setText("");
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
			printCell('\n');
		}
		
		//Prompt shit, item here and such
		if(area.getItemByPos(player.pos) != null) {
			print("On the ground: "+area.getItemByPos(player.pos).ID+"\n");
		}
	}

	public void drawInventory(Player player){
		String toPrint = "";
		toPrint += "INVENTORY\n";
		for(Item i : player.inventory) {
			toPrint += i.ID+" "+i.Quantity+" "+i.Damage+" "+i.Weight+"\n";
		}
		print(toPrint);
	}

}
