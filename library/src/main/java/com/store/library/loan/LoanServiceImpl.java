package com.store.library.loan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("loanService")
@Transactional
public class LoanServiceImpl implements ILoanService {
    @Autowired
    private ILoanDao loanDao;
    
    @Override
    public Loan saveLoan( Loan loan ) {
        
        return loanDao.save( loan );
    }

    @Override
    public List<Loan> getAllLoan() {        
        return loanDao.findAll();
    }

}
