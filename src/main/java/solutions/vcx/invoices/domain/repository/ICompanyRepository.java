package solutions.vcx.invoices.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solutions.vcx.invoices.domain.entities.Company;

import java.util.Optional;

public interface ICompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyName(final String companyName);
}
