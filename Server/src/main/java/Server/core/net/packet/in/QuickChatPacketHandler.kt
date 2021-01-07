package core.net.packet.`in`

import core.game.interaction.QCRepository
import core.game.node.entity.player.Player
import core.net.packet.IncomingPacket
import core.net.packet.IoBuffer

/**
 * Decodes the quick chat packet
 * @author Ceikry
 */
class QuickChatPacketHandler : IncomingPacket {
    override fun decode(player: Player?, opcode: Int, buffer: IoBuffer?) {
        val x = buffer?.toByteBuffer()
        val searchResponse = x?.array()?.size == 5
        player?.debug("Quick chat type ${if(searchResponse) "search" else "selection"}")

        var searchId: Int? = -1

        buffer?.get()
        val type: Int? = buffer?.get()
        val id: Int? = buffer?.get()
        if(searchResponse) searchId = buffer?.short
        QCRepository.sendQC(player,type,id,searchId)
    }
}