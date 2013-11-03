package com.zyxum.lavaescape;

import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game implements GameState {
	
	public static boolean startTransition,endTransition;
	public static Random random;
	public static int camOffX,zeropoint_x,zeropoint_y,shake,bombs,keys,lavax,deadidle,deadmaux,turns;
	public static Image img_player_left,img_player_right,img_player_up,img_player_down;
	public static Image img_wall,img_lava,img_dynamite,img_enemy,img_backwall,img_wallmossy,img_deadmau,img_key,img_wallkeyhole,img_dead;
	public static Player player;
	public static GameContainer public_gc;
	int FPS=1;
	
	@Override
	public int getID() {
		return LavaEscape.game;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		for(WallCube wc : WallCube.getInstances()) wc.destroy();
		for(Lava l : Lava.getInstances()) l.destroy();
		for(Enemy e : Enemy.getInstances()) e.destroy();
		for(Key k : Key.getInstances()) k.destroy();
		for(Bomb b : Bomb.getInstances()) b.destroy();
		
		random=new Random();
		turns=0;
		deadmaux=-62;
		startTransition=true;
		endTransition=false;
		bombs=5;
		keys=3;
		shake=0;
		zeropoint_x=340;
		zeropoint_y=0;
		
		img_wallkeyhole=new Image("res/keyhole.png");
		img_wallmossy=new Image("res/wall_mossy.png");
		img_lava=new Image("res/lava_new.png");
		img_wall=new Image("res/wall.png");
		img_player_right=new Image("res/player.png");
		img_player_left=img_player_right.getFlippedCopy(true, false);
		img_player_down=img_player_right.copy();
		img_player_down.rotate(90);
		img_player_up=img_player_right.copy();
		img_player_up.rotate(270);
		img_dynamite=new Image("res/dynamite.png");
		img_enemy=new Image("res/enemy.png");
		img_enemy=img_enemy.getFlippedCopy(true, false);
		img_backwall=new Image("res/back_wall.png");
		img_deadmau=new Image("res/deadmau.png");
		img_key=new Image("res/key.png");
		img_dead=new Image("res/you_died.png");
		
		new Lava(32, 32);
		camOffX=10;
		player=new Player(10, 5, 20, 20);
		Level.create(2000, gc.getHeight()/32);
		for(WallCube wc : WallCube.getInstances()) if(wc.getX()==20 && wc.getY()==5) wc.destroy();
		
		public_gc=gc;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.setBackground(Color.white);
		
		if(startTransition){
			for(int x=-4096;x<4096;x+=32){
				for(int y=-4096;y<4096;y+=32){
					g.drawImage(img_backwall, zeropoint_x+x, zeropoint_y+y);
				}
			}
		}else{
			for(int x=-32;x<gc.getWidth()+32;x+=32){
				for(int y=-32;y<gc.getHeight()+32;y+=32){
					g.drawImage(img_backwall, zeropoint_x+x, zeropoint_y+y);
				}
			}
		}
		
		for(Dynamite d : Dynamite.getInstances()){
			g.drawImage(img_dynamite, zeropoint_x+((d.getX()-camOffX)*32), zeropoint_y+(d.getY()*32));
			g.setColor(Color.white);
			g.drawString(""+d.getFuse(), zeropoint_x+((d.getX()-camOffX)*32)+10, zeropoint_y+(d.getY()*32)+7);
		}
		
		for(Enemy e : Enemy.getInstances()) if(e.exists) g.drawImage(img_enemy, zeropoint_x+((e.getX()-camOffX)*32), zeropoint_y+(e.getY()*32));
		for(Key k : Key.getInstances()) if(k.exists) g.drawImage(img_key, zeropoint_x+((k.getX()-camOffX)*32), zeropoint_y+(k.getY()*32));
		for(Bomb b : Bomb.getInstances()) g.drawImage(img_dynamite, zeropoint_x+((b.getX()-camOffX)*32), zeropoint_y+(b.getY()*32));
		for(Lava l : Lava.getInstances()) if(l.exists && !((l.getX()-camOffX)*32<-64 && (l.getX()-camOffX)*32>gc.getWidth()) || startTransition) g.drawImage(img_lava, zeropoint_x+(l.getX()-(camOffX*32)), zeropoint_y+l.getY());
		for(WallCube wc : WallCube.getInstances()) if(wc.exists && !((wc.getX()-camOffX)*32<0 && (wc.getX()-camOffX)*32>gc.getWidth()) || startTransition){
			switch(wc.getImageId()){
			case 1: g.drawImage(img_wall, zeropoint_x+((wc.getX()-camOffX)*32), zeropoint_y+(wc.getY()*32)); break;
			case 2: g.drawImage(img_wallmossy, zeropoint_x+((wc.getX()-camOffX)*32), zeropoint_y+(wc.getY()*32)); break;
			case 3: g.drawImage(img_wallkeyhole, zeropoint_x+((wc.getX()-camOffX)*32), zeropoint_y+(wc.getY()*32)); break;
			}
		}
		
		if(!endTransition) switch(KeyCommand.dir){
		case 1: img_player_right.draw(zeropoint_x+(player.getX()*32), zeropoint_y+(player.getY()*32)); break;
		case 2: img_player_left.draw(zeropoint_x+(player.getX()*32), zeropoint_y+(player.getY()*32)); break;
		case 3: img_player_up.draw(zeropoint_x+(player.getX()*32), zeropoint_y+(player.getY()*32)); break;
		case 4: img_player_down.draw(zeropoint_x+(player.getX()*32), zeropoint_y+(player.getY()*32)); break;
		}else g.drawImage(img_dead, 0, 0);
		
		if(!endTransition && !startTransition) Gui.render(g);
		if(deadidle>2000) img_deadmau.drawCentered(deadmaux, gc.getHeight()/2);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		Ticker.tick(delta);
		
		AppGameContainer inGameApp = (AppGameContainer) gc;
		if(turns>0) inGameApp.setTitle("Lava Escape - Turns: "+turns);
		
		if(endTransition){
			deadidle+=.1*delta;
			for(Lava l : Lava.getInstances()) lavax=l.getX();
			if(deadidle>2000) deadmaux+=.1*delta;
		}else deadidle=0;
		
		if((shake>0 && !startTransition) || (endTransition && lavax<(camOffX*32)+gc.getWidth())){
			int[] shakeArray={-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
			zeropoint_x=shakeArray[random.nextInt(shakeArray.length)];
			zeropoint_y=shakeArray[random.nextInt(shakeArray.length)];
			shake-=.01*delta;
		}else if(!startTransition){
			zeropoint_x=0;
			zeropoint_y=0;
		}
		
		if(gc.getInput().isKeyPressed(Input.KEY_F) && LavaEscape.debug) 
			switch(FPS){case 1: public_gc.setShowFPS(true); FPS=0; break; case 0: public_gc.setShowFPS(false); FPS=1; break;}
	}
	
	//mouse events
	@Override public void mouseClicked(int button, int x, int y, int count) {}
	@Override public void mouseDragged(int oldx, int oldy, int newx, int newy) {}
	@Override public void mouseMoved(int oldx, int oldy, int newx, int newy) {}
	@Override public void mousePressed(int button, int x, int y) {
		KeyCommand.mousePressExecutor(button, x, y);
	}
	@Override public void mouseReleased(int button, int x, int y) {}
	@Override public void mouseWheelMoved(int change) {}
	
	//key events
	@Override public void keyPressed(int key, char c) {
		KeyCommand.keyPressExecutor(public_gc, key);
	}
	
	@Override public void keyReleased(int key, char c) {
		KeyCommand.keyReleaseExecutor(key);
	}
	
	//controller events
	@Override public void controllerButtonPressed(int controller, int button) {
		if(button==1) KeyCommand.keyPressExecutor(public_gc, Input.KEY_SPACE);
	}
	@Override public void controllerButtonReleased(int controller, int button) {}
	
	@Override public void controllerDownPressed(int controller) {
		KeyCommand.keyPressExecutor(public_gc, Input.KEY_DOWN);
	}
	@Override public void controllerLeftPressed(int controller) {
		KeyCommand.keyPressExecutor(public_gc, Input.KEY_LEFT);
	}
	@Override public void controllerRightPressed(int controller) {
		KeyCommand.keyPressExecutor(public_gc, Input.KEY_RIGHT);
	}
	@Override public void controllerUpPressed(int controller) {
		KeyCommand.keyPressExecutor(public_gc, Input.KEY_UP);
	}
	
	@Override public void controllerDownReleased(int controller) {}
	@Override public void controllerLeftReleased(int controller) {}
	@Override public void controllerRightReleased(int controller) {}
	@Override public void controllerUpReleased(int controller) {}
	
	//input events: DO NOT CHANGE!!!
	@Override public void inputEnded() {}
	@Override public void inputStarted() {}
	@Override public boolean isAcceptingInput() {
		return true;
	}
	@Override public void setInput(Input input) {}
	
	//state events
	@Override public void enter(GameContainer gc, StateBasedGame game) throws SlickException {}
	@Override public void leave(GameContainer gc, StateBasedGame game) throws SlickException {}
}
