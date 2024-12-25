**To test the graphapi from the postman use this request body**

# GraphQL Request Body

### *For `getBookById` Function*

**Request Body:**

```graphql
query bookDetails {
  bookById(id: "book-1") {
    id
    name
    pageCount
    author {
      id
      firstName
      lastName
    }
  }
}
```

### *For `getBookByAuthor` Function*

**Request Body:**

```graphql
query BookByAuthor {
  getByAuthorName(authorName: "Rice") {
    id
    name
    pageCount
    author {
      firstName
      lastName
    }
  }
}

```

### *For `bookByUserName` Function*

**Request Body:**

```graphql
query bookByBookName {
    bookByUserName(bookName: "Moby Dick") {
        id
        name
        pageCount
        author {
            id
            firstName
            lastName
        }
    }
}

```
