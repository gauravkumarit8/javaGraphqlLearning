package com.graphql.learning.bookDetails;

import com.graphql.learning.exception.PermissionDeniedException;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
class BookController {

    //DatafetchingEnvironment is use to getting the other part of the request body query.
    @QueryMapping
    public Book bookById(@Argument String id, DataFetchingEnvironment environment) {
        String operationName = environment.getOperationDefinition().getName();

        if (!"bookDetails".equals(operationName)) {
            throw new PermissionDeniedException("Permission denied. Only 'bookDetails' operation is allowed.");
        }

        return Book.getById(id);
    }

    @QueryMapping(name = "getByAuthorName")  // Match the schema field name
    public List<Book> getByAuthorName(@Argument String authorName, DataFetchingEnvironment environment) {
        String operatorName = environment.getOperationDefinition().getName();
        if (!"BookByAuthor".equals(operatorName)) {
            throw new PermissionDeniedException("Permission denied. Only 'BookByAuthor' operation is allowed.");
        }
        return Book.getByAuthorName(authorName);
    }

    @QueryMapping
    public Book bookByUserName(@Argument String bookName, DataFetchingEnvironment environment) {
        String operatorName = environment.getOperationDefinition().getName();
        if (!"bookByBookName".equals(operatorName)) {
            throw new PermissionDeniedException("Permission denied. Only 'bookByName' operator is allowed");
        }
        return Book.bookByName(bookName);
    }

    //same function name should be provided in the graphql schema query type with the datatype
    @QueryMapping
    public Integer totalBook(DataFetchingEnvironment environment){
        String operatorName= environment.getOperationDefinition().getName();
        if(!"totalBookCount".equals(operatorName)){   //this is the operator name expected.
            throw new PermissionDeniedException("Permission denied. Only 'totalBookCount' operator is allowed");
        }
        return Book.totalNoOfBooks();//this function name can be anything
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }

}