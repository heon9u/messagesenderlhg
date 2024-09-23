package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import messagesenderlhg.domain.*;
import messagesenderlhg.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class Canceled extends AbstractEvent {

    private Long id;

    public Canceled(Message aggregate) {
        super(aggregate);
    }

    public Canceled() {
        super();
    }
}
//>>> DDD / Domain Event
