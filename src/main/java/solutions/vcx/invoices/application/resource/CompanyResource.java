package solutions.vcx.invoices.application.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solutions.vcx.invoices.domain.entities.Company;
import solutions.vcx.invoices.domain.service.CompanyService;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyResource {

    /**
     *
     */
    private final CompanyService companyService;

    /**
     * @param filters {String}
     * @param pageable        Pageable
     * @return Page<Company>
     */
    @GetMapping
    public Page<Company> listByFilters(final String filters, final Pageable pageable) {
        return this.companyService.listByFilters(filters, pageable);
    }

}
