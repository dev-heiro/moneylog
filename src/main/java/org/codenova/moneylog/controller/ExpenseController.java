package org.codenova.moneylog.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.codenova.moneylog.entity.Expense;
import org.codenova.moneylog.entity.User;
import org.codenova.moneylog.query.DailyExpense;
import org.codenova.moneylog.repository.CategoryRepository;
import org.codenova.moneylog.repository.ExpenseRepository;
import org.codenova.moneylog.request.AddExpenseRequest;
import org.codenova.moneylog.request.SearchPeriodRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/expense")
@AllArgsConstructor
public class ExpenseController {
    private CategoryRepository categoryRepository;
    private ExpenseRepository expenseRepository;

    @GetMapping("/history")
    public String historyHandle(@SessionAttribute("user") User user,
                                @ModelAttribute SearchPeriodRequest searchPeriodRequest,
                                Model model) {
        LocalDate startDate;
        LocalDate endDate;
        if(searchPeriodRequest.getStartDate() != null && searchPeriodRequest.getEndDate() != null) {
            startDate = searchPeriodRequest.getStartDate();
            endDate = searchPeriodRequest.getEndDate();
        } else {
            LocalDate today = LocalDate.now();
            startDate = today.minusDays(today.getDayOfMonth()-1);
            endDate= startDate.plusMonths(1).minusDays(1);
        }

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        model.addAttribute("categorys", categoryRepository.findAll());
        model.addAttribute("now", LocalDate.now());
//         model.addAttribute("expenses", expenseRepository.findWithCategoryByUserId(user.getId()));

        model.addAttribute("expenses",
                expenseRepository.findByUserIdAndDuration(user.getId(), startDate, endDate)
        );
        // findByWithCategoryByUserIdAndDuration  이것도 만들어 둘것..!
        return "expense/history";
    }

    @PostMapping("/history")
    public String historyPostHandle(@ModelAttribute @Valid AddExpenseRequest addExpenseRequest,
                                    BindingResult bindingResult,
                                    @SessionAttribute("user") User user,
                                    Model model) {

        if (bindingResult.hasErrors()) {
            return "expense/history-error";
        }

        Expense expense = Expense.builder()
                .userId(user.getId())
                .expenseDate(addExpenseRequest.getExpenseDate())
                .amount(addExpenseRequest.getAmount())
                .description(addExpenseRequest.getDescription())
                .categoryId(addExpenseRequest.getCategoryId())
                .build();
        expenseRepository.save(expense);

        return "redirect:/expense/history";
    }

    @GetMapping("/report")
    public String reportHandle(@SessionAttribute("user") User user, Model model) {

        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(today.getDayOfMonth()-1);
        LocalDate endDate= startDate.plusMonths(1).minusDays(1);

        model.addAttribute("categoryExpense",
            expenseRepository.getCategoryExpenseByUserIdOrderByCategoryId(user.getId(), startDate, endDate)
        );

        List<DailyExpense> list =
                expenseRepository.getDailyExpenseByUserIdAndPeriod(user.getId(), startDate, endDate);
        Map<LocalDate, DailyExpense> dateMap = new HashMap<>();
        for(DailyExpense expense : list) {
            dateMap.put(expense.getExpenseDate(), expense);
        }

        List<DailyExpense> fullList = new ArrayList<>();
        for(int i = 0; startDate.plusDays(i).isBefore(endDate) || startDate.plusDays(i).isEqual(endDate); i++ ) {
            LocalDate d = startDate.plusDays(i);
            if(dateMap.get(d) != null) {
                fullList.add(dateMap.get(d));
            }else {
                fullList.add(DailyExpense.builder().expenseDate(d).total(0).build());
            }
        }

        model.addAttribute("dailyExpense", fullList);


        return "expense/report";
    }



}
