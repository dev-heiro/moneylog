package org.codenova.moneylog.entity;

import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    private long id;
    private int userId;
    private LocalDate expenseDate;
    private String description;
    private long amount;
    private int categoryId;
}
