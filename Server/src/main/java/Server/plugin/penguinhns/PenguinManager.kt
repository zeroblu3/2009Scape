package plugin.penguinhns

import core.game.node.entity.player.Player
import core.game.system.SystemLogger
import core.game.world.map.Location
import core.plugin.InitializablePlugin
import core.plugin.Plugin

@InitializablePlugin
class PenguinManager : Plugin<Any> {
    companion object {
        var penguins = ArrayList<PenguinSpawner.Penguin>()
        val spawner = PenguinSpawner()
        var tagMapping = HashMap<Location,ArrayList<String>>()
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        penguins  = spawner.spawnPenguins(6)
        for(p in penguins){
            tagMapping.put(p.loc, ArrayList())
        }
        return this
    }

    override fun fireEvent(identifier: String?, vararg args: Any?): Any {
        return Unit
    }

    fun log(message: String){
        SystemLogger.log("[Penguins] $message")
    }

}