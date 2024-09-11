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
        condition = "headers['type']=='SentSuccess'"
    )
    public void wheneverSentSuccess_ValidateMessage(
        @Payload SentSuccess sentSuccess
    ) {
        SentSuccess event = sentSuccess;
        System.out.println(
            "\n\n##### listener ValidateMessage : " + sentSuccess + "\n\n"
        );

        // Sample Logic //
        Validator.validateMessage(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
