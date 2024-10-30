package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main GUI for the Library app.
 */
public class LibraryGUI extends JFrame {

    // Buttons for main menu actions
    private JButton addBookButton;
    private JButton viewBooksButton;
    private JButton searchBooksButton;
    private JButton editBooksButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;

    // Display panel
    private JPanel libraryPanel;

    // Constructor sets up size, layout, and components.
    public LibraryGUI() {
        setTitle("LitLog - A Reader's Best Friend");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Create components
        addHeader();
        addWelcomeAndMenu();
        addLibrarySection();
        addBottomButtons();

        setVisible(true);
    }

    // Add header with title
    private void addHeader() {
        JLabel headerLabel = new JLabel("LitLog - A Reader's Best Friend", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);
    }

    // Add welcome message and menu options on the left
    private void addWelcomeAndMenu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome to LitLog!", SwingConstants.CENTER);
        menuPanel.add(welcomeLabel);

        addBookButton = new JButton("Add Book");
        viewBooksButton = new JButton("View All Books");
        searchBooksButton = new JButton("Search Books");
        editBooksButton = new JButton("Edit Books");

        menuPanel.add(addBookButton);
        menuPanel.add(viewBooksButton);
        menuPanel.add(searchBooksButton);
        menuPanel.add(editBooksButton);

        add(menuPanel, BorderLayout.WEST);

        // Add action listeners for menu button options
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

    // Add library section on the right to display list of books
    private void addLibrarySection() {
        libraryPanel = new JPanel();
        libraryPanel.setLayout(new BoxLayout(libraryPanel, BoxLayout.Y_AXIS));

        JLabel libraryLabel = new JLabel("My Library", SwingConstants.CENTER);
        libraryPanel.add(libraryLabel);

        // libraryPanel.add(); add view books here

        add(libraryPanel, BorderLayout.EAST); // fix format!!
    }

    // Add Save, Load, and Quit buttons at the bottom
    private void addBottomButtons() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        quitButton = new JButton("Quit");

        bottomPanel.add(saveButton);
        bottomPanel.add(loadButton);
        bottomPanel.add(quitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Add action listeners for bottom button options
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadLibrary();
            }
        });
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveLibrary();
            }
        });
        quitButton.addActionListener(e -> System.exit(0));
    }

    // Method to open Add Book screen and include "Back" button
    private void openAddBookScreen() {

    }

    // Method to open View Books screen and include "Back" button
    private void openViewBooksScreen() {

    }

    // Method to open Search Books screen and include "Back" button
    private void openSearchBooksScreen() {

    }

    // Method to open Edit Books screen and include "Back" button
    private void openEditBooksScreen() {

    }

    // Method to load library from file
    private void loadLibrary() {

    }

    // Method to save books to file
    private void saveLibrary() {

    }

    // Method to return to main screen whne "Back" button is selected
    private void returnToMainScreen() {

    }

}
