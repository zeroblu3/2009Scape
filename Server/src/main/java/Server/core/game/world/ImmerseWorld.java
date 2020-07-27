package core.game.world;

import core.game.world.map.Location;
import nonapi.io.github.classgraph.json.Id;
import plugin.ai.AIPBuilder;
import plugin.ai.AIPlayer;
import plugin.ai.general.GeneralBotCreator;
import plugin.ai.general.scriptrepository.CowKiller;
import plugin.ai.general.scriptrepository.Idler;
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
        new GeneralBotCreator(new CowKiller(), assembler.produce(CombatBotAssembler.Type.MELEE, CombatBotAssembler.Tier.MED,Location.create(3257, 3267, 0)));
        new GeneralBotCreator(Location.create(3169, 3487, 0), new Idler());
        new GeneralBotCreator(Location.create(3169, 3487, 0),new Idler());
        new GeneralBotCreator(Location.create(3169, 3491, 0),new Idler());
        new GeneralBotCreator(Location.create(3165, 3498, 0),new Idler());
        new GeneralBotCreator(Location.create(3159, 3490, 0),new Idler());
        new GeneralBotCreator(Location.create(3164, 3487, 0), new Idler());
        new GeneralBotCreator(Location.create(3165, 3487, 0), new Idler());
        new GeneralBotCreator(Location.create(3166, 3487, 0), new Idler());
        new GeneralBotCreator(Location.create(3167, 3489, 0), new Idler());
        new GeneralBotCreator(Location.create(3167, 3490, 0), new Idler());
        new GeneralBotCreator(Location.create(3165, 3492, 0), new Idler());
        new GeneralBotCreator(Location.create(3164, 3492, 0), new Idler());
        new GeneralBotCreator(Location.create(3162, 3490, 0), new Idler());
        new GeneralBotCreator(Location.create(3162, 3489, 0), new Idler());
    }
}
