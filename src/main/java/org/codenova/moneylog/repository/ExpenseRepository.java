package org.codenova.moneylog.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.codenova.moneylog.entity.Expense;
import org.codenova.moneylog.query.CategoryExpense;
import org.codenova.moneylog.query.ExpenseWithCategory;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ExpenseRepository {
    public int save(Expense expense);

    public List<Expense> findByUserId(@Param("userId") int userId);

    public List<Expense> findByUserIdAndDuration(@Param("userId") int userId,
                                                 @Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);

    public List<ExpenseWithCategory> findWithCategoryByUserId(@Param("userId") int userId);



    public int getTotalAmountByUserIdAndPeriod(@Param("userId") int userId,
                                            @Param("startDate") LocalDate startDate,
                                            @Param("endDate") LocalDate endDate);

    public List<Expense> getTop3ExpenseByUserId(@Param("userId") int userId,
                                  @Param("startDate") LocalDate startDate,
                                  @Param("endDate") LocalDate endDate);

    public List<CategoryExpense> getCategoryExpenseByUserId(@Param("userId") int userId,
                                         @Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);
}