package plugin.activity.mta

import core.game.content.ItemNames
import core.game.node.Node
import core.game.node.entity.Entity
import core.game.node.entity.combat.equipment.SpellType
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.SpellBookManager.SpellBook
import core.game.node.entity.player.link.audio.Audio
import core.game.node.item.Item
import core.game.world.update.flag.context.Animation
import core.game.world.update.flag.context.Graphics
import core.plugin.Plugin
import plugin.activity.mta.impl.EnchantingZone
import plugin.activity.mta.impl.EnchantingZone.Shapes
import plugin.skill.magic.MagicSpell
import plugin.skill.magic.Runes

/**
 * Represents the enchant spells.
 * @author Ceikry
 */
class EnchantSpell : MagicSpell {
    /**
     * The enchantable jewellery array.
     */
    private val jewellery: HashMap<Int,Item>?

    /**
     * Constructs a new `EnchantSpell` `Object`.
     */
    constructor() {
        jewellery = null
    }

    /**
     * Constructs a new `EnchantSpell` `Object`.
     * @param level The level required.
     * @param jewellery The jewellery this spell is able to echant.
     * @param runes The runes required.
     */
    constructor(level: Int, experience: Double, jewellery: HashMap<Int,Item>, runes: Array<Item>?) : super(SpellBook.MODERN, level, experience, ANIMATION, GRAPHIC, Audio(115, 1, 0), runes) {
        this.jewellery = jewellery
    }

    override fun cast(entity: Entity, target: Node): Boolean {
        if (target !is Item || entity !is Player) {
            return false
        }
        entity.interfaceManager.setViewedTab(6)
        val enchanted = jewellery?.getOrDefault(target.id,null)

        if (enchanted == null) {
            entity.packetDispatch.sendMessage("You can't use this spell on this item.")
            return false
        }
        if (!meetsRequirements(entity, true, true)) {
            return false
        }

        if (entity.inventory.remove(target)) {
            visualize(entity, target)
            entity.inventory.add(enchanted)
        }


        //MTA-Specific Code
        if (entity.zoneMonitor.isInZone("Enchantment Chamber")) {
            entity.graphics(Graphics.create(237, 110))
            var pizazz = 0
            if (target.id == 6903) {
                pizazz = (if (getSpellId() == 5) 1 else if (getSpellId() == 16) 2 else if (getSpellId() == 28) 3 else if (getSpellId() == 36) 4 else if (getSpellId() == 51) 5 else 6) * 2
            } else {
                val shape = Shapes.forItem(target)
                if (shape != null) {
                    var convert = entity.getAttribute("mta-convert", 0)
                    convert += 1
                    if (convert >= 10) {
                        pizazz = if (getSpellId() == 5) 1 else if (getSpellId() == 16) 2 else if (getSpellId() == 28) 3 else if (getSpellId() == 36) 4 else if (getSpellId() == 51) 5 else 6
                        convert = 0
                    }
                    entity.setAttribute("mta-convert", convert)
                    if (shape == EnchantingZone.BONUS_SHAPE) {
                        pizazz += 1
                        entity.sendMessage("You get " + pizazz + " bonus point" + (if (pizazz != 1) "s" else "") + "!")
                    }
                }
            }
            if (pizazz != 0) {
                EnchantingZone.ZONE.incrementPoints(entity, MTAType.ENCHANTERS.ordinal, pizazz)
            }
        }
        return true
    }

    override fun getDelay(): Int {
        return 1
    }

    override fun getExperience(player: Player): Double {
        return if (player.zoneMonitor.isInZone("Enchantment Chamber")) {
            experience - experience * 0.75
        } else experience
    }

    override fun newInstance(arg: SpellType?): Plugin<SpellType>? {
        /**
         * Enchant Sapphire Jewelry (Lvl-1 Enchant)
         */
        SpellBook.MODERN.register(5, EnchantSpell(7, 17.5,
                hashMapOf(
                        //Begin Jewelry Enchantments
                        ItemNames.SAPPHIRE_RING_1637 to Item(ItemNames.RING_OF_RECOIL_2550),
                        ItemNames.SAPPHIRE_NECKLACE_1656 to Item(ItemNames.GAMES_NECKLACE8_3853),
                        ItemNames.SAPPHIRE_AMULET_1694 to Item(ItemNames.AMULET_OF_MAGIC_1727),
                        ItemNames.SAPPHIRE_BRACELET_11072 to Item(ItemNames.BRACELET_OF_CLAY_11074),
                        //Begin MTA-specific enchantments
                        ItemNames.CUBE_6899 to Item(ItemNames.ORB_6902),
                        ItemNames.CYLINDER_6898 to Item(ItemNames.ORB_6902),
                        ItemNames.ICOSAHEDRON_6900 to Item(ItemNames.ORB_6902),
                        ItemNames.PENTAMID_6901 to Item(ItemNames.ORB_6902),
                        ItemNames.DRAGONSTONE_6903 to Item(ItemNames.ORB_6902)
                ),
                arrayOf(Item(ItemNames.COSMIC_RUNE,1), Item(ItemNames.WATER_RUNE,1))))

        /**
         * Enchant Emerald Jewelry (Lvl-2 Enchant)
         */
        SpellBook.MODERN.register(16, EnchantSpell(27, 37.0,
                hashMapOf(
                        //Begin Jewelry Enchantments
                        ItemNames.EMERALD_RING_1639 to Item(ItemNames.RING_OF_DUELING8_2552),
                        ItemNames.EMERALD_NECKLACE_1658 to Item(ItemNames.BINDING_NECKLACE_5521),
                        ItemNames.EMERALD_AMULET_1696 to Item(ItemNames.AMULET_OF_DEFENCE_1729),
                        ItemNames.EMERALD_BRACELET_11076 to Item(ItemNames.CASTLE_WARS_BRACELET3_11079),
                        //Begin MTA-Specific Enchantments
                        ItemNames.CUBE_6899 to Item(ItemNames.ORB_6902),
                        ItemNames.CYLINDER_6898 to Item(ItemNames.ORB_6902),
                        ItemNames.ICOSAHEDRON_6900 to Item(ItemNames.ORB_6902),
                        ItemNames.PENTAMID_6901 to Item(ItemNames.ORB_6902),
                        ItemNames.DRAGONSTONE_6903 to Item(ItemNames.ORB_6902)
                ),
                arrayOf(Item(Runes.COSMIC_RUNE.id, 1), Item(Runes.AIR_RUNE.id, 3))))

        /**
         * Enchant Ruby Jewelry (Lvl-3 Enchant)
         */
        SpellBook.MODERN.register(28, EnchantSpell(49, 59.0,
                hashMapOf(
                        //Begin Jewelry Enchantments
                        ItemNames.RUBY_RING_1641 to Item(ItemNames.RING_OF_FORGING_2568),
                        ItemNames.RUBY_NECKLACE_1660 to Item(ItemNames.DIGSITE_PENDANT_5_11194),
                        ItemNames.RUBY_AMULET_1698 to Item(ItemNames.AMULET_OF_STRENGTH_1725),
                        ItemNames.RUBY_BRACELET_11085 to Item(ItemNames.INOCULATION_BRACELET_11088),
                        //Begin MTA-Specific Enchantments
                        ItemNames.CUBE_6899 to Item(ItemNames.ORB_6902),
                        ItemNames.CYLINDER_6898 to Item(ItemNames.ORB_6902),
                        ItemNames.ICOSAHEDRON_6900 to Item(ItemNames.ORB_6902),
                        ItemNames.PENTAMID_6901 to Item(ItemNames.ORB_6902),
                        ItemNames.DRAGONSTONE_6903 to Item(ItemNames.ORB_6902)
                ),
                arrayOf(Item(Runes.COSMIC_RUNE.id, 1), Item(Runes.FIRE_RUNE.id, 5))))

        /**
         * Enchant Diamond Jewelry (Lvl-4 Enchant)
         */
        SpellBook.MODERN.register(36, EnchantSpell(57, 67.0,
                hashMapOf(
                        ItemNames.DIAMOND_RING_1643 to Item(ItemNames.RING_OF_LIFE),
                        ItemNames.DIAMOND_NECKLACE_1662 to Item(ItemNames.PHOENIX_NECKLACE_11090),
                        ItemNames.DIAMOND_AMULET_1700 to Item(ItemNames.AMULET_OF_POWER),
                        ItemNames.DIAMOND_BRACELET_11092 to Item(ItemNames.ABYSSAL_BRACELET1_11103),
                        //Begin MTA-Specific Enchantments
                        ItemNames.CUBE_6899 to Item(ItemNames.ORB_6902),
                        ItemNames.CYLINDER_6898 to Item(ItemNames.ORB_6902),
                        ItemNames.ICOSAHEDRON_6900 to Item(ItemNames.ORB_6902),
                        ItemNames.PENTAMID_6901 to Item(ItemNames.ORB_6902),
                        ItemNames.DRAGONSTONE_6903 to Item(ItemNames.ORB_6902)
                ),
                arrayOf(Item(Runes.COSMIC_RUNE.id, 1), Item(Runes.EARTH_RUNE.id, 10))))

        /**
         * Enchant Dragonstone Jewelry (Lvl-5 Enchant)
         */
        SpellBook.MODERN.register(51, EnchantSpell(68, 78.0,
                hashMapOf(
                        //Begin Jewelry Enchantment
                        ItemNames.DRAGONSTONE_RING_1645 to Item(14646),
                        ItemNames.DRAGONSTONE_NECKLACE_1664 to Item(ItemNames.SKILLS_NECKLACE6_11968),
                        ItemNames.DRAGONSTONE_AMULET_1702 to Item(ItemNames.AMULET_OF_GLORY6_11978),
                        ItemNames.DRAGONSTONE_BRACELET_11115 to Item(ItemNames.COMBAT_BRACELET6_11972),
                        //Begin MTA-Specific Enchantments
                        ItemNames.CUBE_6899 to Item(ItemNames.ORB_6902),
                        ItemNames.CYLINDER_6898 to Item(ItemNames.ORB_6902),
                        ItemNames.ICOSAHEDRON_6900 to Item(ItemNames.ORB_6902),
                        ItemNames.PENTAMID_6901 to Item(ItemNames.ORB_6902),
                        ItemNames.DRAGONSTONE_6903 to Item(ItemNames.ORB_6902)
                ),
                arrayOf(Item(Runes.COSMIC_RUNE.id, 1), Item(Runes.WATER_RUNE.id, 15), Item(Runes.EARTH_RUNE.id, 15))))

        /**
         * Enchant Onyx Jewelry (Lvl-6 Enchant)
         */
        SpellBook.MODERN.register(61, EnchantSpell(87, 97.0,
                hashMapOf(
                        //Begin Jewelry Enchantments
                        ItemNames.ONYX_RING_6575 to Item(ItemNames.RING_OF_STONE_6583),
                        ItemNames.ONYX_NECKLACE_6577 to Item(ItemNames.BERSERKER_NECKLACE_11128),
                        ItemNames.ONYX_AMULET_6581 to Item(ItemNames.AMULET_OF_FURY_6585),
                        ItemNames.ONYX_BRACELET_11130 to Item(ItemNames.REGEN_BRACELET_11133),
                        //Begin MTA-Specific Enchantments
                        ItemNames.CUBE_6899 to Item(ItemNames.ORB_6902),
                        ItemNames.CYLINDER_6898 to Item(ItemNames.ORB_6902),
                        ItemNames.ICOSAHEDRON_6900 to Item(ItemNames.ORB_6902),
                        ItemNames.PENTAMID_6901 to Item(ItemNames.ORB_6902),
                        ItemNames.DRAGONSTONE_6903 to Item(ItemNames.ORB_6902)
                ),
                arrayOf(Item(Runes.COSMIC_RUNE.id, 1), Item(Runes.FIRE_RUNE.id, 20), Item(Runes.EARTH_RUNE.id, 20))))
        return this
    }

    companion object {
        /**
         * The animation.
         */
        private val ANIMATION = Animation.create(712)

        /**
         * The graphic.
         */
        private val GRAPHIC = Graphics(114, 96)
    }
}