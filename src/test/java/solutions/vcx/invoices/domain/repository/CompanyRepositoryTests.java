package solutions.vcx.invoices.domain.repository;//package solutions.vcx.invoices.domain.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import solutions.vcx.invoices.domain.AbstractIntegrationTests;
import solutions.vcx.invoices.domain.entities.Company;

import java.util.List;

public class CompanyRepositoryTests extends AbstractIntegrationTests {

    /**
     *
     */
    @Autowired
    private ICompanyRepository companyRepository;

    /**
     *
     */
    @Test(expected = org.springframework.orm.jpa.JpaSystemException.class)
    public void saveCompanyMustFail() {
        this.companyRepository.save(new Company());
    }

    /**
     *
     */
    @Test
    public void saveCompanyMustPass() {
        final String companyName = "Company Name";
        final String fantasyName = "Fantasy Name";
        final String document = "document";
        final Company company = new Company(document, fantasyName, companyName);
        this.companyRepository.save(company);

        Assert.assertNotNull(company);
        Assert.assertEquals(company.getCompanyName(), companyName);
        Assert.assertEquals(company.getFantasyName(), fantasyName);
        Assert.assertEquals(company.getDocument(), document);
    }

    /**
     *
     */
    @Test
    @Sql({"/dataset/companies.sql"})
    public void updateCompanyMustPass() {

        final Company originalCompany = this.companyRepository.findById("first_document").orElseThrow();

        final String companyName = "New Company Name";
        final String fantasyName = "New Fantasy Name";
        final String document = "first_document";
        final Company newCompany = new Company(document, fantasyName, companyName);
        this.companyRepository.save(newCompany);


        Assert.assertNotNull(newCompany);
        Assert.assertNotEquals(newCompany.getCompanyName(), originalCompany.getCompanyName());
        Assert.assertNotEquals(newCompany.getFantasyName(), originalCompany.getFantasyName());
        Assert.assertEquals(newCompany.getDocument(), originalCompany.getDocument());
    }

    /**
     *
     */
    @Sql({"/dataset/companies.sql"})
    @Test(expected = DataIntegrityViolationException.class)
    public void updateCompanyWithRepeatedCompanyNameMustFail() {
        final String companyName = "Second Company Name";
        final String fantasyName = "Second Fantasy Name";
        final String document = "first_document";
        final Company company = new Company(document, fantasyName, companyName);
        this.companyRepository.save(company);
    }

    /**
     *
     */
    @Sql({"/dataset/companies.sql"})
    @Test(expected = DataIntegrityViolationException.class)
    public void saveCompanyWithRepeatedCompanyNameMustFail() {
        final String companyName = "First Company Name";
        final String fantasyName = "First Fantasy Name";
        final String document = "third_document";
        final Company company = new Company(document, fantasyName, companyName);
        this.companyRepository.save(company);
    }

    /**
     *
     */
    @Test
    @Sql({"/dataset/companies.sql"})
    public void findCompanyByIdMustPass() {
        final Company company = this.companyRepository.findById("first_document").orElseThrow();

        Assert.assertNotNull(company);
        Assert.assertNotNull(company.getDocument());
        Assert.assertNotNull(company.getFantasyName());
        Assert.assertNotNull(company.getCompanyName());
        Assert.assertEquals("first_document", company.getDocument());
        Assert.assertEquals("First Company Name", company.getCompanyName());
        Assert.assertEquals("First Fantasy Name", company.getFantasyName());
    }

    /**
     *
     */
    @Test
    @Sql({"/dataset/companies.sql"})
    public void listCompaniesByFantasyNameContainingMustReturn2() {
        final Page<Company> pageOfcompanies = this.companyRepository.findAllByFantasyNameContaining("Fantasy", null);

        Assert.assertNotNull(pageOfcompanies);
        Assert.assertEquals(2, pageOfcompanies.getTotalElements());
    }

    /**
     *
     */
    @Test
    @Sql({"/dataset/companies.sql"})
    public void listCompaniesByFantasyNameContainingMustReturn1() {
        final Page<Company> companies = this.companyRepository.findAllByFantasyNameContaining("First", null);

        Assert.assertNotNull(companies);
        Assert.assertEquals(1, companies.getTotalElements());
    }

    @Test
    @Sql({"/dataset/companies.sql"})
    public void listCompaniesByFantasyNameContainingMustReturn0() {
        final Page<Company> companies = this.companyRepository.findAllByFantasyNameContaining("must return zero", null);

        Assert.assertNotNull(companies);
        Assert.assertEquals(0, companies.getTotalElements());
    }

    /**
     *
     */
    @Test
    @Sql({"/dataset/companies.sql"})
    public void listCompaniesMustReturnAll() {
        final List<Company> companies = this.companyRepository.findAll();

        Assert.assertNotNull(companies);
        Assert.assertEquals(3, companies.size());
    }

}
