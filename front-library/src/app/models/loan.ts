import { Book } from "./book";
import { Customer } from "./customer";

export class Loan {
    bookDTO: Book = new Book();
    customerDTO: Customer = new Customer();

    loanBeginDate: Date;
    loanEndDate:Date;
    
    status: string;
}