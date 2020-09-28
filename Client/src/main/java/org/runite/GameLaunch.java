package org.runite;

import org.rs09.client.config.GameConfig;
import org.runite.jagex.GameShell;

/**
 * Handles the launching of our Game Client.
 * @author Keldagrim Development Team
 *
 */
public class GameLaunch {

	public static final String CONF_FILE="config.json";

	/**
	 * The game settings.
	 */
	/*

	NOTICE: IF YOU'RE A DEVELOPER, THE EASIEST WAY TO CONNECT TO THE LIVESERVER IS WITH CLIENT.JAVA. THIS CLASS IS FOR LOCAL SERVER CONNECTING!

	 */
	public static GameSetting SETTINGS = new GameSetting("2009Scape", Configurations.LOCAL_SERVER ? "127.0.0.1" : "99.146.18.98"/*"34.68.75.237"*/, 1, "live", false);
	
	/**
	 * The main method.
	 * @param args the arguments casted on runtime.
	 */
	public static void main(String[]args) {
		try {
			GameConfig.parse(CONF_FILE);
		} catch (Exception e){
			System.out.println("Can't find config file " + CONF_FILE);
			e.printStackTrace();
		}

		for (int i = 0; i < args.length; i++) {
			String[] cmd = args[i].split("=");
			switch (cmd[0]) {
			case "ip":
				SETTINGS.setIp(cmd[1]);
				break;
			case "world":
				SETTINGS.setWorld(Integer.parseInt(cmd[1]));
				break;
			}
		}
		/**
		 * Launches the client
		 */
		GameShell.launchDesktop();
	}

}
