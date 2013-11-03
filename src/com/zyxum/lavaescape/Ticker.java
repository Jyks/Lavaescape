package com.zyxum.lavaescape;

import org.newdawn.slick.GameContainer;

public class Ticker {
	private static int startTick=0;
	
	public static void tick(int delta){
		if(Game.player.hp<=0){
			Game.endTransition=true;
			Game.player.hp=0;
		}
		
		if(Game.startTransition){
			if(Game.zeropoint_x>0) Game.zeropoint_x-=.1*delta;
			else{
				Game.zeropoint_x=0;
				Game.startTransition=false;
			}
			if(startTick>10){
				nextTurn(Game.public_gc);
				startTick=0;
			}else startTick+=.1*delta;
		}else if(Game.endTransition){
			if(startTick>10){
				nextTurn(Game.public_gc);
				startTick=0;
			}else startTick+=.1*delta;
		}
		
		if(Gui.lastdmgtime>0) Gui.lastdmgtime-=.1*delta;
		else Gui.lastdmgtime=0;
		
		for(Enemy e : Enemy.getInstances()){
			if(e.getX()==Game.player.getX()+Game.camOffX && e.getY()==Game.player.getY()){
				e.destroy();
				Game.shake=2;
				Game.player.hp-=e.getDmg();
				Gui.lastdmg=e.getDmg();
				Gui.lastdmgtime=20;
				Gui.lastdmgx=(Game.player.getX()*32)+16;
				Gui.lastdmgy=(Game.player.getY()*32)+8;
			}
		}
		
		for(Key k : Key.getInstances()){
			if(k.getX()==Game.player.getX()+Game.camOffX && k.getY()==Game.player.getY()){
				k.destroy();
				Game.keys++;
			}
		}
		
		for(Bomb b : Bomb.getInstances()){
			if(b.getX()==Game.player.getX()+Game.camOffX && b.getY()==Game.player.getY()){
				b.destroy();
				Game.bombs++;
			}
		}
	}
	
	public static void nextTurn(GameContainer gc){
		int WIDTH = 2000;
		int HEIGHT = gc.getHeight()/32;

		for(Dynamite d : Dynamite.getInstances()) if(d.exists()) d.fuseTick(HEIGHT);
		
		byte[][] tempLavaMap=new byte[WIDTH+2][HEIGHT+2];
		
		for(Lava l : Lava.getInstances()) if(l.exists) tempLavaMap[l.getX()/32][l.getY()/32]=1;
		
		for(int x=1;x<=WIDTH;x++){
			for(int y=1;y<=HEIGHT;y++){
				if(tempLavaMap[x][y]==1 && (Game.random.nextBoolean() || Game.endTransition || Game.startTransition)){
					if(tempLavaMap[x+1][y]==0 && (Game.random.nextBoolean() || x<Game.camOffX)) new Lava((x+1)*32, y*32);
					if(tempLavaMap[x-1][y]==0 && (Game.random.nextBoolean() || x<Game.camOffX)) new Lava((x-1)*32, y*32);
					if(tempLavaMap[x][y+1]==0 && (Game.random.nextBoolean() || x<Game.camOffX)) new Lava(x*32, (y+1)*32);
					if(tempLavaMap[x][y-1]==0 && (Game.random.nextBoolean() || x<Game.camOffX)) new Lava(x*32, (y-1)*32);
				}
			}
		}
		
		for(Lava l : Lava.getInstances()){
			if(l.getX()/32==Game.player.getX()+Game.camOffX && l.getY()/32==Game.player.getY() && !Game.endTransition){
				Game.shake=2;
				Game.player.hp-=10;
				Gui.lastdmg=10;
				Gui.lastdmgtime=20;
				Gui.lastdmgx=(Game.player.getX()*32)+16;
				Gui.lastdmgy=(Game.player.getY()*32)+8;
			}
		}
	}
}