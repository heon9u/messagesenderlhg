package messagesenderlhg.domain;

import java.util.*;
import lombok.*;
import messagesenderlhg.domain.*;
import messagesenderlhg.infra.AbstractEvent;

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

    public boolean isPass() {
        return this.userContact.equals("01012341234");
    }
}
