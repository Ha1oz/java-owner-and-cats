package net.haloz.controller;

import lombok.AllArgsConstructor;
import net.haloz.request.RequestSender;
import net.haloz.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
@AllArgsConstructor
public class Controller {
    private ClientService clientService;
    private RequestSender requestSender;

    @GetMapping("/owner/{id}")
    public ResponseEntity<?> getOwnerById(@PathVariable Long id) {
        String response = requestSender.sendPublishOwnerToTopic(id);
        String result = clientService.getMessageWithCorrectUUID(response);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/cat/{id}")
    public ResponseEntity<?> getCatById(@PathVariable Long id) {
        String response = requestSender.sendPublishCatToTopic(id);
        String result = clientService.getMessageWithCorrectUUID(response);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        String result = clientService.getAll();
        return ResponseEntity.ok(result);
    }
}
