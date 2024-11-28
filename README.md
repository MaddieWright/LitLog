# LitLog - *A reader's best friend*

## Purpose
The library management sysytem, **LitLog**, will allow users to efficiently organize and track their personal book collections. Users will be able to add books to their digital library, categorize them by genre or custom tags, and update the reading status for each book (e.g., not started, in progress, completed). Furthermore, users can rate the books they have read, write personal reviews, and maintian a history of their reading journey. The system will also feature search and filtering options to help users quickly find books within their library.

This application is designed for avid readers, book collecters, or anyone who wants to keep track of their reading habits in a more structured way. It could be useful for book club members, students, or literature enthusiasts who want to document their experiences with each book. As a fellow reader myself, I believe it would be useful to have a system that not only tracks my reading progress, but also allows me to reflect on what I've read through reviews and ratings.

## User Stories
Some features and specific tasks I would like to accomplish as a **user** with this application are:
- As a **user**, I want to be able to add a new Book to my Library and specify the title, author, and genre.
- As a **user**, I want to be able to view a list of all books in my library.
- As a **user**, I want to be able to mark a book as completed and add a rating with a review.
- As a **user**, I want to be able to categorize books by genre or author for easy seraching.
- As a **user**, I want to to be able to remove or edit books in my library.
- As a **user**, I want to be able to save my library to file (if I choose so).
- As a **user**, I want to be able to load my library from file (if I choose so).

## Instructions for End User

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking on the **Add Book** menu button, then entering the title, author, and genre of the book and press the add button.
- You can generate the second required action related to the user story "list all the Xs in my Y" by clicking on the **View All Books** menu button.
- You can generate the third action related to the user story, marking a book complete with a review and rating, by clicking on the **Edit Books** menu button, then entering the review and rating for book to edit and pressing the complete button.
- You can generate the fourth action related to the user story, searching library by author or genre, by clicking on the **Search Books** menu button then entering either the author or genre to search for and pressing the search button.
- My visual component is a background image located on the menu panel.
- You can save the state of my application by clicking on the **Save** button located at the bottom of the application.
- You can reload the state of my application by clicking on the **Load** button located at the bottom of the application.

## Phase 4: Task 2
Below is a representative sample of the events logged during a session with LitLog. These events are printed to the console when the application is closed.

Application Event Log:\
Added Book to Library: "Pride and Prejudice" by Jane Austen at Sun Nov 23 14:12:45 PST 2024\
Marked Book as completed: "Pride and Prejudice" with with rating 4/5 and review: Amazing at Sun Nov 23 14:15:10 PST 2024\
Library saved to file at Sun Nov 23 14:20:00 PST 2024\
Removed Book in Library: "The Catcher in the Rye" by J.D. Salinger at Sun Nov 23 14:25:30 PST 2024\
Library loaded from file at Sun Nov 23 14:30:05 PST 2024\
Searched Books in Library by author: Jane Austen at Sun Nov 23 14:31:30 PST 2024\
Displayed Books in Library at Sat Nov 23 14:13:37 PST 2024

This sample demonstrates the functionality of the event logging system, which records key actions taken by the user during the session. Each event includes a description and a timestamp indicating when the action occurred.

## Phase 4: Task 3
Reflecting on the design presented in the UML class diagram, one area of improvement lies in the separation of concerns within the Library and LibraryApp/LibraryGUI classes. Currently, Library handles both data storage and some operations related to books and users, while LibraryApp and LibraryGUI combines UI logic with book management functionality. If more time were available, I would refactor these classes to better adhere to the Single Responsibility Principle (SRP). In LibraryApp, I would decouple the UI logic from the backend logic by introducing a LibraryController class to mediate between the UI and the core logic. This separation would make the application more modular, easier to test, and more maintainable. I would also move JSON serialization and deserialization logic into a separate PersistenceManager class, ensuring that Library and other classes don’t get tied to the specifics of data storage.

I would also introduce an observer pattern into the design to improve the flexibility and maintainability of the system, particularly when it comes to event logging and handling changes in the application's state. Currently, the EventLog class is directly associated with other components like Library and Book, which tightly couples the event logging functionality to the application's core logic. By implementing an observer pattern, we could decouple the event generation and event handling, allowing different parts of the system to respond to events independently.  This would allow the system to be more modular and making it easier to add new observers in the future without altering the core event logging functionality. Additionally, this approach promotes better separation of concerns, as each component is responsible only for its own logic, reducing the overall complexity of the system and improving maintainability.
 