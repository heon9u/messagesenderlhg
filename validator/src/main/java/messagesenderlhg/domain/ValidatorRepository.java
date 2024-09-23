package messagesenderlhg.domain;

import messagesenderlhg.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "validators",
    path = "validators"
)
public interface ValidatorRepository
    extends PagingAndSortingRepository<Validator, Long> {}
