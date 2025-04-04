package org.codenova.moneylog.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class AddExpenseRequest {
    @Positive
    private int amount;
    @NotBlank
    private String description;

    private int categoryId;

    @PastOrPresent
    private LocalDate expenseDate;
}
