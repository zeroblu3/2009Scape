package plugin.ai.general.scriptrepository

import core.game.system.command.CommandSystem
import core.tools.RandomFunction
import plugin.ai.AIPlayer

class Idler : Script(){
    var ticks = 0
    override fun tick() {
        val randNum = RandomFunction.random(200)
        if(randNum == 1){
            CommandSystem.getCommandSystem().parse(bot,"roll")
        }
        if(ticks > 1000){
            bot = AIPlayer(bot.location)
            ticks = 0
        }
        ticks++
    }
}