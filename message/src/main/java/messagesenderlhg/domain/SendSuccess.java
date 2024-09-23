package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import messagesenderlhg.domain.*;
import messagesenderlhg.infra.AbstractEvent;

//<<< DDD / Domain Event
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

    public SendSuccess(Message aggregate) {
        super(aggregate);
    }

    public SendSuccess() {
        super();
    }
}
//>>> DDD / Domain Event
