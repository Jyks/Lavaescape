package com.zyxum.lavaescape;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class LavaEscape extends StateBasedGame {

	public static boolean debug = false;
	
	public static final int game=1;
	
	public LavaEscape() {
		super("Lava Escape");
		this.addState(new Game());
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.enterState(game);
	}
	
	public static void main(String[] args) throws SlickException {
		if(args!=null && !debug){
			if(args.toString().contains("debug")) debug=true;
		}
        AppGameContainer app = new AppGameContainer(new LavaEscape());
        app.setShowFPS(false);
        app.setDisplayMode(640, 480, false);
        app.setAlwaysRender(true);
        app.setUpdateOnlyWhenVisible(true);
        app.setVSync(true);
        app.start();
   }
}
