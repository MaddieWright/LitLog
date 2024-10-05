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

    // EFFECTS: runs the library application
    public LibraryApp() {
        runLibrary();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runLibrary() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
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
            newBook();
        } else if (command.equals("v")) {
            viewBooks();
        } else if (command.equals("c")) {
            updateBookStatus();
        } else if (command.equals("s")) {
            doSearch();
        } else if (command.equals("e")) {
            doEditBook();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter book information and adds to library
    private void newBook() {
        System.out.println("\n -------- Adding New Book --------");
        System.out.println("\n Enter book title:");
        String title = input.toString();
        System.out.println("\n Enter book author:");
        String author = input.toString();
        System.out.println("\n Enter book genre: ");
        String genre = input.toString();

        if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
            System.out.println("All fields are required. Book not added.");
            return;
        }

        book = new Book(title, author, genre);
        library.addBook(book);
        System.out.println(book.toString() + "\nhas been added to your library!");
    }

    // EFFECTS: lists out all books in library
    private void viewBooks() {
        System.out.println("\n -------- Viewing All Books --------");

        List<Book> bookList = library.getBooks();
        if (bookList.isEmpty()) {
            System.out.println("Your library is empty.");
        } else {
            for (Book book : bookList) {
                System.out.println("\n" + book.toString());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: updates book status to "started" or "completed"
    // and if completed calls doBookCompleted
    private void updateBookStatus() {
        String command = null;

        System.out.println("\n -------- Updating Book Status --------");

        String title = checkTitle();

        System.out.println("\n Select from: ");
        System.out.println("\t c --> Mark book as completed and add rating and review");
        System.out.println("\t s --> Mark book as started");

        command = input.next();
        command = command.toLowerCase();

        if (command.equals("c")) {
            doBookCompleted(title);
        } else if (command.equals("s")) {
            for (int i = 0; i < library.getSize(); i++) {
                book.equals(library.getBooks().get(i));
                String bookTitle = book.getTitle();

                if (bookTitle.equalsIgnoreCase(title)) {
                    book.setReadingStatus("started");
                } else {
                    System.out.println("\n Book not found in library.");
                }
            }
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: if book is completed asks user for rating and review
    // and updates book in library
    private void doBookCompleted(String title) {
        int rating = 0;
        for (int i = 0; i < library.getSize(); i++) {
            book.equals(library.getBooks().get(i));
            String bookTitle = book.getTitle();

            if (bookTitle.equalsIgnoreCase(title)) {
                book.setReadingStatus("completed");

                System.out.println("\n Enter rating for book: ");
                String userRating = input.toString();
                rating = Integer.parseInt(userRating);

                if (rating < 1 || rating > 5) {
                    System.out.println("\n Not a valid rating (rate from 1 - 5)");
                } else {
                    book.setRating(rating);
                }

                System.out.println("\n Enter review for book: ");
                String review = input.toString();
                book.setReview(review);
            } else {
                System.out.println("\n Book not found in library.");
            }
        }
    }

    // EFFECTS: searches library for book based on genre or author
    private void doSearch() {
        String command = null;
        List<Book> searchList;

        System.out.println("\n -------- Searching for Book --------");

        System.out.println("\n How would you like to categorize your search: ");
        System.out.println("\t g --> By genre");
        System.out.println("\t a --> By author");

        command = input.next();
        command = command.toLowerCase();

        if (command.equalsIgnoreCase("g")) {
            System.out.println("Enter genre to search for: ");
            String genre = input.toString();

            if (genre.isEmpty()) {
                System.out.println("Genre is required.");
            } else {
                searchList = library.findBookByGenre(genre);

                for (Book book : searchList) {
                    System.out.println(book.toString());
                }
            }
        } else if (command.equalsIgnoreCase("a")) {
            System.out.println("Enter author to search for: ");
            String author = input.toString();

            if (author.isEmpty()) {
                System.out.println("Author is required.");
            } else {
                searchList = library.findBookByAuthor(author);

                for (Book book : searchList) {
                    System.out.println(book.toString());
                }
            }
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to edit or delete book,
    // if delete calls doRemoveBook, if edit update book in library
    private void doEditBook() {
        String command = null;

        System.out.println("\n -------- Editing a Book --------");
        
        String title = checkTitle();

        System.out.println("Would you like to delete (d) book or edit (e) this book?");
        command = input.next();
        command = command.toLowerCase();

        if (command.equalsIgnoreCase("d")) {
            for (int i = 0; i < library.getSize(); i++) {
                book.equals(library.getBooks().get(i));
                String bookTitle = book.getTitle();

                if (bookTitle.equalsIgnoreCase(title)) {
                    doRemoveBook(book);
                } else {
                    System.out.println("\n Book not found in library.");
                }
            }
        } else if (command.equalsIgnoreCase("e")) {
            for (int i = 0; i < library.getSize(); i++) {
                book.equals(library.getBooks().get(i));
                String bookTitle = book.getTitle();

                if (bookTitle.equalsIgnoreCase(title)) {
                    System.out.println("Enter new title or leave blank to be unchanged: ");
                    String newTitle = input.toString();
                    System.out.println("Enter new author or leave blank to be unchanged: ");
                    String newAuthor = input.toString();
                    System.out.println("Enter new genre or leave blank to be unchanged: ");
                    String newGenre = input.toString();
                    System.out.println("Enter new rating or leave blank to be unchanged: ");
                    String rating = input.toString();
                    int newRating = Integer.parseInt(rating);
                    System.out.println("Enter new review or leave blank to be unchanged: ");
                    String newReview = input.toString();

                    if (newTitle.isEmpty()) {
                        newTitle = book.getTitle();
                    } else if (newAuthor.isEmpty()) {
                        newAuthor = book.getAuthor();
                    } else if (newGenre.isEmpty()) {
                        newGenre = book.getGenre();
                    } else if (rating.isEmpty()) {
                        newRating = book.getRating();
                    } else if (newReview.isEmpty()) {
                        newReview = book.getReview();
                    }

                    library.editBook(bookTitle, newTitle, newAuthor, newGenre, newRating, newReview);

                } else {
                    System.out.println("\n Book not found in library.");
                }
            }
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes book from library
    private void doRemoveBook(Book book) {
        library.removeBook(book);
    }

    // EFFECTS: checks title of book user wants and returns title of book
    private String checkTitle() {
        System.out.println("\n Enter title of book: ");
        String title = input.toString();

        if (title.isEmpty()) {
            System.out.println("\n Title is required!");
        }

        return title;
    }
}
