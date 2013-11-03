package com.zyxum.lavaescape;

import java.util.ArrayList;

public class WallCube {
	private int x,y,id,imageid;
	public boolean exists;
	private static int count;
	private static ArrayList<WallCube> list = new ArrayList<WallCube>();
	
	public WallCube(int x, int y, int imageid){
		this.imageid=imageid;
		this.x=x;
		this.y=y;
		exists=true;
		id=++count;
		list.add(this);
	}
	
	public int getImageId(){
		return imageid;
	}
	
	public void destroy(){
		exists=false;
		x=-10000;
		y=-10000;
	}
	
	public int getID(){
		return id;
	}
	
	public static int getCount(){
		return count;
	}
	
	public static ArrayList<WallCube> getInstances(){
		return list;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
