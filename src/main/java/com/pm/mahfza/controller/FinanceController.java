package com.pm.mahfza.controller;

import com.pm.mahfza.entite.Account;
import com.pm.mahfza.entite.Transaction;
import com.pm.mahfza.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class FinanceController {

    private final FinanceService service;

    @GetMapping("/")
    public String home(Model model) {
        List<Account> accounts = service.getAllAccounts();
        Map<String, List<Transaction>> lastTxs = new HashMap<>();

        for (Account acc : accounts) {
            List<Transaction> txs = service.getTransactions(acc.getName()).stream()
                    .limit(5)
                    .toList();
            lastTxs.put(acc.getName(), txs);
        }

        // حساب الرصيد الإجمالي
        BigDecimal totalBalance = service.getTotalBalance();

        model.addAttribute("accounts", accounts);
        model.addAttribute("lastTxs", lastTxs);
        model.addAttribute("totalBalance", totalBalance);  // ⬅ هنا
        return "index";
    }


    @PostMapping("/add")
    public String addFunds(@RequestParam String walletName,
                           @RequestParam BigDecimal amount,
                           @RequestParam String description) {
        service.addFunds(walletName, amount, description);
        return "redirect:/";
    }

    @PostMapping("/spend")
    public String spendFunds(@RequestParam String walletName,
                             @RequestParam BigDecimal amount,
                             @RequestParam String description) {
        service.spendFunds(walletName, amount, description);
        return "redirect:/";
    }

    @GetMapping("/transactions/{walletName}")
    public String transactions(@PathVariable String walletName, Model model) {
        List<Transaction> txs = service.getTransactions(walletName);
        model.addAttribute("transactions", txs);
        model.addAttribute("walletName", walletName);
        return "transactions";
    }
}
