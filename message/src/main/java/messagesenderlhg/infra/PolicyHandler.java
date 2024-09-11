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
    public void wheneverValidateFailed_Saved(
        @Payload ValidateFailed validateFailed
    ) {
        ValidateFailed event = validateFailed;
        System.out.println(
            "\n\n##### listener Saved : " + validateFailed + "\n\n"
        );

        // Sample Logic //
        Message.saved(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SendFailed'"
    )
    public void wheneverSendFailed_Saved(@Payload SendFailed sendFailed) {
        SendFailed event = sendFailed;
        System.out.println("\n\n##### listener Saved : " + sendFailed + "\n\n");

        // Sample Logic //
        Message.saved(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
