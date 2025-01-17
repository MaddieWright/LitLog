package ui;

import javax.swing.*;

import exception.LogException;
import model.Book;
import model.EventLog;
import model.Library;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 * Main GUI for the Library app.
 */
public class LibraryGUI extends JFrame {
    private Library library;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private DefaultListModel<String> bookListModel;
    private JList<String> bookList;

    private static final String JSON_STORE = "./lib/data/library.json";

    // Buttons for main menu actions
    private JButton addBookButton;
    private JButton viewBooksButton;
    private JButton searchBooksButton;
    private JButton editBooksButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;

    private ImageIcon libraryImage;
    private Color rgbColor = new Color(253, 253, 150);

    // EFFECTS: Constructor sets up size, layout, and components.
    public LibraryGUI() {
        setTitle("LitLog - A Reader's Best Friend");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        getContentPane().setBackground(rgbColor);

        // Create components
        addHeader();
        addWelcomeAndMenu();
        addLibrarySection();
        addBottomButtons();

        setVisible(true);

        init();

        addWindowListener();
    }

    // EFFECTS: Add WindowListener to handle application exit and print event log
    private void addWindowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    LogPrinter lp = new ConsolePrinter(); // LogPrinter for console
                    lp.printLog(EventLog.getInstance());
                } catch (LogException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "System Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes library
    private void init() {
        library = new Library();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: Add header with title
    private void addHeader() {
        JLabel headerLabel = new JLabel("LitLog - A Reader's Best Friend", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);
    }

    // EFFECTS: Add welcome message and menu options on the left
    private void addWelcomeAndMenu() {
        JPanel menuPanel = addBackgroundToMenuPanel();

        JLabel welcomeLabel = new JLabel("Welcome to LitLog!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        menuPanel.add(welcomeLabel);

        addBookButton = new JButton("Add Book");
        viewBooksButton = new JButton("View All Books");
        searchBooksButton = new JButton("Search Books");
        editBooksButton = new JButton("Edit Books");

        menuPanel.add(addBookButton);
        menuPanel.add(viewBooksButton);
        menuPanel.add(searchBooksButton);
        menuPanel.add(editBooksButton);

        add(menuPanel);

        menuActionListeners(addBookButton, viewBooksButton, searchBooksButton, editBooksButton);
    }

    // EFFECTS: Add action listeners for menu button options
    private void menuActionListeners(JButton addBookButton, JButton viewBooksButton, JButton searchBooksButton,
            JButton editBooksButton) {
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddBookScreen();
            }
        });
        viewBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openViewBooksScreen();
            }
        });
        searchBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSearchBooksScreen();
            }
        });
        editBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openEditBooksScreen();
            }
        });
    }

    // EFFECTS: Sets up menu panel with background image and returns it to
    // addWelcomeAndMenu()
    private JPanel addBackgroundToMenuPanel() {
        String sep = System.getProperty("file.separator");
        libraryImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "image" + sep + "litlog.png");
        Image bgImage = libraryImage.getImage();

        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        menuPanel.setLayout(new GridLayout(17, 1, 8, 8));
        return menuPanel;
    }

    // EFFECTS: Add library section on the right to display list of books
    private void addLibrarySection() {
        JPanel libraryPanel = new JPanel(new BorderLayout());
        JLabel libraryLabel = new JLabel("My Library", SwingConstants.CENTER);
        libraryLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        libraryPanel.add(libraryLabel, BorderLayout.NORTH);
        libraryPanel.setBackground(Color.WHITE);

        bookListModel = new DefaultListModel<>();
        bookList = new JList<>(bookListModel);
        JScrollPane scrollPane = new JScrollPane(bookList);
        libraryPanel.add(scrollPane, BorderLayout.CENTER);

        add(libraryPanel, BorderLayout.EAST);
    }

    // EFFECTS: Add Save, Load, and Quit buttons at the bottom
    private void addBottomButtons() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(rgbColor);

        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        quitButton = new JButton("Quit");

        bottomPanel.add(saveButton);
        bottomPanel.add(loadButton);
        bottomPanel.add(quitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        addBottomActionListeners();
    }

    // EFFECTS: Add action listeners for bottom buttons
    private void addBottomActionListeners() {
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveLibrary();
            }
        });
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadLibrary();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    LogPrinter lp = new ConsolePrinter(); // LogPrinter for console
                    lp.printLog(EventLog.getInstance());
                } catch (LogException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "System Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                System.exit(0);
            }
        });
    }

    // EFFECTS: Method to open and set up Add Book screen and include "Back" button
    @SuppressWarnings("methodlength")
    private void openAddBookScreen() {
        getContentPane().removeAll();
        doBackButton();

        JPanel addBookPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        addBookPanel.setBackground(rgbColor);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        JTextField titleField = new JTextField();
        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        JTextField authorField = new JTextField();
        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        JTextField genreField = new JTextField();
        JButton submitButton = new JButton("Add Book");

        addBookPanel.add(titleLabel);
        addBookPanel.add(titleField);
        addBookPanel.add(authorLabel);
        addBookPanel.add(authorField);
        addBookPanel.add(genreLabel);
        addBookPanel.add(genreField);
        addBookPanel.add(new JLabel());
        addBookPanel.add(submitButton);

        add(addBookPanel, BorderLayout.CENTER);
        revalidate();
        repaint();

        addBooksActionListener(submitButton, titleField, authorField, genreField);
    }

    // MODIFIES: library
    // EFFECTS: Add action listener for openAddBookScreen()
    private void addBooksActionListener(JButton submitButton, JTextField titleField, JTextField authorField,
            JTextField genreField) {
        submitButton.addActionListener(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String genre = genreField.getText();
            library.addBook(new Book(title, author, genre));
            returnToMainScreen();
            displayAllBooks();
        });
    }

    // EFFECTS: Refreshes the list of books displayed
    private void displayAllBooks() {
        for (Book book : library.getBooks()) {
            bookListModel.addElement(toStringFormat(book));
        }
    }

    // EFFECTS: Method to open View Books screen and include "Back" button
    private void openViewBooksScreen() {
        getContentPane().removeAll();
        doBackButton();

        // Create panel to hold library list
        JPanel libraryPanel = new JPanel(new BorderLayout());
        JLabel libraryLabel = new JLabel("My Library", SwingConstants.CENTER);
        libraryPanel.setBackground(rgbColor);
        libraryLabel.setFont(new Font("Calibri", Font.BOLD, 25));
        libraryPanel.add(libraryLabel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(bookList);
        libraryPanel.add(scrollPane, BorderLayout.CENTER);

        add(libraryPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    // EFFECTS: Method to open Search Books screen and include "Back" button
    @SuppressWarnings("methodlength")
    private void openSearchBooksScreen() {
        getContentPane().removeAll();
        doBackButton();

        JPanel searchPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        searchPanel.setBackground(rgbColor);

        JLabel authorLabel = new JLabel("Search by Author:");
        authorLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        JTextField authorField = new JTextField();
        JButton authorSearchButton = new JButton("Search");

        JLabel genreLabel = new JLabel("Search by Genre:");
        genreLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        JTextField genreField = new JTextField();
        JButton genreSearchButton = new JButton("Search");

        searchPanel.add(authorLabel);
        searchPanel.add(authorField);
        searchPanel.add(authorSearchButton);
        searchPanel.add(genreLabel);
        searchPanel.add(genreField);
        searchPanel.add(genreSearchButton);

        add(searchPanel, BorderLayout.CENTER);

        JPanel displayPanel = doSearchDisplayPanel();
        searchPanel.add(displayPanel, BorderLayout.CENTER);

        searchBookActionListeners(authorSearchButton, genreSearchButton, authorField, genreField);

        revalidate();
        repaint();
    }

    // EFFECTS: Add action listeners for openSearchBooksScreen()
    private void searchBookActionListeners(JButton authorSearchButton, JButton genreSearchButton,
            JTextField authorField, JTextField genreField) {
        authorSearchButton.addActionListener(e -> {
            String author = authorField.getText();
            bookListModel.clear();
            for (Book book : library.findBookByAuthor(author)) {
                bookListModel.addElement(toStringFormat(book));
            }
        });

        genreSearchButton.addActionListener(e -> {
            String genre = genreField.getText();
            bookListModel.clear();
            for (Book book : library.findBookByGenre(genre)) {
                bookListModel.addElement(toStringFormat(book));
            }
        });
    }

    // EFFECTS: Set up display panel for result of book search and returns new panel
    // to
    // openSearchBooksScreen()
    private JPanel doSearchDisplayPanel() {
        JPanel displayPanel = new JPanel(new BorderLayout());

        // Initialize bookListModel and bookList for displaying books
        bookListModel = new DefaultListModel<>();
        bookList = new JList<>(bookListModel);

        // Add book list to a scroll pane and then to libraryPanel
        JScrollPane scrollPane = new JScrollPane(bookList);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        return displayPanel;
    }

    // EFFECTS: Method to open Edit Books screen and include "Back" button
    @SuppressWarnings("methodlength")
    private void openEditBooksScreen() {
        getContentPane().removeAll();
        doBackButton();

        JPanel editPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        editPanel.setBackground(rgbColor);

        JLabel bookLabel = new JLabel("Enter Book Title to Edit:");
        bookLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        JTextField bookField = new JTextField();
        JButton completeButton = new JButton("Complete");
        JLabel bookReview = new JLabel("Enter Review below:");
        bookReview.setFont(new Font("Calibri", Font.BOLD, 18));
        JLabel bookRating = new JLabel("Enter Rating below:");
        bookRating.setFont(new Font("Calibri", Font.BOLD, 18));
        JTextField reviewField = new JTextField();
        JTextField rateField = new JTextField();
        JButton startButton = new JButton("Start Book");
        JButton removeButton = new JButton("Remove Book");

        editPanel.add(bookLabel);
        editPanel.add(bookField);
        editPanel.add(bookReview);
        editPanel.add(reviewField);
        editPanel.add(bookRating);
        editPanel.add(rateField);
        editPanel.add(completeButton);
        editPanel.add(startButton);
        editPanel.add(removeButton);

        add(editPanel, BorderLayout.CENTER);
        revalidate();
        repaint();

        editBooksActionListeners(completeButton, startButton, removeButton, bookField, reviewField, rateField);
    }

    // MODIFIES: library, book
    // EFFECTS: Add action listeners for openEditBooksScreen()
    private void editBooksActionListeners(JButton completeButton, JButton startButton, JButton removeButton,
            JTextField bookField, JTextField reviewField, JTextField rateField) {
        completeButton.addActionListener(e -> {
            Book b = findBook(bookField);
            int rating = Integer.parseInt(rateField.getText());
            b.setRating(rating);
            String review = reviewField.getText();
            b.setReview(review);

            b.setReadingStatus("completed");

            displayAllBooks();
        });

        startButton.addActionListener(e -> {
            Book b = findBook(bookField);
            b.setReadingStatus("started");
            displayAllBooks();
        });

        removeButton.addActionListener(e -> {
            Book b = findBook(bookField);
            library.removeBook(b);
            bookListModel.removeElement(b);
            displayAllBooks();
        });
    }

    // EFFECTS: Method to find book in library given title
    private Book findBook(JTextField bookField) {
        String title = bookField.getText();
        Book foundBook = null;
        for (Book b : library.getBooks()) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                foundBook = b;
            }
        }
        return foundBook;
    }

    // MODIFIES: this
    // EFFECTS: Method to load library from file
    private void loadLibrary() {
        try {
            library = jsonReader.read();
            bookListModel.clear();
            displayAllBooks();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: Method to save books to file
    private void saveLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(library);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Library saved successfully!", "Save Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: Method to set up back button
    private void doBackButton() {
        JButton backButton = new JButton("Back!");
        backButton.addActionListener(e -> {
            returnToMainScreen();
            displayAllBooks();
        });
        add(backButton, BorderLayout.NORTH);
    }

    // EFFECTS: Method to return to main screen when "Back" button is selected
    private void returnToMainScreen() {
        getContentPane().removeAll();
        addHeader();
        addWelcomeAndMenu();
        addLibrarySection();
        addBottomButtons();

        revalidate();
        repaint();
    }

    // EFFECTS: Displays Books in format so that each Book field is on new line
    public String toStringFormat(Book b) {
        return "<html>Title: " + b.getTitle() + "<br/>Author: " + b.getAuthor() + "<br/>Genre: " + b.getGenre()
                + "<br/>Status: " + b.getReadingStatus() + "<br/>Rating: " + b.getRating() + "<br/>Review: "
                + b.getReview() + "<br/><br/>";
    }
}
