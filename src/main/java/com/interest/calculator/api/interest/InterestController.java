package com.interest.calculator.api.interest;

import com.interest.calculator.api.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterestController {
    private final InterestService interestService;
    
    @PostMapping(value = "/interest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Payment>> calculateInterest(@Valid @RequestBody CreditRequest creditRequest) throws BadRequestException {
    
        List<Payment> payments = interestService.calculateInterest(creditRequest);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(String.format("400#%s", fieldName), errorMessage);
        });
        return errors;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public Map<String, String> handleBadRequestExceptionException(
        BadRequestException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getCode(), ex.getMessage());
        return errors;
    }
}
