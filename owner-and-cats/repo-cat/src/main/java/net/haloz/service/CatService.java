package net.haloz.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.haloz.dto.CatDto;
import net.haloz.entity.Cat;
import net.haloz.exception.CatNotFoundException;
import net.haloz.mapper.CatMapper;
import net.haloz.repo.CatRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CatService {
    private CatRepository catRepository;
    private CatMapper mapper;
    public Cat create(CatDto catDto) {
        Cat cat = mapper.toCat(catDto);

        return catRepository.save(cat);
    }
    public CatDto read(Long id) {
        Optional<Cat> catOptional = catRepository.findById(id);

        if (catOptional.isEmpty()) {
            throw new CatNotFoundException();
        }

        CatDto catDto = mapper.toCatDto(catOptional.get());

        return catDto;
    }
    public Cat update(Long id, CatDto ownerDto){
        Cat owner = mapper.toCat(ownerDto);
        owner.setId(id);

        return catRepository.save(owner);
    }
    public void deleteById(Long id) {
        catRepository.deleteById(id);
    }
}
