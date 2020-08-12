package solutions.vcx.invoices.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
}
