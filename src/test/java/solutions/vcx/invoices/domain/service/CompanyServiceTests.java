package solutions.vcx.invoices.domain.service;//package solutions.vcx.invoices.domain.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import solutions.vcx.invoices.domain.AbstractIntegrationTests;
import solutions.vcx.invoices.domain.entities.Company;

public class CompanyServiceTests extends AbstractIntegrationTests {

    /**
     *
     */
    @Autowired
    private CompanyService companyService;

    /**
     *
     */
    @Test(expected = org.springframework.orm.jpa.JpaSystemException.class)
    public void saveCompanyMustFail() {
        this.companyService.save(new Company());
    }

    /**
     *
     */
    @Test(expected = org.springframework.transaction.TransactionSystemException.class)
    public void saveCompanyWithoutCompanyNameMustFail() {
        final String companyName = null;
        final String fantasyName = "Fantasy Name";
        final String document = "documentt";
        final Company company = new Company(document, fantasyName, companyName);
        this.companyService.save(company);
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
        this.companyService.save(company);

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

        final Company originalCompany = this.companyService.findById("first_document").orElseThrow();

        final String companyName = "New Company Name";
        final String fantasyName = "New Fantasy Name";
        final String document = "first_document";
        final Company newCompany = new Company(document, fantasyName, companyName);
        this.companyService.save(newCompany);


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
        this.companyService.save(company);
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
        this.companyService.save(company);
    }

    /**
     *
     */
    @Test
    @Sql({"/dataset/companies.sql"})
    public void findCompanyByIdMustPass() {
        final Company company = this.companyService.findById("first_document").orElseThrow();

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
    public void listCompaniesMustReturnAll() {
        final Page<Company> companies = this.companyService.findAll(PageRequest.of(0, 1000));

        Assert.assertNotNull(companies);
        Assert.assertEquals(3, companies.getTotalElements());
    }


    /**
     *
     */
    @Test(expected = EmptyResultDataAccessException.class)
    @Sql({"/dataset/companies.sql"})
    public void removeCompanyByIdMustFail() {
        this.companyService.deleteById("123456");
    }

    /**
     *
     */
    @Test
    @Sql({"/dataset/companies.sql"})
    public void removeCompanyByIdMustPass() {
        this.companyService.deleteById("document");

        final Page<Company> companies = this.companyService.findAll(PageRequest.of(0, 1000));
        Assert.assertNotNull(companies);
        Assert.assertEquals(2, companies.getTotalElements());
    }
}
