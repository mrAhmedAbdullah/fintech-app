package in.fintech.backend.service;

import in.fintech.backend.model.FinancialRecord;
import in.fintech.backend.repository.FinancialRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;

@Service // Spring ko batata hai ki ye Business Logic class hai
public class FinanceService {

    @Autowired // Repository ko automatically connect (Inject) karta hai
    private FinancialRecordRepository repository;

    // 1. Naya Record Save karne ke liye
    public FinancialRecord addRecord(FinancialRecord record) {
        return repository.save(record);
    }

    // 2. Saare Records fetch karne ke liye
    public List<FinancialRecord> getAllRecords() {
        return repository.findAll();
    }

    // 3. Dashboard Summary Logic (Total Income, Expense, Balance)
    public Map<String, BigDecimal> getSummary() {
        List<FinancialRecord> allRecords = repository.findAll();

        // Java Streams ka magic: Type ke basis par filter karke sum nikalna
        BigDecimal totalIncome = allRecords.stream()
                .filter(r -> "INCOME".equalsIgnoreCase(r.getTransactionType())) // Sirf INCOME wale pakdo
                .map(FinancialRecord::getAmount)                   // Unka Amount nikaalo
                .reduce(BigDecimal.ZERO, BigDecimal::add);         // Sabko plus kar do

        BigDecimal totalExpense = allRecords.stream()
                .filter(r -> "EXPENSE".equalsIgnoreCase(r.getTransactionType())) // Sirf EXPENSE wale pakdo
                .map(FinancialRecord::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Net Balance = Income - Expense
        BigDecimal netBalance = totalIncome.subtract(totalExpense);

        // Result ko Map (Key-Value) mein pack karke bhej rahe hain
        Map<String, BigDecimal> summary = new HashMap<>();
        summary.put("totalIncome", totalIncome);
        summary.put("totalExpense", totalExpense);
        summary.put("netBalance", netBalance);

        return summary;
    }
}