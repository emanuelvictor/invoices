package solutions.vcx.invoices.application.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solutions.vcx.invoices.domain.service.CompanyService;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyResource {

    /**
     *
     */
    private final CompanyService companyService;

}
