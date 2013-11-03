package com.zyxum.lavaescape;

import java.util.ArrayList;

public class Dynamite {
	private boolean existance;
	private int x,y,id,fuse;
	private static int count;
	private static ArrayList<Dynamite> list = new ArrayList<Dynamite>();
	
	public Dynamite(int x, int y, int fuse){
		this.x=x;
		this.y=y;
		this.fuse=fuse;
		existance=true;
		id=++count;
		list.add(this);
	}
	
	public boolean exists(){
		return existance;
	}
	
	public void destroy(){
		x=-10000;
		y=-10000;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getFuse(){
		return fuse;
	}
	
	public void fuseTick(int height){
		if(exists()){
			if(fuse>0) fuse--;
			else{
				Game.shake=15;
				
				for(WallCube wc : WallCube.getInstances()){
					if(wc.getY()!=0 && wc.getY()!=height-1){
						if(wc.getX()+1==x && wc.getY()==y) wc.destroy();
						if(wc.getX()-1==x && wc.getY()==y) wc.destroy();
						if(wc.getX()==x && wc.getY()+1==y) wc.destroy();
						if(wc.getX()==x && wc.getY()-1==y) wc.destroy();
						if(wc.getX()+1==x && wc.getY()+1==y) wc.destroy();
						if(wc.getX()-1==x && wc.getY()-1==y) wc.destroy();
						if(wc.getX()-1==x && wc.getY()+1==y) wc.destroy();
						if(wc.getX()+1==x && wc.getY()-1==y) wc.destroy();
					}
				}
				
				for(Enemy e : Enemy.getInstances()){
					if(e.getY()!=0 && e.getY()!=height-1){
						if(e.getX()+1==x && e.getY()==y) e.destroy();
						if(e.getX()-1==x && e.getY()==y) e.destroy();
						if(e.getX()==x && e.getY()+1==y) e.destroy();
						if(e.getX()==x && e.getY()-1==y) e.destroy();
						if(e.getX()+1==x && e.getY()+1==y) e.destroy();
						if(e.getX()-1==x && e.getY()-1==y) e.destroy();
						if(e.getX()-1==x && e.getY()+1==y) e.destroy();
						if(e.getX()+1==x && e.getY()-1==y) e.destroy();
					}
				}
				
				if(Game.player.getX()+Game.camOffX+1==x && Game.player.getY()==y){
					Game.player.hp-=5;
					Gui.lastdmg=5;
					Gui.lastdmgtime=20;
					Gui.lastdmgx=(Game.player.getX()*32)+16;
					Gui.lastdmgy=(Game.player.getY()*32)+8;
				}
				if(Game.player.getX()+Game.camOffX-1==x && Game.player.getY()==y){
					Game.player.hp-=5;
					Gui.lastdmg=5;
					Gui.lastdmgtime=20;
					Gui.lastdmgx=(Game.player.getX()*32)-16;
					Gui.lastdmgy=(Game.player.getY()*32)+8;
				}
				if(Game.player.getX()+Game.camOffX==x && Game.player.getY()+1==y){
					Game.player.hp-=5;
					Gui.lastdmg=5;
					Gui.lastdmgtime=20;
					Gui.lastdmgx=(Game.player.getX()*32)+8;
					Gui.lastdmgy=(Game.player.getY()*32)+16;
				}
				if(Game.player.getX()+Game.camOffX==x && Game.player.getY()-1==y){
					Game.player.hp-=5;
					Gui.lastdmg=5;
					Gui.lastdmgtime=20;
					Gui.lastdmgx=(Game.player.getX()*32)+8;
					Gui.lastdmgy=(Game.player.getY()*32)-16;
				}
				existance=false;
				destroy();
			}
		}
	}
	
	public int getID(){
		return id;
	}
	
	public static int getCount(){
		return count;
	}
	
	public static ArrayList<Dynamite> getInstances(){
		return list;
	}
}
