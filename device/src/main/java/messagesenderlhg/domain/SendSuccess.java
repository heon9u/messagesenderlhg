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

    public SendSuccess(Device aggregate) {
        super(aggregate);
    }

    public SendSuccess() {
        super();
    }
}
//>>> DDD / Domain Event
