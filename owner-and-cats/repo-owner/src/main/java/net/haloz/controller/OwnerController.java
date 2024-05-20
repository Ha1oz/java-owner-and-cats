package net.haloz.controller;

import lombok.AllArgsConstructor;
import net.haloz.dto.OwnerDto;
import net.haloz.entity.Owner;
import net.haloz.payload.SendingObject;
import net.haloz.producer.Producer;
import net.haloz.service.OwnerService;
import org.apache.kafka.common.KafkaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
@AllArgsConstructor
public class OwnerController {
    private OwnerService ownerService;
    private Producer producer;

    @PostMapping("/add")
    public ResponseEntity<?> addOwner(@RequestBody OwnerDto ownerDto) {
        return ResponseEntity.ok(ownerService.create(ownerDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOwnerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ownerService.read(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") Long id, @RequestBody OwnerDto ownerDto) {
        return ResponseEntity.ok(ownerService.update(id, ownerDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id) {
        ownerService.deleteById(id);
        return ResponseEntity.ok(true);
    }
    @GetMapping("/publish/{id}")
    public ResponseEntity<?> publishToTopicOwnerById(@PathVariable("id") Long id){
        OwnerDto owner = ownerService.read(id);
        SendingObject sendingObject = new SendingObject(owner);

        producer.sendOwner(sendingObject);

        return ResponseEntity.ok(sendingObject.getGlobalId());
    }

}
