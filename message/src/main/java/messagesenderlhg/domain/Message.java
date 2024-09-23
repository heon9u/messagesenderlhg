package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import messagesenderlhg.MessageApplication;
import messagesenderlhg.domain.SendSuccess;

@Entity
@Table(name = "Message_table")
@Data
//<<< DDD / Aggregate Root
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String messageId;

    private String userContact;

    private String mno;

    private Date sendTime;

    private String chatbotId;

    private String description;

    @PostPersist
    public void onPostPersist() {
        SendSuccess sendSuccess = new SendSuccess(this);
        sendSuccess.publishAfterCommit();
    }

    public static MessageRepository repository() {
        MessageRepository messageRepository = MessageApplication.applicationContext.getBean(
            MessageRepository.class
        );
        return messageRepository;
    }

    public void send() {
        //implement business logic here:

        SendSuccess sendSuccess = new SendSuccess(this);
        sendSuccess.publishAfterCommit();
    }
}
//>>> DDD / Aggregate Root
