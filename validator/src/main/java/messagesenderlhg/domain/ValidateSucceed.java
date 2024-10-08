package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import messagesenderlhg.domain.*;
import messagesenderlhg.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ValidateSucceed extends AbstractEvent {

    private Long id;
    private String messageId;
    private String policyInfo;
    private String userContact;
    private String mno;
    private String sendTime;
    private String chatbotId;
    private String description;

    public ValidateSucceed(Validator aggregate) {
        super(aggregate);
    }

    public ValidateSucceed() {
        super();
    }
}
//>>> DDD / Domain Event
