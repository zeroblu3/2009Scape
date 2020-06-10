package plugin.interaction.city;

import core.cache.def.impl.NPCDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Package -> plugin.interaction.city
 * Created on -> 9/10/2016 @10:42 PM for 530
 *
 * @author Ethan Kyle Millard
 */
@InitializablePlugin
public class HomePlugin extends OptionHandler {

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        NPCDefinition.forId(552).getConfigurations().put("talk-to", this);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        switch (node.getId()) {
            case 552:
                player.getDialogueInterpreter().open(552);
                break;
        }
        return false;
    }
}
