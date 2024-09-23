package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import messagesenderlhg.ValidatorApplication;
import messagesenderlhg.domain.ValidateFailed;
import messagesenderlhg.domain.ValidateSucceed;

@Entity
@Table(name = "Validator_table")
@Data
//<<< DDD / Aggregate Root
public class Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String messageId;

    private String userContact;

    private String mno;

    private String sendTime;

    private String chatbotId;

    private String policyInfo;

    private String description;

    @PostPersist
    public void onPostPersist() {
        ValidateSucceed validateSucceed = new ValidateSucceed(this);
        validateSucceed.publishAfterCommit();

        ValidateFailed validateFailed = new ValidateFailed(this);
        validateFailed.publishAfterCommit();
    }

    public static ValidatorRepository repository() {
        ValidatorRepository validatorRepository = ValidatorApplication.applicationContext.getBean(
            ValidatorRepository.class
        );
        return validatorRepository;
    }

    //<<< Clean Arch / Port Method
    public static void validateMessage(SendSuccess sendSuccess) {
        //implement business logic here:

        Validator validator = new Validator();
        repository().save(validator);

        if(sendSuccess.isPass()) {
            ValidateSucceed validateSucceed = new ValidateSucceed(validator);
            validateSucceed.publishAfterCommit();
            return;
        }

        ValidateFailed validateFailed = new ValidateFailed(validator);
        validateFailed.publishAfterCommit();

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
