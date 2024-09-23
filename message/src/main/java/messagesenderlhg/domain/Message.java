package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import messagesenderlhg.MessageApplication;
import messagesenderlhg.domain.Canceled;
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

    private String sendTime;

    private String chatbotId;

    private String description;

    @PostPersist
    public void onPostPersist() {
        SendSuccess sendSuccess = new SendSuccess(this);
        sendSuccess.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        Canceled canceled = new Canceled(this);
        canceled.publishAfterCommit();
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

    public void cancel() {
        //implement business logic here:

        Canceled canceled = new Canceled(this);
        canceled.publishAfterCommit();
    }
}
//>>> DDD / Aggregate Root
