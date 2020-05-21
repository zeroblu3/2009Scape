package org.crandor.net.packet.in;

import com.mysql.jdbc.StringUtils;
import org.crandor.game.node.entity.player.Player;
import org.crandor.net.packet.IncomingPacket;
import org.crandor.net.packet.IoBuffer;

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
