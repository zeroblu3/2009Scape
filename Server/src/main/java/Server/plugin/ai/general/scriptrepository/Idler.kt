package plugin.ai.general.scriptrepository

import core.game.world.map.RegionManager
import core.game.world.update.flag.context.ChatMessage
import core.game.world.update.flag.player.ChatFlag
import plugin.ai.AIPlayer

class Idler : Script(){

    var ticks = 0
    override fun tick() {
        val players = RegionManager.getLocalPlayers(bot)
        bot.updateMasks.register(ChatFlag(ChatMessage(bot, "Test", 0, 0)))//effects: 1 wave: 2 wave2: 3 shake: 4 scroll: 5 slide:
        for(player in players){
//            if(!player.isArtificial){
//                bot.sendChat(messages.random())
//                player?.interaction?.handle(bot,player.interaction[2])
//            }
        }
        if(ticks > 1000){
            bot = AIPlayer(bot.location)
            ticks = 0
        }
        ticks++
    }
}