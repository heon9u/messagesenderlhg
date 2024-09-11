package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import messagesenderlhg.domain.*;
import messagesenderlhg.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SavedSuccess extends AbstractEvent {

    private Long id;

    public SavedSuccess(Message aggregate) {
        super(aggregate);
    }

    public SavedSuccess() {
        super();
    }
}
//>>> DDD / Domain Event
