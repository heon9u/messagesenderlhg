package messagesenderlhg.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import messagesenderlhg.DeviceApplication;
import messagesenderlhg.domain.SendFailed;
import messagesenderlhg.domain.SendSucceed;

@Entity
@Table(name = "Device_table")
@Data
//<<< DDD / Aggregate Root
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String messageId;

    private String userContact;

    private String mno;

    private String sendTime;

    private String chatbotId;

    private String description;

    private String result;

    @PostPersist
    public void onPostPersist() {
        SendSucceed sendSucceed = new SendSucceed(this);
        sendSucceed.publishAfterCommit();

        SendFailed sendFailed = new SendFailed(this);
        sendFailed.publishAfterCommit();
    }

    public static DeviceRepository repository() {
        DeviceRepository deviceRepository = DeviceApplication.applicationContext.getBean(
            DeviceRepository.class
        );
        return deviceRepository;
    }

    //<<< Clean Arch / Port Method
    public static void send(ValidateSucceed validateSucceed) {
        //implement business logic here:

        /** Example 1:  new item 
        Device device = new Device();
        repository().save(device);

        SendSucceed sendSucceed = new SendSucceed(device);
        sendSucceed.publishAfterCommit();
        SendFailed sendFailed = new SendFailed(device);
        sendFailed.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(validateSucceed.get???()).ifPresent(device->{
            
            device // do something
            repository().save(device);

            SendSucceed sendSucceed = new SendSucceed(device);
            sendSucceed.publishAfterCommit();
            SendFailed sendFailed = new SendFailed(device);
            sendFailed.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void sendCancel(Canceled canceled) {
        //implement business logic here:

        /** Example 1:  new item 
        Device device = new Device();
        repository().save(device);

        */

        /** Example 2:  finding and process
        
        repository().findById(canceled.get???()).ifPresent(device->{
            
            device // do something
            repository().save(device);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
