User Management App
Overview
This is an Android application built with Kotlin that implements a Master form for managing users with CRUD (Create, Read, Update, Delete) operations. The app uses Room as the local database and includes features like form validations, pagination, search, and synchronized horizontal scrolling for the user list. It follows the MVVM (Model-View-ViewModel) architecture and incorporates best practices for Android development.
The app allows users to add, edit, and delete user records with fields like Login User ID (auto-generated from the name), Full Name, Address, Contact, Mobile Number, Email, Remarks, and more. The user list displays records in a RecyclerView with a scrollable header, pagination controls, and a search functionality.
Features

CRUD Operations:
Add new users with auto-generated Login User ID (e.g., "Sagar Mahajan" → "sagar.m").
Edit existing user records.
Delete users with a confirmation message.


Form Validations:
Validates fields for emptiness, email format, phone number format (10 digits), and uniqueness (Login User ID, Mobile Number, Email).
Displays error messages directly on the form fields.


User List:
Displays users in a RecyclerView with columns: Login User ID, Full Name, Mobile Number, Tax Number, Tab Enable, OTP Verify, Is Active, and Permissions.
Synchronized horizontal scrolling between the header and data rows.


Pagination:
Configurable page sizes (7, 10, 20, 50 entries per page).
"Previous," "Next," and numbered page buttons to navigate.
Displays "Showing X to Y of Z entries" information.


Search:
(Optional) Search functionality to filter users by name, email, or other fields.


Database:
Uses Room for local storage with migrations for schema updates.


UI:
Material Design components for a modern look.
Custom button styles for pagination controls.



Screenshots
(You can add screenshots here by taking them from your app and uploading them to GitHub. For example:)

screenshots/user_list.png: The main user list screen with pagination.
screenshots/add_edit_user.png: The Add/Edit user form.

Tech Stack

Language: Kotlin
Architecture: MVVM (Model-View-ViewModel)
Database: Room (SQLite)
Libraries:
Android Jetpack (ViewModel, LiveData, Room, RecyclerView)
Material Components
Coroutines for asynchronous operations


UI: XML layouts with View Binding

Prerequisites

Android Studio (version 2022.3.1 or later)
Kotlin plugin installed
Android device/emulator running API 21 (Lollipop) or higher

Setup Instructions

Clone the Repository:
git clone https://github.com/your-username/user-management-app.git
cd user-management-app


Open in Android Studio:

Launch Android Studio.
Select Open an existing project and choose the cloned repository folder.
Let Android Studio sync the project with Gradle.


Build the Project:

Ensure your build.gradle files include the following dependencies:// Project-level build.gradle
buildscript {
    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0"
    }
}

// App-level build.gradle
apply plugin: 'kotlin-kapt'

dependencies {
    implementation "androidx.core:core-ktx:1.12.0"
    implementation "androidx.appcompat:appcompat:1.6.1"
    implementation "com.google.android.material:material:1.12.0"
    implementation "androidx.constraintlayout:constraintlayout:2.1.4"
    implementation "androidx.recyclerview:recyclerview:1.3.2"
    implementation "androidx.room:room-runtime:2.6.1"
    kapt "androidx.room:room-compiler:2.6.1"
    implementation "androidx.room:room-ktx:2.6.1"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"
}


Sync the project with Gradle by clicking the "Sync Project with Gradle Files" button.


Run the App:

Connect an Android device or start an emulator.
Click the "Run" button in Android Studio to build and install the app.



Usage

View the User List:

The main screen displays a list of users with columns for Login User ID, Full Name, Mobile Number, etc.
Scroll horizontally to view all columns; the header scrolls in sync.
Use the pagination controls at the bottom to navigate between pages.
Adjust the page size using the "Show" spinner (7, 10, 20, 50 entries per page).


Add a New User:

Click the "Add New" button at the top.
Fill in the form (Full Name, Address, Contact, Mobile Number, Email, Remarks, etc.).
The Login User ID is auto-generated from the Full Name (e.g., "Sagar Mahajan" → "sagar.m").
Toggle switches for OTP Verify, Tab Enable, and Is Active as needed.
Select a User Level from the spinner.
Click "Modify" to save; validation errors will be shown on the form if any fields are invalid.


Edit a User:

In the user list, click the "Edit" button (pencil icon) next to a user.
Update the fields as needed and click "Modify" to save.


Delete a User:

In the user list, click the "Delete" button (trash icon) next to a user.
A confirmation message will be shown (e.g., "User Sagar Mahajan deleted").


Permissions:

Click the "Permissions" button (settings icon) to view a placeholder message. (This feature can be extended as needed.)



Project Structure

data/: Contains Room database classes (User, UserDao, AppDatabase, UserRepository).
viewmodel/: ViewModel classes (UserViewModel, UserViewModelFactory) for handling business logic.
res/layout/:
activity_user_list.xml: Main screen with user list, pagination, and search.
activity_add_edit_user.xml: Form for adding/editing users.
item_user_list.xml: Layout for each user row in the RecyclerView.
view_header_user.xml: Header for the main screen.


MainActivity.kt: Displays the user list and handles pagination.
AddEditUserActivity.kt: Manages the Add/Edit form with validations.
UserListAdapter.kt: Adapter for the RecyclerView to display user rows.

Future Improvements

Search Functionality: Implement the SearchView to filter users by name, email, or other fields.
Dynamic Pagination: Add more page number buttons dynamically based on the total number of pages.
Permissions Feature: Extend the "Permissions" button to manage page-level permissions for users.
Testing: Add unit tests for the ViewModel and Room database operations.
UI Enhancements: Add animations for transitions and improve the visual design.

Contributing
Contributions are welcome! Please follow these steps:

Fork the repository.
Create a new branch (git checkout -b feature/your-feature).
Make your changes and commit (git commit -m "Add your feature").
Push to the branch (git push origin feature/your-feature).
Open a Pull Request.

License
This project is licensed under the MIT License - see the LICENSE file for details.
Contact
For any questions or suggestions, feel free to reach out:

GitHub: Vinayak Patil
Email: patildvinayak@gmail.com




![taskss3](https://github.com/user-attachments/assets/b5458272-469b-45e6-8199-16cf9cf19a8d)
![taskss2](https://github.com/user-attachments/assets/1b0f1bb9-85c1-43b9-9e9b-7b297261a014)
![taskSs1](https://github.com/user-attachments/assets/1c286e3e-a12e-4a6a-923b-635942e154b5)
