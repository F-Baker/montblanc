package montblanc.exceptions;

import montblanc.utils.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleAllExceptionMethod(MethodArgumentNotValidException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getBindingResult().getAllErrors());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException ex) {
        GenericResponse response = new GenericResponse(ex.getMessage(), HttpStatus.NOT_FOUND, new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> userExistException(UserAlreadyExistException ex) {
        GenericResponse response = new GenericResponse(ex.getMessage(), HttpStatus.CONFLICT, new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)

    public ResponseEntity<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> methodArgumentConversionNotSupportedException(MethodArgumentConversionNotSupportedException ex) {
        GenericResponse response = new GenericResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
