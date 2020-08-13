package solutions.vcx.invoices.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solutions.vcx.invoices.application.exceptions.InvalidDocumentException;
import solutions.vcx.invoices.domain.entities.Company;
import solutions.vcx.invoices.domain.repository.ICompanyRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {


    /**
     *
     */
    private final MessageSource messageSource;

    /**
     *
     */
    private final ICompanyRepository producerRepository;

    /**
     * @param filters
     * @param pageable
     * @return
     */
    public Page<Company> listByFilters(final String filters, final Pageable pageable) {
        throw new InvalidDocumentException();
    }
}
