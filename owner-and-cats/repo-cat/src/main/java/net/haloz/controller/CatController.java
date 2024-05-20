package net.haloz.controller;

import lombok.AllArgsConstructor;
import net.haloz.dto.CatDto;
import net.haloz.payload.SendingObject;
import net.haloz.producer.Producer;
import net.haloz.service.CatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cat")
@AllArgsConstructor
public class CatController {
    private CatService catService;
    private Producer producer;

    @PostMapping("/add")
    public ResponseEntity<?> addOwner(@RequestBody CatDto catDto) {
        return ResponseEntity.ok(catService.create(catDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOwnerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(catService.read(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") Long id, @RequestBody CatDto catDto) {
        return ResponseEntity.ok(catService.update(id, catDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id) {
        catService.deleteById(id);
        return ResponseEntity.ok(true);
    }
    @GetMapping("/publish/{id}")
    public ResponseEntity<?> publishCatToTopicById(@PathVariable("id") Long id){
        CatDto cat = catService.read(id);
        SendingObject sendingObject = new SendingObject(cat);

        producer.sendCat(sendingObject);

        return ResponseEntity.ok(sendingObject.getGlobalId());
    }
}
