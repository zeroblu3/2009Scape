package core.game.world;

import core.game.world.map.Location;
import plugin.ai.AIPBuilder;
import plugin.ai.AIPlayer;
import plugin.ai.general.GeneralBotCreator;
import plugin.ai.general.scriptrepository.CowKiller;
import plugin.ai.general.scriptrepository.LobsterCatcher;
import plugin.ai.lumbridge.LumbridgeBotHandler;
import plugin.ai.pvmbots.CombatBotAssembler;
import plugin.ai.pvmbots.PvMBotsBuilder;

public class ImmerseWorld {
    static CombatBotAssembler assembler = new CombatBotAssembler();
    public static void init(){
        PvMBotsBuilder.immersiveSpawns();
        LumbridgeBotHandler.immersiveLumbridge();
        new GeneralBotCreator(Location.create(2805, 3435, 0),new LobsterCatcher());
        new GeneralBotCreator(new CowKiller(), assembler.produce(CombatBotAssembler.Type.RANGE, CombatBotAssembler.Tier.MED,Location.create(3261, 3269, 0)));
        new GeneralBotCreator(new CowKiller(), assembler.produce(CombatBotAssembler.Type.MELEE, CombatBotAssembler.Tier.LOW,Location.create(3261, 3269, 0)));
        new GeneralBotCreator(new CowKiller(), assembler.produce(CombatBotAssembler.Type.MELEE, CombatBotAssembler.Tier.LOW,Location.create(3257, 3267, 0)));
    }
}
