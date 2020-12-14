package com.store.library.loan;

import java.time.LocalDate;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="loans")
@AssociationOverrides({
@AssociationOverride(name = "pk.book", joinColumns = @JoinColumn(name = "bookid")),
@AssociationOverride(name = "pk.customer", joinColumns = @JoinColumn(name = "customerid"))
})
public class Loan {
    @EmbeddedId
    private LoanId pk = new LoanId();
    
    private LocalDate begindate;
    
    @Enumerated(EnumType.STRING)    
    private LoanStatus status;

    private LocalDate enddate;   
    
    public Loan() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Loan( LoanId pk, LocalDate begindate, LocalDate enddate, LoanStatus status ) {
        super();
        this.pk = pk;
        this.begindate = begindate;
        this.enddate = enddate;
        this.status = status;
    }

    public LoanId getPk() {
        return pk;
    }

    public void setPk( LoanId pk ) {
        this.pk = pk;
    }

    public LocalDate getBegindate() {
        return begindate;
    }

    public void setBegindate( LocalDate begindate ) {
        this.begindate = begindate;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate( LocalDate enddate ) {
        this.enddate = enddate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus( LoanStatus status ) {
        this.status = status;
    }
    
    
    
    
}
