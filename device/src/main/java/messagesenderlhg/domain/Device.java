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

    // @PostPersist
    // public void onPostPersist() {
    //     SendSucceed sendSucceed = new SendSucceed(this);
    //     sendSucceed.publishAfterCommit();
    // 
    //     SendFailed sendFailed = new SendFailed(this);
    //     sendFailed.publishAfterCommit();
    // }

    public static DeviceRepository repository() {
        DeviceRepository deviceRepository = DeviceApplication.applicationContext.getBean(
            DeviceRepository.class
        );
        return deviceRepository;
    }

    //<<< Clean Arch / Port Method
    public static void send(ValidateSucceed validateSucceed) {
        //implement business logic here:

        Device device = new Device();
        device.setMessageId(validateSucceed.getMessageId());
        device.setChatbotId(validateSucceed.getChatbotId());
        device.setMno(validateSucceed.getMno());
        device.setDescription(validateSucceed.getDescription());
        device.setSendTime(validateSucceed.getSendTime());
        device.setUserContact(validateSucceed.getUserContact());
        
        repository().save(device);

        if(validateSucceed.isPass()) {
            System.out.println("#### 검증 성공 ####");
            device.setResult("send succeed");
            SendSucceed sendSucceed = new SendSucceed(device);
            sendSucceed.publishAfterCommit();
            return;
        } else {
            System.out.println("#### 검증 실패 ####");
            device.setResult("send failed");
            SendFailed sendFailed = new SendFailed(device);
            sendFailed.publishAfterCommit();
        }
        
    }
    //>>> Clean Arch / Port Method
    
    //<<< Clean Arch / Port Method
    public static void sendCancel(Canceled canceled) {
        //implement business logic here:

        Device device = repository.findById(canceled.getId());
        device.setResult("CANCEL");

        repository().save(device);

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
