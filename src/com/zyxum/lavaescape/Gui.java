package com.zyxum.lavaescape;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Gui {
	
	public static int lastdmg,lastdmgx,lastdmgy,lastdmgtime;
	
	public static void render(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, 64, 32);
		g.fillRect(0, 32, 84, 16);
		
		g.setColor(Color.black);
		g.drawRect(0, 0, 32, 32);
		g.drawRect(32, 0, 32, 32);
		g.drawRect(0, 32, 84, 16);
		g.drawString("HP: "+Game.player.hp+"/"+Game.player.maxhp, 1, 32);
		
		Game.img_dynamite.draw(8, 1, 0.5F);
		Game.img_key.draw(40, 1, 0.5F);
		
		if(lastdmgtime>0){
			g.setColor(Color.red);
			g.drawString("-"+lastdmg, lastdmgx, lastdmgy);
		}
		
		if(Game.bombs!=0) g.setColor(Color.black);
		else g.setColor(Color.red);
		g.drawString(""+Game.bombs, 8, 16);
		
		if(Game.keys!=0) g.setColor(Color.black);
		else g.setColor(Color.red);
		g.drawString(""+Game.keys, 40, 16);
	}
}
