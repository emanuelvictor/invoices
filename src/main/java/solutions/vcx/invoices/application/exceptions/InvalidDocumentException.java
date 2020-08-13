package solutions.vcx.invoices.application.exceptions;

import lombok.Getter;
import org.springframework.context.i18n.LocaleContextHolder;
import solutions.vcx.invoices.application.i18n.MessageSourceHolder;

public class InvalidDocumentException extends RuntimeException {

    /**
     *
     */
    @Getter
    private final String message = MessageSourceHolder.getMessage("invalid.document", null, LocaleContextHolder.getLocale());

}
