package plugin.quest.witchs_house;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.game.interaction.Interaction;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;

/**
 * Handles the witch's house experiment NPCs
 * @author ceik
 */
public class ExperimentNPC extends NPC {
    private NPCDefinition definition;
    private static int[] ids = { 897, 898, 899, 900 };
    private int thisId = this.getId();
    private static int index;
    private ExperimentNPC thisNpc = this;

    public ExperimentNPC(Location location, Direction direction, int index){
        super(ids[index], location, direction);
        this.index = index;
    }

    @Override
    public void init() {
        super.init();
        this.setRespawn(false);
        this.setInteraction(new Interaction(this){
            @Override
            public void handle(Player player, Option option) {
                if(option.getName() == "attack"){
                    player.getProperties().getCombatPulse().attack(thisNpc);
                    return;
                }
                super.handle(player, option);
            }
        });
    }

    @Override
    public void finalizeDeath(Entity killer) {
        super.finalizeDeath(killer);
        if(index < 3) {
            killer.asPlayer().debug("Loading next enemy, current index: " + index + " next index: " + (index + 1));
            ExperimentNPC next = new ExperimentNPC(getLocation(), getDirection(), index + 1);
            next.init();
            return;
        }
        killer.asPlayer().setAttribute("/save:killedExperiment",true);
    }

}
