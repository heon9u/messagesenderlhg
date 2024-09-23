package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import messagesenderlhg.domain.*;
import messagesenderlhg.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ValidateFailed extends AbstractEvent {

    private Long id;
    private String messageId;
    private String policyInfo;
    private String userContact;
    private String mno;
    private String sendTime;
    private String chatbotId;
    private String description;

    public ValidateFailed(Validator aggregate) {
        super(aggregate);
    }

    public ValidateFailed() {
        super();
    }
}
//>>> DDD / Domain Event
