package plugin.interaction.item.brawling_gloves;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

public class BrawlingGlove {
    public static int id;
    public static String name;
    public static int charge;
    public static ItemDefinition def = ItemDefinition.forId(id);
    public static byte indicator;

    public BrawlingGlove(int id, String name, int charges, byte indicator){
        this.id = id;
        this.name = name;
        this.charge = charges;
        this.indicator = indicator;
    }
    public int getId() {return id;}
    public String getName() {return def.getName();}
    public byte getIndicator() {return indicator;}
    public int getCharge() { return charge;}
}
