package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import messagesenderlhg.infra.AbstractEvent;

@Data
public class Canceled extends AbstractEvent {

    private Long id;
}
