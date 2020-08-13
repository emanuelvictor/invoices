package solutions.vcx.invoices.application.aspect;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import solutions.vcx.invoices.application.exceptions.InvalidDocumentException;

/**
 * Class RestResponseEntityExceptionHandler, it serves to catch the errors of the application and to treat them.
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class ResponseEntityExceptionHandlerImpl extends ResponseEntityExceptionHandler {

    /**
     *
     */
    private final MessageSource messageSource;

    /**
     * @param exception InvalidDocumentException
     * @param request   WebRequest
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(InvalidDocumentException.class)
    public ResponseEntity<Object> handleInvalidDocumentException(final InvalidDocumentException exception, final WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * @param exception Result
     * @param request   WebRequest
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleException(final org.springframework.dao.EmptyResultDataAccessException exception, final WebRequest request) {
        final String message = messageSource.getMessage("repository.emptyResult", null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(exception, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
