package core.net.packet.in;

import core.game.node.entity.player.Player;
import core.net.packet.IncomingPacket;
import core.net.packet.IoBuffer;

/**
 * Handles the quick chat packet.
 * @author Vexia
 *
 */
public class QuickChatPacket implements IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		
	}

}
