package org.topicsswe.userservice.application;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.topicsswe.userservice.domain.exceptions.NoUserFoundException;

import java.util.HashMap;
import java.util.Map;

//Taken from
// https://medium.com/@bubu.tripathy/effective-exception-handling-6c0ce043d96f#:~:text=Spring%20Boot%20provides%20the%20%40ControllerAdvice,mechanism%20for%20an%20entire%20application.&text=In%20this%20example%2C%20we%20define%20a%20global,handler%20using%20the%20%40ControllerAdvice%20annotation.
@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "An error occurred");

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<Object> handleNoUserFound(NoUserFoundException ex) {
//        return new ResponseEntity<>(ex.)
//        TODO NoUserFound exception handler
        return null;
    }

}
