package messagesenderlhg.domain;

import messagesenderlhg.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "devices", path = "devices")
public interface DeviceRepository
    extends PagingAndSortingRepository<Device, Long> {}
