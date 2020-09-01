package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.ConsumableEffect;

public class PercentHeal extends ConsumableEffect {
    double perc = 0.0;

    public PercentHeal(double percent){
        perc = percent;
    }

    @Override
    public void activate(Player p) {
        int amount = (int) Math.floor(p.getSkills().getMaximumLifepoints() * perc);
        if(p.getSkills().getLifepoints() + amount > p.getSkills().getMaximumLifepoints()){
            amount = p.getSkills().getMaximumLifepoints() - p.getSkills().getLifepoints();
        }
        p.getSkills().heal(amount);
    }
}
