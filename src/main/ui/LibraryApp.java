package ui;

import model.Book;
import model.Library;

import java.util.List;
import java.util.Scanner;

// Library application
public class LibraryApp {
    private Book book;
    private Library library;
    private Scanner input;
    private String command;
    private int rating = 0;
    private boolean validRating = false;

    // EFFECTS: runs the library application
    public LibraryApp() {
        runLibrary();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runLibrary() {
        boolean keepGoing = true;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();

            if (command.equalsIgnoreCase("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays menu of options to user
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t a -> add new book");
        System.out.println("\t v -> view books in library");
        System.out.println("\t c -> change book status");
        System.out.println("\t s -> search library for genre or author");
        System.out.println("\t e -> remove or edit book");
        System.out.println("\t q -> quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes library
    private void init() {
        library = new Library();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            System.out.println("\n-------- Adding New Book --------");
            doNewBook();
        } else if (command.equals("v")) {
            System.out.println("\n -------- Viewing All Books --------");
            doViewBooks();
        } else if (command.equals("c")) {
            System.out.println("\n -------- Updating Book Status --------");
            doUpdateStatus();
        } else if (command.equals("s")) {
            System.out.println("\n -------- Searching for Book --------");
            doSearch();
        } else if (command.equals("e")) {
            System.out.println("\n -------- Editing a Book --------");
            doUpdateBook();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter book information and adds to library
    private void doNewBook() {
        System.out.println("\nEnter book title:");
        String title = input.next();
        System.out.println("\nEnter book author:");
        String author = input.next();
        System.out.println("\nEnter book genre: ");
        String genre = input.next();

        if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
            System.out.println("All fields are required. Book not added.");
            return;
        }

        book = new Book(title, author, genre);
        library.addBook(book);
        System.out.println("\n" + book.toString() + "\nhas been added to your library!");
    }

    // EFFECTS: lists out all books in library
    private void doViewBooks() {
        List<Book> bookList = library.getBooks();
        if (bookList.isEmpty()) {
            System.out.println("Your library is empty.");
        } else {
            for (Book book : bookList) {
                System.out.println("\n" + book.toString());
            }
        }
    }

    // EFFECTS: prompts user for title of book to update,
    // then updates book status to "started" or "completed"
    // and calls appropriate method to handle updates
    private void doUpdateStatus() {
        String title = checkTitle();

        while (title.equals("Book not found in library!")) {
            System.out.println(title);
            title = checkTitle();
        }

        System.out.println("\n Select from: ");
        System.out.println("\t c --> Mark book as completed and add rating and review");
        System.out.println("\t s --> Mark book as started");

        command = input.next();

        if (command.equals("c")) {
            handleBookCompleted(title);
        } else if (command.equals("s")) {
            handleBookStarted(title);
        } else {
            System.out.println("Selection not valid... returning to main menu");
        }
    }

    // EFFECTS: prompts user to categorize their search based on genre or author,
    // then delegates choice to appropriate search method
    private void doSearch() {
        System.out.println("\nHow would you like to categorize your search: ");
        System.out.println("\t g --> By genre");
        System.out.println("\t a --> By author \n");

        command = input.next();

        if (command.equalsIgnoreCase("g")) {
            handleSearchByGenre();
        } else if (command.equalsIgnoreCase("a")) {
            handleSearchByAuthor();
        } else {
            System.out.println("Selection not valid... returning back to main menu");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to edit or delete book,
    // if delete calls handleBookRemoval, if edit calls handleBookEdit
    private void doUpdateBook() {
        String title = checkTitle();

        while (title.equals("Book not found in library!")) {
            System.out.println(title);
            title = checkTitle();
        }

        System.out.println("Would you like to delete (d) book or edit (e) this book?");
        command = input.next();

        if (command.equalsIgnoreCase("d")) {
            handleBookRemoval(title);
        } else if (command.equalsIgnoreCase("e")) {
            handleBookEdit(title);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter information to be edited in book
    // and changes book upon given information
    private void doEditBook(String bookTitle, Book newBook) {
        validRating = false;
        System.out.println("Enter new title or leave blank to be unchanged: ");
        String newTitle = input.next();
        System.out.println("Enter new author or leave blank to be unchanged: ");
        String newAuthor = input.next();
        System.out.println("Enter new genre or leave blank to be unchanged: ");
        String newGenre = input.next();
        int newRating = validRating(1, book);
        System.out.println("Enter new review or leave blank to be unchanged: ");
        String newReview = input.next();

        if (newTitle.isEmpty()) {
            newTitle = book.getTitle();
        }
        if (newAuthor.isEmpty()) {
            newAuthor = book.getAuthor();
        }
        if (newGenre.isEmpty()) {
            newGenre = book.getGenre();
        }
        if (newReview.isEmpty()) {
            newReview = book.getReview();
        }

        newBook.editBook(newTitle, newAuthor, newGenre, newRating, newReview);
    }

    // MODIFIES: this
    // EFFECTS: if book is completed asks user for rating and review
    // and updates book in library
    private void handleBookCompleted(String title) {
        for (int i = 0; i < library.getSize(); i++) {
            book = library.getBooks().get(i);
            String bookTitle = book.getTitle();

            if (bookTitle.equalsIgnoreCase(title)) {
                book.setReadingStatus("completed");

                rating = validRating(0, book);
                book.setRating(rating);

                System.out.println("\nEnter review for book: ");
                String review = input.next();
                book.setReview(review);
            } else {
                System.out.println("\nBook not found in library.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: handles setting book status from user to "started"
    private void handleBookStarted(String title) {
        for (int i = 0; i < library.getSize(); i++) {
            book = library.getBooks().get(i);
            String bookTitle = book.getTitle();

            if (bookTitle.equalsIgnoreCase(title)
                    && !book.getReadingStatus().equals("completed")) {
                book.setReadingStatus("started");
            } else {
                System.out.println("Book has already been completed!");
            }
        }
    }

    // EFFECTS: prompts user to search books by genre and prints out those matching
    public void handleSearchByGenre() {
        List<Book> searchList;

        System.out.println("Enter genre to search for: ");
        String genre = input.next();

        if (genre.isEmpty()) {
            System.out.println("Genre is required.");
        } else {
            searchList = library.findBookByGenre(genre);

            if (searchList.isEmpty()) {
                System.out.println("\nThere are no books with this genre!");
            }

            for (Book book : searchList) {
                System.out.println("\n" + book.toString());
            }
        }
    }

    // EFFECTS: prompts user to search books by auhtor and prints out those matching
    public void handleSearchByAuthor() {
        List<Book> searchList;

        System.out.println("Enter author to search for: ");
        String author = input.next();

        if (author.isEmpty()) {
            System.out.println("Author is required.");
        } else {
            searchList = library.findBookByAuthor(author);

            if (searchList.isEmpty()) {
                System.out.println("\nThere are no books with this genre!");
            }

            for (Book book : searchList) {
                System.out.println("\n" + book.toString());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes first book from library that the title matches
    private void handleBookRemoval(String title) {
        for (int i = 0; i < library.getSize(); i++) {
            book = library.getBooks().get(i);
            String bookTitle = book.getTitle();

            if (bookTitle.equalsIgnoreCase(title)) {
                library.removeBook(book);
            }
        }
    }

    // EFFECTS: prompts user to edit book through doEditBook method
    private void handleBookEdit(String title) {
        for (int i = 0; i < library.getSize(); i++) {
            book = library.getBooks().get(i);
            String bookTitle = book.getTitle();

            if (bookTitle.equalsIgnoreCase(title)) {
                doEditBook(title, book);
            }
        }
        System.out.println(title + " has been updated!");
    }

    // EFFECTS: checks if the title of book user wants is in library and returns
    // title of book
    private String checkTitle() {
        boolean bookExists = false;
        List<Book> bookList = library.getBooks();
        System.out.println("\nEnter title of book: ");
        String title = input.next();

        if (title.isEmpty()) {
            System.out.println("\nTitle is required!");
        }

        for (Book book : bookList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                bookExists = true;
            }
        }

        if (bookExists) {
            return title;
        }
        return "Book not found in library!";

    }

    // EFFECTS: checks if users rating is valid (Integer and in range)
    private int validRating(int choice, Book book) {
        while (!validRating) {
            if (choice == 0) {
                System.out.println("\nEnter rating for book: ");
            } else {
                System.out.println("Enter rating for book or leave blank to be unchanged: ");
            }
            String userRating = input.next();
            try {
                if (userRating.isEmpty()) {
                    rating = book.getRating();
                } else {
                    rating = Integer.parseInt(userRating);
                }
                if (rating < 1 || rating > 5) {
                    System.out.println("\nNot a valid rating (rate from 1 - 5)");
                } else {
                    validRating = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a vlaid rating (rate from 1 -5)");
            }
        }
        return rating;
    }
}
