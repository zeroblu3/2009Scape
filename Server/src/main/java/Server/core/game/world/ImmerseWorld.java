package core.game.world;

import core.game.node.entity.player.ai.lumbridge.LumbridgeBotHandler;
import core.game.node.entity.player.ai.pvmbots.PvMBotsBuilder;

public class ImmerseWorld {
    public static void init(){
        PvMBotsBuilder.immersiveSpawns();
        LumbridgeBotHandler.immersiveLumbridge();
    }
}
