package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import messagesenderlhg.ValidateMessageApplication;
import messagesenderlhg.domain.ValidateFailed;
import messagesenderlhg.domain.ValidateSuccess;

@Entity
@Table(name = "Validator_table")
@Data
//<<< DDD / Aggregate Root
public class Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PostPersist
    public void onPostPersist() {
        ValidateSuccess validateSuccess = new ValidateSuccess(this);
        validateSuccess.publishAfterCommit();

        ValidateFailed validateFailed = new ValidateFailed(this);
        validateFailed.publishAfterCommit();
    }

    public static ValidatorRepository repository() {
        ValidatorRepository validatorRepository = ValidateMessageApplication.applicationContext.getBean(
            ValidatorRepository.class
        );
        return validatorRepository;
    }

    //<<< Clean Arch / Port Method
    public static void validateMessage(SentSuccess sentSuccess) {
        //implement business logic here:

        /** Example 1:  new item 
        Validator validator = new Validator();
        repository().save(validator);

        */

        /** Example 2:  finding and process
        
        repository().findById(sentSuccess.get???()).ifPresent(validator->{
            
            validator // do something
            repository().save(validator);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
