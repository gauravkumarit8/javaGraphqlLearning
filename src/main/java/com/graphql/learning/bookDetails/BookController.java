package com.graphql.learning.bookDetails;

import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
class BookController {

    //DatafetchingEnvironment is use to getting the other part of the request body query.
    @QueryMapping
    public Book bookById(@Argument String id, DataFetchingEnvironment environment) {
        String operationName= environment.getOperationDefinition().getName();
        //only allow if the operation name is bookDetails
        if(!"bookDetails".equals(operationName)){
            throw new RuntimeException("Permission denied..");
        }
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }

}