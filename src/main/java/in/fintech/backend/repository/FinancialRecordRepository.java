package in.fintech.backend.repository;

import in.fintech.backend.model.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    // JpaRepository extend karne se aapko save(), findAll(), deleteById() 
    // jaise functions likhne nahi padenge. Spring inhe khud handle karega.
}