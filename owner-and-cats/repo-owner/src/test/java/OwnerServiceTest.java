import net.haloz.dto.OwnerDto;
import net.haloz.entity.Owner;
import net.haloz.mapper.OwnerMapper;
import net.haloz.repo.OwnerRepository;
import net.haloz.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OwnerServiceTest {
    @Mock
    private OwnerRepository mockRepo;
    @Mock
    private OwnerMapper mockMapper;

    private final OwnerService ownerService;

    public OwnerServiceTest() {
        MockitoAnnotations.openMocks(this);
        ownerService = new OwnerService(mockRepo, mockMapper);
    }

    @Test
    public void createTest() {
        Owner owner = new Owner();
        owner.setName("Name-1");

        when(mockRepo.save(any(Owner.class))).thenReturn(owner);
        when(mockMapper.toOwner(any(OwnerDto.class))).thenReturn(owner);

        Owner addedOwner = ownerService.create(new OwnerDto());

        assertEquals("Name-1", addedOwner.getName());

        verify(mockRepo, times(1)).save(any(Owner.class));
    }
    @Test
    public void readTest() {
        String name = "Name-1";

        Owner owner = new Owner();
        owner.setId(1L);
        owner.setName(name);

        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setName(name);

        when(mockRepo.findById(owner.getId())).thenReturn(Optional.of(owner));
        when(mockMapper.toOwnerDto(any(Owner.class))).thenReturn(ownerDto);

        OwnerDto foundOwnerDto = ownerService.read(owner.getId());

        assertEquals(owner.getName(), foundOwnerDto.getName());

        verify(mockRepo, times(1)).findById(anyLong());
    }
    @Test
    public void updateTest() {
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setName("Name-1");

        when(mockRepo.save(any(Owner.class))).thenReturn(owner);
        when(mockMapper.toOwner(any(OwnerDto.class))).thenReturn(owner);

        Owner addedOwner = ownerService.update(owner.getId(), new OwnerDto());

        assertEquals("Name-1", addedOwner.getName());

        verify(mockRepo, times(1)).save(any(Owner.class));
    }
    @Test
    public void deleteTest() {
        ownerService.deleteById(1L);

        verify(mockRepo, times(1)).deleteById(1L);
    }
}
