package solutions.vcx.invoices.application.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class InternationalizationConfig {

    /**
     * @return MessageSource
     */
    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setAlwaysUseMessageFormat(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames("classpath:i18n/exceptions", "classpath:i18n/labels", "classpath:i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
}
