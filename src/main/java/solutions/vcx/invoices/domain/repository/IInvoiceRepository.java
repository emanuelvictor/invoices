package solutions.vcx.invoices.domain.repository;

import solutions.vcx.invoices.domain.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvoiceRepository extends JpaRepository<Invoice, Long> {
}
