package com.zyxum.lavaescape;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class KeyCommand {
	
	public static int dir=1;
	
	public static void keyPressExecutor(GameContainer gc, int key){
		//key press commands
		if(key==Input.KEY_ESCAPE) gc.exit();
		else if(key==Input.KEY_SPACE && (Game.bombs>0) && !Game.startTransition && !Game.endTransition){
			new Dynamite(Game.player.getX()+Game.camOffX, Game.player.getY(), 2);
			Game.bombs--;
		}else if(key==Input.KEY_SPACE && Game.startTransition){
			Game.startTransition=false;
			for(int i=0;i<Game.zeropoint_x;i+=10) Ticker.nextTurn(gc);
		}else if(key==Input.KEY_SPACE && Game.endTransition){
			try {
				gc.reinit();
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		if(key==Input.KEY_RIGHT && !Game.startTransition && !Game.endTransition){
			for(WallCube wc : WallCube.getInstances()){
				if((Game.player.getX()+Game.camOffX)+1==wc.getX() && Game.player.getY()==wc.getY() && wc.getImageId()!=3) return;
				else if((Game.player.getX()+Game.camOffX)+1==wc.getX() && Game.player.getY()==wc.getY() && wc.getImageId()==3 && Game.keys>0){
					wc.destroy();
					Game.keys--;
				}else if((Game.player.getX()+Game.camOffX)+1==wc.getX() && Game.player.getY()==wc.getY() && wc.getImageId()==3 && Game.keys<=0) return;
			}
			Game.turns++;
			Game.camOffX+=1;
			Ticker.nextTurn(gc);
			dir=1;
		}else if(key==Input.KEY_LEFT && !Game.startTransition && !Game.endTransition){
			for(WallCube wc : WallCube.getInstances()){
				if(Game.camOffX==0 || ((Game.player.getX()+Game.camOffX)-1==wc.getX() && Game.player.getY()==wc.getY() && wc.getImageId()!=3)) return;
				else if((Game.player.getX()+Game.camOffX)-1==wc.getX() && Game.player.getY()==wc.getY() && wc.getImageId()==3 && Game.keys>0){
					wc.destroy();
					Game.keys--;
				}else if((Game.player.getX()+Game.camOffX)-1==wc.getX() && Game.player.getY()==wc.getY() && wc.getImageId()==3 && Game.keys<=0) return;
			}
			Game.turns++;
			Game.camOffX-=1;
			Ticker.nextTurn(gc);
			dir=2;
		}else if(key==Input.KEY_UP && !Game.startTransition && !Game.endTransition){
			for(WallCube wc : WallCube.getInstances()) if(Game.player.getY()-1==wc.getY() && (Game.player.getX()+Game.camOffX)==wc.getX()) return;
			Game.player.setY(-1);
			Ticker.nextTurn(gc);
			dir=3;
			Game.turns++;
		}else if(key==Input.KEY_DOWN && !Game.startTransition && !Game.endTransition){
			for(WallCube wc : WallCube.getInstances()) if(Game.player.getY()+1==wc.getY() && (Game.player.getX()+Game.camOffX)==wc.getX()) return;
			Game.player.setY(1);
			Ticker.nextTurn(gc);
			dir=4;
			Game.turns++;
		}
	}
	
	public static void keyReleaseExecutor(int key){
		//key release commands
		
	}
	
	public static void mousePressExecutor(int button, int x, int y){
		//mouse press commands
		
	}
}
