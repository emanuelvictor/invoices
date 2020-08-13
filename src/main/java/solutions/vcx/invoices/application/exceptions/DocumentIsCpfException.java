package solutions.vcx.invoices.application.exceptions;

import lombok.Getter;
import org.springframework.context.i18n.LocaleContextHolder;
import solutions.vcx.invoices.application.i18n.MessageSourceHolder;

public class DocumentIsCpfException extends InvalidDocumentException {

    /**
     *
     */
    @Getter
    private final String message = MessageSourceHolder.getMessage("document.is-a-cpf", null, LocaleContextHolder.getLocale());

}
