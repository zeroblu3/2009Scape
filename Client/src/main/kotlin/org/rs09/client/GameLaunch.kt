package org.rs09.client

import org.rs09.client.config.GameConfig
import org.runite.client.GameShell

object GameLaunch {

    /**
     * The main method.
     * r @param args the arguments casted on runtime.
     * r_game
     *
     */
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            GameConfig.parse(GameConfig.configLocation)
            GameConfig.implementHoliday()
        } catch (e: Exception){
            GameConfig.IP_ADDRESS = "play.2009scape.org"
            GameConfig.IP_MANAGEMENT = "play.2009scape.org"
            GameConfig.RCM_STYLE_PRESET = "classic"
            GameConfig.RCM_TITLE = "<col=5d5447>Choose Option</col>"
        }
        for (i in args.indices) {
            val cmd = args[i].split("=").toTypedArray()
            when (cmd[0]) {
                "ip" -> GameConfig.IP_ADDRESS = cmd[1]
                "world" -> GameConfig.WORLD = cmd[1].toInt()
            }
        }
        /**
         * Launches the client
         */
        GameShell.launchDesktop()
    }

}