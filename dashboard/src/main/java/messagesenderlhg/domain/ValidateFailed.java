package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import messagesenderlhg.infra.AbstractEvent;

@Data
public class ValidateFailed extends AbstractEvent {

    private Long id;
    private String messageId;
    private String policyInfo;
    private String userContact;
    private String mno;
    private String sendTime;
    private String chatbotId;
    private String description;
}
