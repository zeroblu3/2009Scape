package core.net.packet.in;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.Rights;
import core.net.packet.IncomingPacket;
import core.net.packet.IoBuffer;

/**
 * Handles the idle packet handler.
 * @author Emperor
 */
public final class IdlePacketHandler implements IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		if (player.getDetails().getRights() != Rights.ADMINISTRATOR) {
			player.getPacketDispatch().sendLogout();
		}
	}

}