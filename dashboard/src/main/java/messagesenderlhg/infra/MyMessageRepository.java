package messagesenderlhg.infra;

import java.util.List;
import messagesenderlhg.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "myMessages",
    path = "myMessages"
)
public interface MyMessageRepository
    extends PagingAndSortingRepository<MyMessage, Long> {}
