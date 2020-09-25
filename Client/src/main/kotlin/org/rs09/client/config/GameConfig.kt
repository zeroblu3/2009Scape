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