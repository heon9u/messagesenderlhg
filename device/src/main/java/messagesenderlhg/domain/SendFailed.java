package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import messagesenderlhg.domain.*;
import messagesenderlhg.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SendFailed extends AbstractEvent {

    private Long id;
    private String messageId;
    private String userContact;
    private String mno;
    private String sendTime;
    private String chatbotId;
    private String description;
    private String result;

    public SendFailed(Device aggregate) {
        super(aggregate);
    }

    public SendFailed() {
        super();
    }
}
//>>> DDD / Domain Event
