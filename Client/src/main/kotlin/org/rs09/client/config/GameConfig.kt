package org.rs09.client.config

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.FileReader

/**
 * Handles the client's config loading
 * @author Ceikry
 */
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
        var RCM_STYLE_PRESET = "classic"

        @JvmField
        var RCM_TITLE = "<col=0>Choose Option</col>"

        @JvmField
        var IP_ADDRESS = "localhost"

        @JvmField
        var RS3_CONTEXT_STYLE = false

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
                        if(tb.containsKey("font_color")) RCM_TITLE = RCM_TITLE.replace("0", tb["font_color"].toString().replace("#",""))
                        if(tb.containsKey("color")) RCM_TITLE_COLOR = tb["color"].toString().replace("#","").toInt(16) //convert hex -> deci
                        if(tb.containsKey("opacity")) RCM_TITLE_OPACITY = tb["opacity"].toString().toInt()
                    }

                    //border
                    if(rcm.containsKey("border")){
                        val border = rcm["border"] as JSONObject
                        if(border.containsKey("color")) RCM_BORDER_COLOR = border["color"].toString().replace("#","").toInt(16) //convert hex -> deci
                        if(border.containsKey("opacity")) RCM_BORDER_OPACITY = border["opacity"].toString().toInt()
                    }

                    //styles (changes how things are drawn)
                    if(rcm.containsKey("styles")){
                        val style = rcm["styles"] as JSONObject
                        if(style.containsKey("presets")) RCM_STYLE_PRESET = style["presets"].toString()
                        if(style.containsKey("rs3border")) RS3_CONTEXT_STYLE = style["rs3border"] as Boolean
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


            /**
             * Style overwrites (Still working on this system. We should allow for maximum creativity
             * The way that it will be setup is a style type 1st
             * ie, classicbox, rs3, rounded, rounded2
             * Then we introduce color schemes that a user could select
             * ie, classic, rs3, alternate, alternate2, custom
             * @author Woah
             */
            when (RCM_STYLE_PRESET) {
                "classic" -> {
                    RS3_CONTEXT_STYLE = false
                    RCM_BG_COLOR = 6116423
                    RCM_BG_OPACITY = 255
                    RCM_TITLE = "<col=5d5447>Choose Option</col>"
                    RCM_TITLE_COLOR = 0
                    RCM_TITLE_OPACITY = 255
                    RCM_BORDER_COLOR = 0
                    RCM_BORDER_OPACITY = 255
                }
                "rs3" -> {
                    RS3_CONTEXT_STYLE = true
                    RCM_BG_COLOR = 662822
                    RCM_BG_OPACITY = 255
                    RCM_TITLE = "<col=C6B895>Choose Option</col>"
                    RCM_TITLE_COLOR = 1512718
                    RCM_TITLE_OPACITY = 165
                    RCM_BORDER_COLOR = 16777215
                    RCM_BORDER_OPACITY = 255
                }
            }
        }
    }
}