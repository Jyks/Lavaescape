package com.zyxum.lavaescape;

import java.util.ArrayList;

public class Lava {
	private int x,y,id;
	public boolean exists;
	private static int count;
	private static ArrayList<Lava> list = new ArrayList<Lava>();
	
	public Lava(int x, int y){
		this.x=x;
		this.y=y;
		exists=true;
		id=++count;
		list.add(this);
	}
	
	public void destroy(){
		exists=false;
		x=-10000;
		y=-10000;
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
	
	public static int getCount(){
		return count;
	}
	
	public static ArrayList<Lava> getInstances(){
		return list;
	}
}
