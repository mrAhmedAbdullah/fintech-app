package in.fintech.backend.controller;

import in.fintech.backend.model.FinancialRecord;
import in.fintech.backend.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController // Batata hai ki ye REST API hai jo JSON return karegi
@RequestMapping("/api/finance") // Base URL: http://localhost:8080/api/finance
public class FinanceController {

    @Autowired
    private FinanceService service;

    // POST: Naya record create karne ke liye
    @PostMapping("/add")
    public FinancialRecord createRecord(@Valid @RequestBody FinancialRecord record) {
        return service.addRecord(record);
    }
    

    // GET: Saare records dekhne ke liye
    @GetMapping("/all")
    public List<FinancialRecord> getAll() {
        return service.getAllRecords();
    }

    // GET: Dashboard summary (Income/Expense/Balance)
    @GetMapping("/summary")
    public Map<String, BigDecimal> getSummary() {
        return service.getSummary();
    }
}