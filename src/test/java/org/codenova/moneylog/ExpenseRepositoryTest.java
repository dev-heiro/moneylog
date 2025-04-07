package org.codenova.moneylog;

import org.codenova.moneylog.query.DailyExpense;
import org.codenova.moneylog.repository.ExpenseRepository;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ExpenseRepositoryTest {

    @Autowired
    ExpenseRepository expenseRepository;

    @Test
    public void dailyExpenseTest() {
        List<DailyExpense> list = expenseRepository.getDailyExpenseByUserIdAndPeriod(10,
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 30)
        );
        for(DailyExpense expense : list) {
            System.out.println(expense);
        }
        System.out.println("=======================================================");
        list.add(0,
                DailyExpense.builder().expenseDate(LocalDate.of(2025, 4, 5)).total(0).build());

        for(DailyExpense expense : list) {
            System.out.println(expense);
        }
    }

    @Test
    public void test2() {
        LocalDate from = LocalDate.of(2025, 4, 1);
        LocalDate to = LocalDate.of(2025, 4, 30);
        // 반복문을 이용해서 이 사이의 모든 날짜 (LocalDate)를 출력
        List<LocalDate> dates = new ArrayList<>();
        for(int i = 0; from.plusDays(i).isBefore(to) || from.plusDays(i).isEqual(to); i++ ) {
            dates.add(from.plusDays(i));
        }
        List<DailyExpense> expenses = new ArrayList<>();
        for(LocalDate date : dates) {
            expenses.add(
                    DailyExpense.builder().expenseDate(date).total(0).build()
            );
        }
        // ===================================================================================
        for(DailyExpense one : expenses) {
            System.out.println(one);
        }
    }


    @Test
    public void test3() {
        LocalDate from = LocalDate.of(2025, 4, 1);
        LocalDate to = LocalDate.of(2025, 4, 14);
        List<DailyExpense> list = expenseRepository.getDailyExpenseByUserIdAndPeriod(10, from, to);
        Map<LocalDate, DailyExpense> dateMap = new HashMap<>();

        for(DailyExpense expense : list) {
        //    System.out.println(expense);
            dateMap.put(expense.getExpenseDate(), expense);
        }
        // System.out.println(listMap);
        List<DailyExpense> fullList = new ArrayList<>();
        for(int i = 0; from.plusDays(i).isBefore(to) || from.plusDays(i).isEqual(to); i++ ) {
            LocalDate d = from.plusDays(i);
            // if 만약 이 날짜에 해당하는 데이터를 불러왓따면, 그때는 그걸 add
            if(dateMap.get(d) != null) {
                fullList.add(dateMap.get(d));
            }else {
                // else 없다면 이날짜로 데이터를 생성해서 add
                fullList.add(DailyExpense.builder().expenseDate(d).total(0).build());
            }
        }

        System.out.println("======================================================");
        for(DailyExpense expense : fullList) {
            System.out.println(expense);
        }




    }
}
