package solutions.vcx.invoices.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import solutions.vcx.invoices.domain.entities.Company;

import java.util.Optional;

/**
 *
 */
public interface ICompanyRepository extends JpaRepository<Company, String> {

    /**
     * @param companyName String
     * @param pageable    Pageable
     * @return Page<Company>
     */
    Page<Company> findAllByFantasyNameContaining(final String companyName, final Pageable pageable);
}
