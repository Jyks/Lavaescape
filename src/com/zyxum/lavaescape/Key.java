package com.zyxum.lavaescape;

import java.util.ArrayList;

public class Key {
	private int x,y,id;
	public boolean exists;
	private static int count;
	private static ArrayList<Key> list = new ArrayList<Key>();
	
	public Key(int x, int y){
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
	
	public int getId(){
		return id;
	}
	
	public static int getCount(){
		return count;
	}
	
	public static ArrayList<Key> getInstances(){
		return list;
	}
}
