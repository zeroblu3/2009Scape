package org.crandor.game.node.entity.player.link.quest;

import org.crandor.game.component.Component;
import org.crandor.game.node.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class JournalBuilder {
    public static final String BLUE = "<col=08088A>";
    public static final String RED = "<col=8A0808>";
    public static final int JOURNAL_COMPONENT = 275;
    public static List<journalLine> lines = new ArrayList<>();

    public void addLine(String message, int line, boolean strikeCondition){
        message = BLUE + message;
        message = message.replace("!!","</col>" + RED);
        message = message.replace("??","</col>" + BLUE);
        lines.add(new journalLine(message,line,strikeCondition));
    }
    public void addSwitchingLine(String message1, String message2, int line, boolean switchCondition,boolean strikeCondition){
        if(switchCondition){
            addLine(message2,line,strikeCondition);
        } else {
            addLine(message1,line,strikeCondition);
        }
    }

    public void setTitle(String name){
        addLine("!!"+name+"??",2,false);
    }

    public void draw(Player player){
        Object[] linesArray = lines.toArray();
        int length = linesArray.length;
        for (int i = 0; i < 311; i++) {
            player.getPacketDispatch().sendString("" , JOURNAL_COMPONENT, i);
        }
        for(int i = 0; i < length; i++){
            journalLine line = (journalLine) linesArray[i];
            if(line.condition){
                line.clearFormatting();
            }
            player.getPacketDispatch().sendString(line.condition ? "<str>" + line.message + "</str>" : line.message,JOURNAL_COMPONENT,line.line);
        }
        player.getInterfaceManager().open(new Component(JOURNAL_COMPONENT));
    }

    public static class journalLine{
        public String message;
        public int line;
        public boolean condition;
        public journalLine(String message, int line, boolean condition){
            this.message = message;
            this.line = line;
            this.condition = condition;
        }
        public void clearFormatting(){
            this.message = message.replace("</col>" + RED,"");
        }
    }
}
