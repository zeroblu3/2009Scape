package plugin.quest.witchs_house;

import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.InitializablePlugin;

/**
 * Created for 2009Scape
 * User: Ethan Kyle Millard
 * Date: March 25, 2020
 * Time: 8:28 PM
 */
@InitializablePlugin
public class WitchsExperimentNPC extends AbstractNPC {

    private Player player;
    private ExperimentType type;
    /**
     * Constructs a new {@code TreeSpiritNPC} {@code Object}.
     * @param id the id.
     * @param location the location.
     */
    public WitchsExperimentNPC(int id, Location location) {
        super(id, location);
    }

    /**
     * Constructs a new {@code TreeSpiritNPC} {@code Object}.
     */
    public WitchsExperimentNPC() {
        super(0, null);
    }


    public WitchsExperimentNPC(ExperimentType type, Location location) {
        super(type.getId(), location);
        this.type = type;

    }

    @Override
    public void handleTickActions() {
        super.handleTickActions();
        if (!inCombat()) {
            attack(player);
        }
        if (player != null && !player.isActive() || player.getLocation().getDistance(getLocation()) > 15) {
            clear();
            player.removeAttribute("experimentSpawned");
        }
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public boolean isAttackable(Entity entity, CombatStyle style) {
        if (entity != player) {
            return false;
        }
        return super.isAttackable(entity, style);
    }

    @Override
    public void finalizeDeath(Entity killer) {
        super.finalizeDeath(killer);
        if (killer instanceof Player) {
            Player player = (Player) killer;
            if(type != ExperimentType.FOURTH) {
                WitchsExperimentNPC experiment = new WitchsExperimentNPC(type.next(), getLocation());
                for (String message : type.getMessage()) {
                    if (message.length() > 0)
                        player.sendMessage(message);
                }
                experiment.setPlayer(player);
                experiment.setRespawn(false);
                experiment.init();
                experiment.attack(player);
                player.setAttribute("/save:killedExperiment", false);
                return;
            }
            player.setAttribute("/save:killedExperiment", true);
            player.removeAttribute("experimentSpawned");
        }
    }


    @Override
    public AbstractNPC construct(int id, Location location, Object... objects) {
        return new WitchsExperimentNPC(id, location);
    }

    @Override
    public int[] getIds() {
        return new int[] { 897, 898, 899, 900 };
    }

    /**
     * Sets the player.
     * @param player the player.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the player.
     * @return The player.
     */
    public Player getPlayer() {
        return player;
    }

    public ExperimentType getType() {
        return type;
    }

    public enum ExperimentType {
        FIRST(897, ""),
        SECOND(898, "The shapeshifters' body begins to deform!", "The shapeshifter turns into a spider!"),
        THIRD(899, "The shapeshifters' body begins to twist!", "The shapeshifter turns into a bear!"),
        FOURTH(900, "The shapeshifters' body pulses!", "The shapeshifter turns into a wolf!"),

        ;

        private int id;
        private String[] message;

        ExperimentType(int id, String... message) {
            this.id = id;
            this.message = message;
        }

        private static ExperimentType[] experimentTypes = values();

        public ExperimentType next()
        {
            return experimentTypes[(this.ordinal()+1) % experimentTypes.length];
        }

        public int getId() {
            return id;
        }

        public String[] getMessage() {
            return message;
        }
    }

}