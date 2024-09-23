package messagesenderlhg.infra;

import messagesenderlhg.domain.*;
import messagesenderlhg.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MyMessageHistoryViewHandler {

//<<< DDD / CQRS
    @Autowired
    private MyMessageHistoryRepository myMessageHistoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenValidateFailed_then_CREATE_1 (@Payload ValidateFailed validateFailed) {
        try {

            if (!validateFailed.validate()) return;

            // view 객체 생성
            MyMessageHistory myMessageHistory = new MyMessageHistory();
            // view 객체에 이벤트의 Value 를 set 함
            myMessageHistory.setId(validateFailed.getId());
            myMessageHistory.setMessageId(validateFailed.getMessageId());
            myMessageHistory.setUserContact(validateFailed.getUserContact());
            myMessageHistory.setMno(validateFailed.getMno());
            myMessageHistory.setSendTime(validateFailed.getSendTime());
            myMessageHistory.setChatbotId(validateFailed.getChatbotId());
            myMessageHistory.setDescription(validateFailed.getDescription());
            myMessageHistory.setResult(validate failed);
            // view 레파지 토리에 save
            myMessageHistoryRepository.save(myMessageHistory);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenSendFailed_then_CREATE_2 (@Payload SendFailed sendFailed) {
        try {

            if (!sendFailed.validate()) return;

            // view 객체 생성
            MyMessageHistory myMessageHistory = new MyMessageHistory();
            // view 객체에 이벤트의 Value 를 set 함
            myMessageHistory.setId(Long.valueOf(sendFailed.getMessageId()));
            myMessageHistory.setMessageId(sendFailed.getMessageId());
            myMessageHistory.setUserContact(sendFailed.getUserContact());
            myMessageHistory.setMno(sendFailed.getMno());
            myMessageHistory.setSendTime(sendFailed.getSendTime());
            myMessageHistory.setChatbotId(sendFailed.getChatbotId());
            myMessageHistory.setDescription(sendFailed.getDescription());
            myMessageHistory.setResult(send failed);
            // view 레파지 토리에 save
            myMessageHistoryRepository.save(myMessageHistory);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenSendSucceed_then_CREATE_3 (@Payload SendSucceed sendSucceed) {
        try {

            if (!sendSucceed.validate()) return;

            // view 객체 생성
            MyMessageHistory myMessageHistory = new MyMessageHistory();
            // view 객체에 이벤트의 Value 를 set 함
            myMessageHistory.setId(sendSucceed.getId());
            myMessageHistory.setMessageId(sendSucceed.getMessageId());
            myMessageHistory.setUserContact(sendSucceed.getUserContact());
            myMessageHistory.setMno(sendSucceed.getMno());
            myMessageHistory.setSendTime(String.valueOf(sendSucceed.getSendTime()));
            myMessageHistory.setChatbotId(sendSucceed.getChatbotId());
            myMessageHistory.setDescription(sendSucceed.getDescription());
            myMessageHistory.setResult(send succeed);
            // view 레파지 토리에 save
            myMessageHistoryRepository.save(myMessageHistory);

        }catch (Exception e){
            e.printStackTrace();
        }
    }




//>>> DDD / CQRS
}

