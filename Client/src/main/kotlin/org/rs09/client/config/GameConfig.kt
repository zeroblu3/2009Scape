package org.rs09.client.config

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.FileReader

class GameConfig {
    companion object {
        @JvmField
        var ITEM_DEBUG_ENABLED = false

        @JvmField
        var OBJECT_DEBUG_ENABLED = false

        @JvmField
        var NPC_DEBUG_ENABLED = false

        @JvmField
        var HD_LOGIN_DEBUG = false

        @JvmField
        var HD_LOGIN_VERBOSE = false

        @JvmField
        var CACHE_DEBUG = false

        @JvmField
        var WORLD_MAP_DEBUG = false

        @JvmField
        var LOGIN_THEME = "scape main"

        @JvmField
        var RCM_BG_COLOR = 6116423

        @JvmField
        var RCM_BG_OPACITY  = 255

        @JvmField
        var RCM_TITLE_COLOR = 0

        @JvmField
        var RCM_TITLE_OPACITY = 255

        @JvmField
        var RCM_BORDER_COLOR = 0

        @JvmField
        var RCM_BORDER_OPACITY = 255

        @JvmField
        var IP_ADDRESS = "localhost"

        @JvmStatic
        fun parse(path: String){
            val reader = FileReader(path)
            val parser = JSONParser()
            val data = parser.parse(reader) as JSONObject

            //parse IP
            if(data.containsKey("ip_address")) IP_ADDRESS = data["ip_address"].toString()

            //Parse customization options
            if(data.containsKey("customization")){
                val custom = data["customization"] as JSONObject
                if(custom.containsKey("login_theme")) LOGIN_THEME = custom["login_theme"].toString()

                //Right-click menu customizations
                if(custom.containsKey("right_click_menu")){
                    val rcm = custom["right_click_menu"] as JSONObject

                    //background
                    if(rcm.containsKey("background")){
                        val bg = rcm["background"] as JSONObject
                        if(bg.containsKey("color")) RCM_BG_COLOR = bg["color"].toString().replace("#","").toInt(16) //convert hex -> deci
                        if(bg.containsKey("opacity")) RCM_BG_OPACITY = bg["opacity"].toString().toInt()
                    }

                    //title bar
                    if(rcm.containsKey("title_bar")){
                        val tb = rcm["title_bar"] as JSONObject
                        if(tb.containsKey("color")) RCM_TITLE_COLOR = tb["color"].toString().replace("#","").toInt(16) //convert hex -> deci
                        if(tb.containsKey("opacity")) RCM_TITLE_OPACITY = tb["opacity"].toString().toInt()
                    }

                    //border
                    if(rcm.containsKey("border")){
                        val border = rcm["border"] as JSONObject
                        if(border.containsKey("color")) RCM_BORDER_COLOR = border["color"].toString().replace("#","").toInt(16) //convert hex -> deci
                        if(border.containsKey("opacity")) RCM_BORDER_OPACITY = border["opacity"].toString().toInt()
                    }
                }
            }

            //Parse debug options
            if(data.containsKey("debug")){
                val debug = data["debug"] as JSONObject
                if(debug.containsKey("item_debug")) ITEM_DEBUG_ENABLED = debug["item_debug"] as Boolean
                if(debug.containsKey("npc_debug")) NPC_DEBUG_ENABLED = debug["npc_debug"] as Boolean
                if(debug.containsKey("object_debug")) OBJECT_DEBUG_ENABLED = debug["object_debug"] as Boolean
                if(debug.containsKey("hd_login_region_debug"))  HD_LOGIN_DEBUG = debug["hd_login_region_debug"] as Boolean
                if(debug.containsKey("hd_login_region_debug_verbose")) HD_LOGIN_VERBOSE = debug["hd_login_region_debug_verbose"] as Boolean
                if(debug.containsKey("cache_debug")) CACHE_DEBUG = debug["cache_debug"] as Boolean
                if(debug.containsKey("world_map_debug")) WORLD_MAP_DEBUG = debug["world_map_debug"] as Boolean
            }

        }
    }
}