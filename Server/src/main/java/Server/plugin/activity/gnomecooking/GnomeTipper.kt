package plugin.activity.gnomecooking

import core.game.content.ItemNames
import core.game.node.item.Item
import core.game.node.item.WeightedChanceItem
import core.tools.RandomFunction

object GnomeTipper {
    private val easyTips = arrayListOf(
            WeightedChanceItem(995,50,100,30),
            WeightedChanceItem(995,23,76,50),
            WeightedChanceItem(995,10,250,20)
    )

    private val hardTips = arrayListOf(
            //Uniques Weight = 18
            WeightedChanceItem(ItemNames.GNOME_GOGGLES_9472,1,6),
            WeightedChanceItem(ItemNames.GNOME_SCARF_9470,1,6),
            WeightedChanceItem(ItemNames.GRAND_SEED_POD_9469,5,7),
            WeightedChanceItem(ItemNames.MINT_CAKE_9475,1,4),
            WeightedChanceItem(ItemNames.GNOMEBALL_751,1,4),
            //Herbs Weight = 20
            WeightedChanceItem(ItemNames.TOADFLAX_2998,3,10),
            WeightedChanceItem(ItemNames.SNAPDRAGON_3000,1,10),
            //Uncut Gems Weight = 46
            WeightedChanceItem(ItemNames.RED_TOPAZ_1613,1,8),
            WeightedChanceItem(ItemNames.DIAMOND_1601,1,7),
            WeightedChanceItem(ItemNames.UNCUT_EMERALD_1621,3,5,7),
            WeightedChanceItem(ItemNames.UNCUT_JADE,2,3,7),
            WeightedChanceItem(ItemNames.UNCUT_SAPPHIRE_1623,6,10,8),
            WeightedChanceItem(ItemNames.UNCUT_RUBY_1619,2,3,7),
            WeightedChanceItem(ItemNames.UNCUT_OPAL_1625,1,10),
            //Runes Weight = 25
            WeightedChanceItem(ItemNames.COSMIC_RUNE,11,5),
            WeightedChanceItem(ItemNames.NATURE_RUNE,10,15,5),
            WeightedChanceItem(ItemNames.LAW_RUNE,10,5),
            WeightedChanceItem(ItemNames.DEATH_RUNE,11,5),
            WeightedChanceItem(ItemNames.SOUL_RUNE,9,5),
            //Untipped crossbow bolts Weight = 9
            WeightedChanceItem(ItemNames.MITHRIL_BOLTS_UNF_9379,5,10,3),
            WeightedChanceItem(ItemNames.ADAMANT_BOLTSUNF_9380,3,5,3),
            WeightedChanceItem(ItemNames.RUNITE_BOLTS_UNF_9381,1,3,3),
            //Other tips
            WeightedChanceItem(ItemNames.LOOP_HALF_OF_KEY,1,9),
            WeightedChanceItem(ItemNames.TOOTH_HALF_OF_KEY,1,9),
            WeightedChanceItem(ItemNames.PURE_ESSENCE_NOTED,97,3),
            WeightedChanceItem(ItemNames.BIRD_NEST_5073,1,2),
            WeightedChanceItem(ItemNames.YEW_SEED,1,6),
            WeightedChanceItem(ItemNames.CALQUAT_TREE_SEED,1,6)

    )

    enum class LEVEL {
        EASY,
        HARD
    }

    @JvmStatic
    fun getTip(level: LEVEL): Item {
        return when(level){
            LEVEL.EASY -> RandomFunction.rollWeightedChanceTable(easyTips)
            LEVEL.HARD -> RandomFunction.rollWeightedChanceTable(hardTips)
        }
    }



}