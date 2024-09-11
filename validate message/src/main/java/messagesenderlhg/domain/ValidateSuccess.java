package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import messagesenderlhg.domain.*;
import messagesenderlhg.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ValidateSuccess extends AbstractEvent {

    private Long id;

    public ValidateSuccess(Validator aggregate) {
        super(aggregate);
    }

    public ValidateSuccess() {
        super();
    }
}
//>>> DDD / Domain Event
