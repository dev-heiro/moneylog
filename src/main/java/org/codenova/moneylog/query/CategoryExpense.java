package org.codenova.moneylog.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryExpense {
    private int categoryId;
    private long total;
    private String categoryName;
}
