package core.net.packet.in;

import core.game.node.entity.player.Player;
import core.net.packet.IncomingPacket;
import core.net.packet.IoBuffer;

public class MapClosedPacket implements IncomingPacket {
    @Override
    public void decode(Player player, int opcode, IoBuffer buffer) {
        //I'm just leaving this blank because I have no clue what this packet does.
        //getting a short from the buffer returns 345
        //getting an int from the buffer returns 22657011
        //getting a string causes a buffer underflow
        //getting a long causes a buffer underflow
    }
}
