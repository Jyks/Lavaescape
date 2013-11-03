package com.zyxum.lavaescape;

public class Player {
	private int x,y;
	public int hp,maxhp;
	
	public Player(int x, int y, int hp, int maxhp){
		this.x=x;
		this.y=y;
		this.hp=hp;
		this.maxhp=maxhp;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int num){
		x+=num;
	}
	
	public void setY(int num){
		y+=num;
	}
}
