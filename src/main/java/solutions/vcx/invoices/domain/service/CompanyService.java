package solutions.vcx.invoices.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solutions.vcx.invoices.application.exceptions.InvalidDocumentException;
import solutions.vcx.invoices.domain.entities.Company;
import solutions.vcx.invoices.domain.repository.ICompanyRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    /**
     *
     */
    private final ICompanyRepository companyRepository;

    /**
     * @param filters
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<Company> listByFilters(final String filters, final Pageable pageable) {
        throw new InvalidDocumentException();
    }

    /**
     * @param company Company
     * @return Company
     */
    @Transactional
    public Company save(final Company company) {
        return this.companyRepository.save(company);
    }

    /**
     * @param document String
     * @return Optional<Company>
     */
    @Transactional(readOnly = true)
    public Optional<Company> findById(final String document) {
        return this.companyRepository.findById(document);
    }

    /**
     * @param pageable Pageable
     * @return Page<Company>
     */
    @Transactional(readOnly = true)
    public Page<Company> findAll(final Pageable pageable) {
        return this.companyRepository.findAll(pageable);
    }

    @Transactional
    public void deleteById(final String document) {
        this.companyRepository.deleteById(document);
    }

}
