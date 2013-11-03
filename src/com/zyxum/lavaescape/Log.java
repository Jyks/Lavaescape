package com.zyxum.lavaescape;

public class Log {
	public static void debug(Object obj){
		//debug messages
		if(LavaEscape.debug) System.out.println("[DEBUG] "+obj);
	}
}