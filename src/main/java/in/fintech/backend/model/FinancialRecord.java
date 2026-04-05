package in.fintech.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;


@Entity // Ye Spring ko batata hai ki iska database mein table banana hai
@Table(name = "financial_records") // Table ka naam
@Data // Lombok: Ye auto-generate karega Getters, Setters, aur toString()
public class FinancialRecord {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be positive")
    private BigDecimal amount; // Paiso ke liye hamesha BigDecimal use karein (Precision ke liye)

    
    @Column(nullable = false)
    @NotBlank(message = "Transaction (INCOME/EXPENSE) type is required")
    private String transactionType; // "INCOME" ya "EXPENSE"

    @NotBlank(message = "Category is required")
    private String category; // e.g., Salary, Food, Rent

    private String description;

    private LocalDateTime date;

    // Record kisne create kiya (Audit ke liye)
    private String createdBy;

    // Ye method record save hone se pehle date set kar dega
    @PrePersist
    protected void onCreate() {
        this.date = LocalDateTime.now();
    }
}