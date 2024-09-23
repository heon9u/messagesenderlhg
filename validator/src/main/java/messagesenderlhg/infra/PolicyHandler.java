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
    ValidatorRepository validatorRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SendSuccess'"
    )
    public void wheneverSendSuccess_ValidateMessage(
        @Payload SendSuccess sendSuccess
    ) {
        SendSuccess event = sendSuccess;
        System.out.println(
            "\n\n##### listener ValidateMessage : " + sendSuccess + "\n\n"
        );

        // Comments //
        //메시지 검증기
        // - 브랜드와 챗봇
        // - 수신번호와 통신사
        // - 광고성 문자

        // Sample Logic //
        Validator.validateMessage(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
