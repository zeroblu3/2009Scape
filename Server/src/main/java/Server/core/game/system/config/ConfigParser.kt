package core.game.system.config

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ConfigParser {
    fun prePlugin() = runBlocking{
        launch {
            NPCConfigParser().load()
            ItemConfigParser().load()
        }
        launch {
            ObjectConfigParser().load()
            XteaParser().load()
        }
        InterfaceConfigParser().load()
    }

    fun postPlugin() = runBlocking{
        launch {
            ShopParser().load()
            DropTableParser().load()
            NPCSpawner().load()
        }
        launch {
            DoorConfigLoader().load()
            GroundSpawnLoader().load()
            MusicConfigLoader().load()
        }
        RangedConfigLoader().load()
    }
}