package solutions.vcx.invoices.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solutions.vcx.invoices.domain.repository.ICompanyRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    /**
     *
     */
    private final ICompanyRepository producerRepository;


}
