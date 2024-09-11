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
public class MyTemplatesViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private MyTemplatesRepository myTemplatesRepository;
    //>>> DDD / CQRS
}
