package messagesenderlhg.infra;

import messagesenderlhg.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ValidatorHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Validator>> {

    @Override
    public EntityModel<Validator> process(EntityModel<Validator> model) {
        return model;
    }
}
