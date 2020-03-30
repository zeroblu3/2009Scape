package org.crandor.net.packet.in;

import org.crandor.game.node.entity.player.Player;
import org.crandor.net.packet.IncomingPacket;
import org.crandor.net.packet.IoBuffer;

/**
 * The incoming packet for holding shift, needs to be reworked OOF
 * @author jamix77
 */
public class HoldingShiftPacket implements IncomingPacket {

	@Override
	public void decode(final Player player, int opcode, IoBuffer buffer) {
		if (player == null) {
			return;
		}
		int yeet = buffer.getInt();
		if (yeet == 0) {
			player.getSettings().setHoldingShift(false);
		}
		if (yeet == 1) {
			player.getSettings().setHoldingShift(true);
		}
		player.debug("Shift packet = " + yeet);
	}
		
}