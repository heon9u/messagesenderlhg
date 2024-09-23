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
    DeviceRepository deviceRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ValidateSucceed'"
    )
    public void wheneverValidateSucceed_Send(
        @Payload ValidateSucceed validateSucceed
    ) {
        ValidateSucceed event = validateSucceed;
        System.out.println(
            "\n\n##### listener Send : " + validateSucceed + "\n\n"
        );

        // Sample Logic //
        Device.send(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Canceled'"
    )
    public void wheneverCanceled_SendCancel(@Payload Canceled canceled) {
        Canceled event = canceled;
        System.out.println(
            "\n\n##### listener SendCancel : " + canceled + "\n\n"
        );

        // Sample Logic //
        Device.sendCancel(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
