package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import messagesenderlhg.domain.*;
import messagesenderlhg.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SentSuccess extends AbstractEvent {

    private Long id;

    public SentSuccess(Message aggregate) {
        super(aggregate);
    }

    public SentSuccess() {
        super();
    }
}
//>>> DDD / Domain Event
