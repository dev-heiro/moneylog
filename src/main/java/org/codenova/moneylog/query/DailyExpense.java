package org.codenova.moneylog.query;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DailyExpense {
    private LocalDate expenseDate;
    private long total;
}
