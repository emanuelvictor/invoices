package solutions.vcx.invoices.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"number", "providing_id", "requesting_id"})
})
public class Invoice {

    /**
     *
     */
    @Id
    @NotNull
    @Column(nullable = false, unique = true, updatable = false)
    private Long number;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private BigDecimal value;

    /**
     *
     */
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "providing_id")
    private Company providing;

    /**
     *
     */
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "requesting_id")
    private Company requesting;

    /**
     *
     */
    public Invoice() {
    }

    /**
     * @param number {Long}
     */
    public Invoice(final Long number) {
        this.number = number;
    }

    /**
     * @param number     {Long}
     * @param date       {DateTime}
     * @param value      {Long}
     * @param providing  {Company}
     * @param requesting {Company}
     */
    public Invoice(final Long number,
                   final @NotNull LocalDate date,
                   final @NotNull BigDecimal value,
                   final @NotNull Company providing,
                   final @NotNull Company requesting) {
        this.number = number;
        this.date = date;
        this.value = value;
        this.providing = providing;
        this.requesting = requesting;
    }
}
