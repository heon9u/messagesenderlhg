package messagesenderlhg.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import messagesenderlhg.config.kafka.KafkaProcessor;
import messagesenderlhg.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MyMessageViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private MyMessageRepository myMessageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenValidateFailed_then_CREATE_1(
        @Payload ValidateFailed validateFailed
    ) {
        try {
            if (!validateFailed.validate()) return;

            // view 객체 생성
            MyMessage myMessage = new MyMessage();
            // view 객체에 이벤트의 Value 를 set 함
            myMessage.setId(validateFailed.getId());
            myMessage.setMessageId(validateFailed.getMessageId());
            myMessage.setUserContact(validateFailed.getUserContact());
            myMessage.setMno(validateFailed.getMno());
            myMessage.setSendTime(validateFailed.getSendTime());
            myMessage.setChatbotId(validateFailed.getChatbotId());
            myMessage.setDescription(validateFailed.getDescription());
            myMessage.setResult("validate failed");
            // view 레파지 토리에 save
            myMessageRepository.save(myMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSendFailed_then_CREATE_2(@Payload SendFailed sendFailed) {
        try {
            if (!sendFailed.validate()) return;

            // view 객체 생성
            MyMessage myMessage = new MyMessage();
            // view 객체에 이벤트의 Value 를 set 함
            myMessage.setId(Long.valueOf(sendFailed.getMessageId()));
            myMessage.setMessageId(sendFailed.getMessageId());
            myMessage.setUserContact(sendFailed.getUserContact());
            myMessage.setMno(sendFailed.getMno());
            myMessage.setSendTime(sendFailed.getSendTime());
            myMessage.setChatbotId(sendFailed.getChatbotId());
            myMessage.setDescription(sendFailed.getDescription());
            myMessage.setResult("send failed");
            // view 레파지 토리에 save
            myMessageRepository.save(myMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSendSucceed_then_CREATE_3(
        @Payload SendSucceed sendSucceed
    ) {
        try {
            if (!sendSucceed.validate()) return;

            // view 객체 생성
            MyMessage myMessage = new MyMessage();
            // view 객체에 이벤트의 Value 를 set 함
            myMessage.setId(sendSucceed.getId());
            myMessage.setMessageId(sendSucceed.getMessageId());
            myMessage.setUserContact(sendSucceed.getUserContact());
            myMessage.setMno(sendSucceed.getMno());
            myMessage.setSendTime(String.valueOf(sendSucceed.getSendTime()));
            myMessage.setChatbotId(sendSucceed.getChatbotId());
            myMessage.setDescription(sendSucceed.getDescription());
            myMessage.setResult("send succeed");
            // view 레파지 토리에 save
            myMessageRepository.save(myMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
