package com.graphql.learning.bookDetails;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

record Book(String id, String name, int pageCount, String authorId) {

    private static List<Book> books = Arrays.asList(
            new Book("book-1", "Harry Potter and the Philosopher's Stone", 223, "author-1"),
            new Book("book-2", "Moby Dick", 635, "author-2"),
            new Book("book-3", "Interview with the vampire", 371, "author-3")
    );

    public static Book getById(String id) {
        return books.stream().filter(book -> book.id().equals(id)).findFirst().orElse(null);
    }

    public static List<Book> getByAuthorName(String authorName){
        return books.stream().filter(book->{
            Author author=Author.getById(book.authorId);
            String fullname=author.firstName()+" "+author.lastName();
            return fullname.toLowerCase().contains(authorName.toLowerCase());
        }).collect(Collectors.toList());
    }

    public static Book bookByName(String bookName){
        return books.stream().filter(rana->rana.name.equals(bookName)).findFirst().orElse(null);
    }

    public static Integer totalNoOfBooks(){
        return books.size();
    }

}