package com.store.library.loan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoanDao extends JpaRepository<Loan,Integer> {

}
