package messagesenderlhg.infra;

import java.util.List;
import messagesenderlhg.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "myTemplates",
    path = "myTemplates"
)
public interface MyTemplatesRepository
    extends PagingAndSortingRepository<MyTemplates, Long> {}
