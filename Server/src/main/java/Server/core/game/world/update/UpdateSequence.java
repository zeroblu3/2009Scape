package core.game.world.update;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.game.world.map.RegionManager;
import core.game.world.repository.InitializingNodeList;
import core.game.world.repository.Repository;
import core.net.packet.PacketRepository;
import core.net.packet.context.PlayerContext;
import core.net.packet.out.ClearMinimapFlag;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The entity update sequence.
 * @author Emperor
 */
public final class UpdateSequence {

	List<Player> lobbyList, playersList;
	List<NPC>    npcList;

	/**
	 * The list of active players.
	 */
	private static final InitializingNodeList<Player> RENDERABLE_PLAYERS = new InitializingNodeList<>();

	/**
	 * The executor used.
	 */
	private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	/**
	 * Constructs a new {@code ParallelUpdatingSequence} {@code Object}.
	 */
	public UpdateSequence() {
		/*
		 * empty.
		 */
	}

	/**
	 * Starts the update sequence.
	 * @return {@code True} if we should continue.
	 */
	public void start() {
		lobbyList = Repository.getLobbyPlayers();
		playersList = getRenderablePlayers();
		npcList = Repository.getRenderableNpcs();

		lobbyList.forEach(player -> PacketRepository.send(ClearMinimapFlag.class, new PlayerContext(player)));
		playersList.forEach(player -> {
			try{
				player.tick();
			} catch (Exception e){
				e.printStackTrace();
			}
		});
		npcList.forEach(npc -> {
			try{
				npc.tick();
			} catch (Exception e){
				e.printStackTrace();
			}
		});
	}

	/**
	 * Runs the updating part of the sequence.
	 */
	public void run() {
		final CountDownLatch latch = new CountDownLatch(getRenderablePlayers().size());
		playersList.forEach(player -> EXECUTOR.execute(() -> {
			try {
				player.update();
			} catch (Throwable t) {
				t.printStackTrace();
			}
			latch.countDown();
		}));
		try {
			latch.await(1000L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ends the sequence, calls the {@link Entity#reset()} method..
	 */
	public void end() {
		playersList.forEach(Player::reset);
		npcList.forEach(NPC::reset);
		getRenderablePlayers().sync();
		RegionManager.pulse();
		GroundItemManager.pulse();
	}

	/**
	 * Terminates the update sequence.
	 */
	public final void terminate() {
		EXECUTOR.shutdown();
	}

	/**
	 * Gets the renderablePlayers.
	 * @return The renderablePlayers.
	 */
	public static InitializingNodeList<Player> getRenderablePlayers() {
		return RENDERABLE_PLAYERS;
	}
}