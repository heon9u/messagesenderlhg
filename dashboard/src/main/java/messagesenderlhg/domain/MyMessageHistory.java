package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "MyMessageHistory_table")
@Data
public class MyMessageHistory {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String messageId;
    private String userContact;
    private String mno;
    private String sendTime;
    private String chatbotId;
    private String result;
    private String description;
}
