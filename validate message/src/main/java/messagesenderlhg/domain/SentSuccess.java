package messagesenderlhg.domain;

import java.util.*;
import lombok.*;
import messagesenderlhg.domain.*;
import messagesenderlhg.infra.AbstractEvent;

@Data
@ToString
public class SentSuccess extends AbstractEvent {

    private Long id;
}
