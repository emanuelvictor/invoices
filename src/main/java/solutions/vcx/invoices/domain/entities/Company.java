package solutions.vcx.invoices.domain.entities;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import solutions.vcx.invoices.application.exceptions.DocumentIsCpfException;
import solutions.vcx.invoices.application.exceptions.InvalidCnpjException;
import solutions.vcx.invoices.application.exceptions.InvalidDocumentException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

@Data
@Entity
@EqualsAndHashCode
public class Company {

    /**
     *
     */
    @Id
    @NotNull
    @Column(nullable = false, unique = true, updatable = false)
    private String document;

    /**
     * Nome Fantasia
     */
    private String fantasyName;

    /**
     * Razão Social
     */
    @NotNull
    @Column(nullable = false, unique = true)
    private String companyName;

    /**
     *
     */
    public Company() {
    }

    /**
     * @param document String
     */
    public Company(@NotNull final String document) {
        this.document = document;
    }

    /**
     * @param document    String
     * @param fantasyName String
     * @param companyName String
     */
    public Company(@NotNull final String document, final String fantasyName, final @NotNull String companyName) {
        this.document = document;
        this.fantasyName = fantasyName;
        this.companyName = companyName;
    }

    /**
     * Valida o documento
     */
    public void validateDocumento() {
        this.document = validateDocumento(this.document);
    }

    /**
     * Remove '.', '/' e '-'
     *
     * @param documento {String}
     * @return {String}
     */
    private static String prepareDocument(final String documento) {
        if (documento == null || documento.length() < 1) {
            throw new InvalidDocumentException();
        }

        return documento.replaceAll(Pattern.quote("."), "").replaceAll(Pattern.quote("/"), "");
    }

    /**
     * @param document {String}
     * @return {String}
     */
    public static String validateDocumento(final String document) {

        // Prepare the document
        // Remove characters '.', '/' and '-'
        final String doc = Company.prepareDocument(document);

        // Caelumn Stella Validators
        final CNPJValidator cnpjValidator = new CNPJValidator();
        final CPFValidator cpfValidator = new CPFValidator();

        if (cpfValidator.isEligible(doc)) {
            // Document must not be cpf
            throw new DocumentIsCpfException();
        } else if (cnpjValidator.isEligible(doc)) {
            try {
                cnpjValidator.assertValid(doc);
            } catch (Exception e) {
                // The CNPJ is invalid
                throw new InvalidCnpjException();
            }
        } else {
            // The document is malformed, is not eligible to cpf or cnpj
            throw new InvalidDocumentException();
        }

        return doc;
    }
}
