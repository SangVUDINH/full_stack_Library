import { Category } from "./category"

export class Book {
    idbook: number;

    title: string;
    
    isbn: string;
    
    totalExamplaries: number;
    
    author: string;

    releaseDate: Date;
    
    category = new Category();

}