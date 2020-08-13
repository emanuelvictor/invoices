package solutions.vcx.invoices;

import solutions.vcx.invoices.application.i18n.ResourceBundleMessageSource;

public abstract class AbstractUnitTests {

    /**
     * Configure internationalization singleton
     */
    protected void setUp() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setAlwaysUseMessageFormat(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames("classpath:i18n/exceptions", "classpath:i18n/labels", "classpath:i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
    }

}
