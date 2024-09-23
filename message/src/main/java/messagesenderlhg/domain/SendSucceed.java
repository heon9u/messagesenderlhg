package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import messagesenderlhg.domain.*;
import messagesenderlhg.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SendSucceed extends AbstractEvent {

    private Long id;

    public SendSucceed(Message aggregate) {
        super(aggregate);
    }

    public SendSucceed() {
        super();
    }
}
//>>> DDD / Domain Event
