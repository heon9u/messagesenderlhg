package messagesenderlhg.domain;

import java.util.*;
import lombok.*;
import messagesenderlhg.domain.*;
import messagesenderlhg.infra.AbstractEvent;

@Data
@ToString
public class SendSuccess extends AbstractEvent {

    private Long id;
    private String messageId;
    private String userContact;
    private String mno;
    private Date sendTime;
    private String chatbotId;
    private String description;

    public boolean isPass() {
        return chatbotId.equals("0801234567");
    }
}
