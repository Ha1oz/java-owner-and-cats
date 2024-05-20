package net.haloz.service;

import lombok.AllArgsConstructor;
import net.haloz.dto.OwnerDto;
import net.haloz.entity.Owner;
import net.haloz.exception.OwnerNotFoundException;
import net.haloz.mapper.OwnerMapper;
import net.haloz.repo.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerService {
    private OwnerRepository ownerRepository;
    private OwnerMapper mapper;
    public Owner create(OwnerDto ownerDto) {
        Owner owner = mapper.toOwner(ownerDto);

        return ownerRepository.save(owner);
    }
    public OwnerDto read(Long id) {
        Optional<Owner> ownerOptional = ownerRepository.findById(id);

        if (ownerOptional.isEmpty()) {
            throw new OwnerNotFoundException();
        }

        OwnerDto ownerDto = mapper.toOwnerDto(ownerOptional.get());

        return ownerDto;
    }
    public Owner update(Long id, OwnerDto ownerDto){
        Owner owner = mapper.toOwner(ownerDto);
        owner.setId(id);

        return ownerRepository.save(owner);
    }
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

}
