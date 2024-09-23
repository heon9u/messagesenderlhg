package messagesenderlhg.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import messagesenderlhg.config.kafka.KafkaProcessor;
import messagesenderlhg.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    MessageRepository messageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ValidateFailed'"
    )
    public void wheneverValidateFailed_SaveHistory(
        @Payload ValidateFailed validateFailed
    ) {
        ValidateFailed event = validateFailed;
        System.out.println(
            "\n\n##### listener SaveHistory : " + validateFailed + "\n\n"
        );

        // Comments //
        //메시지 발송 이력 저장
        // - 검증에 실패한 경우
        // - 발송에 실패한 경우

        // Sample Logic //
        Message.saveHistory(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SendFailed'"
    )
    public void wheneverSendFailed_SaveHistory(@Payload SendFailed sendFailed) {
        SendFailed event = sendFailed;
        System.out.println(
            "\n\n##### listener SaveHistory : " + sendFailed + "\n\n"
        );

        // Comments //
        //메시지 발송 이력 저장
        // - 검증에 실패한 경우
        // - 발송에 실패한 경우

        // Sample Logic //
        Message.saveHistory(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SendSucceed'"
    )
    public void wheneverSendSucceed_SaveHistory(
        @Payload SendSucceed sendSucceed
    ) {
        SendSucceed event = sendSucceed;
        System.out.println(
            "\n\n##### listener SaveHistory : " + sendSucceed + "\n\n"
        );

        // Comments //
        //메시지 발송 이력 저장
        // - 검증에 실패한 경우
        // - 발송에 실패한 경우

        // Sample Logic //
        Message.saveHistory(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
