package org.hbrs.se2.project.softwaree.control.factories;

import com.vaadin.flow.component.messages.MessageListItem;
import org.hbrs.se2.project.softwaree.dtos.MessageDTO;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MessageListItemFactory {
    private MessageListItemFactory() {

    }
    public static List<MessageListItem> createMessageListItemList(List<MessageDTO> messages){
        List<MessageListItem> list = new ArrayList<>();
        for(MessageDTO message: messages ){
            list.add(new MessageListItem(message.getMessage(),message.getTimeSent().toInstant(),message.getName()));
        }
        Collections.sort(list, Comparator.comparing(MessageListItem::getTime));
        return list;
    }

}
