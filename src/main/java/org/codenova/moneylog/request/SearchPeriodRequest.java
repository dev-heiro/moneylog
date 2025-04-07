package org.codenova.moneylog.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class SearchPeriodRequest {
    private LocalDate startDate;
    private LocalDate endDate;
}
