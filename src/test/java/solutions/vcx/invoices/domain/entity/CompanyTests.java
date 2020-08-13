package solutions.vcx.invoices.domain.entity;

import org.junit.Before;
import org.junit.Test;
import solutions.vcx.invoices.application.exceptions.DocumentIsCpfException;
import solutions.vcx.invoices.application.exceptions.InvalidCnpjException;
import solutions.vcx.invoices.application.exceptions.InvalidDocumentException;
import solutions.vcx.invoices.domain.AbstractUnitTests;
import solutions.vcx.invoices.domain.entities.Company;

public class CompanyTests extends AbstractUnitTests {

    /**
     *
     */
    @Before
    @Override
    public void setUp() {
        super.setUp();
    }

    /**
     *
     */
    @Test(expected = InvalidDocumentException.class)
    public void validateDocumentWithNullDocumentMustFail() {
        new Company().validateDocumento();
    }

    /**
     *
     */
    @Test(expected = InvalidDocumentException.class)
    public void validateDocumentWithInvalidDocumentMustFail() {
        new Company("invalid_document", "", "").validateDocumento();
    }

    /**
     *
     */
    @Test(expected = DocumentIsCpfException.class)
    public void validateDocumentWithACPFDocumentMustFail() {
        new Company("07074762911", "", "").validateDocumento();
    }

    /**
     *
     */
    @Test(expected = InvalidDocumentException.class)
    public void validateDocumentWithACPFDocumentWithEspecialCharactersMustFail() {
        new Company("070.747.629-11", "", "").validateDocumento();
    }

    /**
     *
     */
    @Test
    public void validateDocumentWithEspecialCharactersMustPass() {
        new Company("33.198.847/0001-81", "", "").validateDocumento();
    }

    /**
     *
     */
    @Test
    public void validateDocumentMustPass() {
        new Company("33198847000181", "", "").validateDocumento();
    }

    /**
     *
     */
    @Test(expected = InvalidCnpjException.class)
    public void validateDocumentWithInvalidCpnjMustPass() {
        new Company("33198847000281", "", "").validateDocumento();
    }
}
