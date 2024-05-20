package net.haloz.handler;

import net.haloz.exception.CatNotFoundException;
import org.apache.kafka.common.KafkaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(CatNotFoundException.class)
    public ResponseEntity<Object> handleOwnerNotFoundException(CatNotFoundException e) {
        LOGGER.error(e.toString());
        return new ResponseEntity<>("Owner is not found.", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(KafkaException.class)
    public ResponseEntity<Object> handleOwnerNotFoundException(KafkaException e) {
        LOGGER.error(e.toString());
        return new ResponseEntity<>("Something goes wrong while message sent", HttpStatus.NOT_FOUND);
    }
}
