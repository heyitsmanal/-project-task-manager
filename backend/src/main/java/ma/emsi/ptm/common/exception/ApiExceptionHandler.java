package ma.emsi.ptm.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handle(RuntimeException ex, HttpServletRequest req) {
        return ResponseEntity.badRequest().body(
                new ApiError(
                        Instant.now(),
                        400,
                        "Bad Request",
                        ex.getMessage(),
                        req.getRequestURI()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        return ResponseEntity.badRequest().body(
                new ApiError(
                        Instant.now(),
                        400,
                        "Validation Error",
                        ex.getMessage(),
                        req.getRequestURI()
                )
        );
    }
}
