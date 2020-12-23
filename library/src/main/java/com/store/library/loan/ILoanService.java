package com.store.library.loan;

import java.time.LocalDate;
import java.util.List;

public interface ILoanService {
    
    public Loan saveLoan(Loan loan);
    public List<Loan> getAllLoan();
    List<Loan> findAllLoansByEndDateBefore( LocalDate maxEndDate);
    Loan getOpenedLoan( SimpleLoanDTO simpleLoanDTO );
}
