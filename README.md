carDeal

carDeal is an Android application developed during an internship in 2023. The application is designed to facilitate car dealership operations, providing features such as vehicle inventory management, customer data handling, and sales tracking.

Description

The carDeal application aims to streamline the processes within a car dealership by offering a user-friendly interface for managing various aspects of the business. Key functionalities include:
	•	Vehicle Inventory Management: Add, update, and remove vehicles from the inventory with detailed information such as make, model, year, price, and specifications.
	•	Customer Data Handling: Maintain a database of customer information, including contact details, purchase history, and preferences.
	•	Sales Tracking: Monitor sales performance with features to record transactions, generate invoices, and analyze sales data.

Interesting Techniques

This project employs several noteworthy techniques:
	•	Model-View-ViewModel (MVVM) Architecture: Separates the development of the graphical user interface from the business logic, enhancing code maintainability and testability.
	•	Data Binding: Facilitates automatic synchronization between the UI components and the underlying data models, reducing boilerplate code.
	•	LiveData: Ensures that the UI reflects the latest data by observing data changes in a lifecycle-aware manner.

Libraries and Technologies

In addition to standard Android development tools, carDeal integrates the following libraries:
	•	Retrofit: A type-safe HTTP client for Android and Java, used for network operations.
	•	Glide: An image loading and caching library for Android, utilized for efficient image handling.
	•	Room: A persistence library that provides an abstraction layer over SQLite, streamlining database access.

Project Structure

The repository is organized as follows:

<pre>
.
├── .idea/                  # Project-specific settings for the IDE
├── app/                    # Main application source code
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── cardeal/
│   │   │   │               ├── ui/             # UI components and activities
│   │   │   │               ├── data/           # Data models and repositories
│   │   │   │               ├── viewmodel/      # ViewModel classes
│   │   │   │               └── utils/          # Utility classes
│   │   │   ├── res/                            # Resource files (layouts, drawables, etc.)
│   │   │   └── AndroidManifest.xml             # Application manifest file
│   ├── build.gradle                            # Module-level Gradle build file
├── dataentry/              # Contains data entry-related components
├── gradle/
│   └── wrapper/            # Gradle wrapper files
├── .gitignore              # Specifies files to ignore in version control
├── build.gradle            # Build configuration script
├── gradle.properties       # Gradle properties
├── gradlew                 # Unix shell script to run Gradle
├── gradlew.bat             # Windows batch script to run Gradle
└── settings.gradle         # Settings for the Gradle build
</pre>


Highlights in the Structure
	•	.idea/: Contains IDE-specific configurations and settings.
	•	app/: Houses the main application code, including source files and resources.
	•	dataentry/: Includes components or scripts related to data entry functionalities.
	•	gradle/wrapper/: Contains the Gradle Wrapper files, enabling consistent build environments.
