//package plugin.consumable.food;
//
//import org.crandor.game.content.global.consumable.ConsumableProperties;
//import org.crandor.game.content.global.consumable.CookingProperties;
//import org.crandor.game.content.global.consumable.Food;
//import plugin.tutorial.TutorialSession;
//import plugin.tutorial.TutorialStage;
//import org.crandor.game.node.Node;
//import org.crandor.game.node.entity.player.Player;
//import org.crandor.game.node.entity.player.link.diary.DiaryType;
//import org.crandor.plugin.InitializablePlugin;
//import org.crandor.game.node.object.GameObject;
//
///**
// * Represents the bread food that is based in it's own class because of the
// * conditions related to Tutorial Island.
// * @author 'Vexia
// * @version 1.0
// */
//@InitializablePlugin
//public final class BreadPlugin extends Food {
//
//	/**
//	 * Represents the cooking properties of bread.
//	 */
//	private static final BreadProperties PROPERTIES = new BreadProperties();
//
//	/**
//	 * Constructs a new {@code Bread} {@code Object}.
//	 */
//	public BreadPlugin() {
//		super(2309, 2307, 2311, new ConsumableProperties(5), PROPERTIES);
//	}
//
//	@Override
//	public boolean interact(final Player player, final Node node) {
//		int stage = TutorialSession.getExtension(player).getStage();
//		if (stage < TutorialSession.MAX_STAGE) {
//			cook(player, (GameObject) node, 1);
//			return false;
//		}
//		return true;
//	}
//
//	/**
//	 * Represents the bread cooking properties.
//	 * @author 'Vexia
//	 * @date 22/12/2013
//	 */
//	public static class BreadProperties extends CookingProperties {
//
//		/**
//		 * Constructs a new {@code Bread} {@code Object}.
//		 */
//		public BreadProperties() {
//			super(1, 40, 40, true);
//		}
//
//		@Override
//		public boolean cook(final Food food, final Player player, final GameObject object) {
//			if (super.cook(food, player, object)) {
//				return true;
//			}
//			return false;
//		}
//	}
//}
