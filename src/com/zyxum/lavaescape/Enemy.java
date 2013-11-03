package com.zyxum.lavaescape;

import java.util.ArrayList;

public class Enemy {
	private int x,y,id,dmg;
	public boolean exists;
	private static int count;
	private static ArrayList<Enemy> list=new ArrayList<Enemy>();
	
	public Enemy(int x, int y, int dmg){
		this.x=x;
		this.y=y;
		this.dmg=dmg;
		exists=true;
		id=++count;
		list.add(this);
	}
	
	public void destroy(){
		x=-10000;
		y=-10000;
		exists=false;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getID(){
		return id;
	}
	
	public int getDmg(){
		return dmg;
	}
	
	public static int getCount(){
		return count;
	}
	
	public static ArrayList<Enemy> getInstances(){
		return list;
	}
}
