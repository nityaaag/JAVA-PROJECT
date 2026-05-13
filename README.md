# 🏨 Lodgings & Restaurant Management System

A beginner-friendly hotel and restaurant management system built with Java 21, Spring Boot, and MySQL. This project demonstrates basic CRUD operations for room management.

## 📋 Features

### Room Management Module (Implemented)
- ✅ **View Rooms** - Display all rooms in a table format
- ✅ **Add Room** - Create new room entries
- ✅ **Edit Room** - Update room details
- ✅ **Delete Room** - Remove room from system
- ✅ **Room Filtering** - View rooms by status

### Room Attributes
- Room ID (Auto-generated)
- Room Number (Unique identifier)
- Room Type (Simple, Deluxe, Luxury, Suite, Lake View)
- Price (Nightly rate)
- Status (Available, Occupied, Under Maintenance)

## 🛠️ Technology Stack

| Component | Technology |
|-----------|-----------|
| **Language** | Java 21 |
| **Framework** | Spring Boot 3.2.0 |
| **Build Tool** | Gradle |
| **Template Engine** | Thymeleaf |
| **ORM** | Spring Data JPA |
| **Database** | MySQL 8.0+ |
| **Frontend** | HTML5 + CSS3 |
| **IDE** | IntelliJ IDEA |

## 📁 Project Structure

```
JAVA PROJECT/
├── src/
│   ├── main/
│   │   ├── java/com/lodging/project/
│   │   │   ├── LodgingApplication.java        # Main Spring Boot Application
│   │   │   ├── controller/
│   │   │   │   ├── HomeController.java        # Home page handler
│   │   │   │   └── RoomController.java        # Room CRUD operations
│   │   │   ├── entity/
│   │   │   │   └── Room.java                  # Room entity/model
│   │   │   ├── repository/
│   │   │   │   └── RoomRepository.java        # Database operations interface
│   │   │   └── service/
│   │   │       └── RoomService.java           # Business logic
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── index.html                 # Home page
│   │       │   ├── rooms.html                 # Room list view
│   │       │   ├── add-room.html              # Add room form
│   │       │   └── edit-room.html             # Edit room form
│   │       ├── static/css/
│   │       │   └── style.css                  # Styling
│   │       └── application.properties         # Configuration
│   └── test/                                  # Test files
├── build.gradle                               # Gradle build configuration
├── settings.gradle                            # Gradle settings
├── .gitignore                                 # Git ignore rules
└── README.md                                  # This file
```

## 🚀 Setup Instructions

### Prerequisites
Before starting, ensure you have installed:
- **Java 21** or higher
- **IntelliJ IDEA** (Community or Ultimate Edition)
- **MySQL 8.0** or higher
- **Git**

### Step 1: Create MySQL Database

Open MySQL command line and run:

```sql
-- Create database
CREATE DATABASE lodging_db;

-- Use the database
USE lodging_db;

-- Tables will be created automatically by Hibernate (Spring Data JPA)
-- when you run the application for the first time
```

### Step 2: Configure Database Connection

Edit `src/main/resources/application.properties`:

```properties
# MySQL Connection
spring.datasource.url=jdbc:mysql://localhost:3306/lodging_db
spring.datasource.username=root
spring.datasource.password=your_password
```

Replace `your_password` with your actual MySQL password.

### Step 3: Build the Project using Gradle

Open terminal in the project directory and run:

```bash
# On Windows
gradlew build

# On Mac/Linux
./gradlew build
```

This command:
- Downloads all dependencies
- Compiles the Java code
- Packages the application

### Step 4: Run the Application

```bash
# On Windows
gradlew bootRun

# On Mac/Linux
./gradlew bootRun
```

Or run directly from IntelliJ IDEA:
1. Click on `Run` menu
2. Select `Run 'LodgingApplication'`
3. Or click the green play button ▶ next to the class name

### Step 5: Access the Application

Open your web browser and navigate to:
```
http://localhost:8080
```

## 📚 Understanding the Code

### Project Layers

#### 1. **Controller Layer** (`controller/`)
- **Responsibility**: Handle HTTP requests and responses
- **HomeController.java**: Routes to home page
- **RoomController.java**: Handles room CRUD operations

#### 2. **Service Layer** (`service/`)
- **Responsibility**: Contains business logic
- **RoomService.java**: Methods for room operations
- Acts as a bridge between Controller and Repository

#### 3. **Repository Layer** (`repository/`)
- **Responsibility**: Database operations
- **RoomRepository.java**: Extends JpaRepository
- Provides CRUD methods automatically

#### 4. **Entity Layer** (`entity/`)
- **Responsibility**: Database model/table structure
- **Room.java**: Represents a room in the database
- Annotations map this class to database table

### Application Flow

```
User Access http://localhost:8080
        ↓
HomeController → index.html (Home Page)
        ↓
Click "Manage Rooms"
        ↓
RoomController.getAllRooms() → rooms.html
        ↓
User Actions:
  - Click "Add Room" → RoomController.addRoomForm() → add-room.html
  - Click "Edit" → RoomController.editRoomForm() → edit-room.html
  - Click "Delete" → RoomController.deleteRoom()
  - Form Submit → RoomController.saveRoom() → Database → Redirect to rooms.html
```

## 🔧 Gradle Dependencies Explained

```gradle
// Web framework for building web applications
implementation 'org.springframework.boot:spring-boot-starter-web'

// ORM for database operations
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

// Template engine for dynamic HTML rendering
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'

// MySQL database driver
runtimeOnly 'com.mysql:mysql-connector-j'

// Reduces boilerplate code (Getters, Setters, Constructors)
compileOnly 'org.projectlombok:lombok'

// Automatic restart during development
developmentOnly 'org.springframework.boot:spring-boot-devtools'
```

## 📝 Key Annotations Explained

### Java Annotations Used

| Annotation | Purpose | Location |
|-----------|---------|----------|
| `@SpringBootApplication` | Marks main application class | LodgingApplication.java |
| `@Controller` | Marks class as web controller | HomeController, RoomController |
| `@GetMapping` | Maps HTTP GET requests | Controller methods |
| `@PostMapping` | Maps HTTP POST requests | Controller methods |
| `@PathVariable` | Extracts path parameters | Controller methods |
| `@ModelAttribute` | Binds form data to objects | Controller methods |
| `@Service` | Marks class as business logic | RoomService |
| `@Repository` | Marks interface as data access | RoomRepository |
| `@Autowired` | Dependency injection | Service/Controller fields |
| `@Entity` | Marks class as JPA entity | Room.java |
| `@Table` | Specifies table name | Room.java |
| `@Id` | Marks primary key | Room.id |
| `@GeneratedValue` | Auto-increment ID | Room.id |
| `@Column` | Specifies column properties | Room fields |

## 🗄️ Database Schema

### rooms Table (Auto-created by Hibernate)

```sql
CREATE TABLE rooms (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_number VARCHAR(50) NOT NULL UNIQUE,
    room_type VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    status VARCHAR(50) NOT NULL
);
```

## 🐛 Troubleshooting

### Issue: Application won't connect to MySQL

**Solution:**
1. Check MySQL is running
2. Verify database name: `lodging_db`
3. Check username/password in `application.properties`
4. Ensure MySQL is on port 3306

### Issue: Table "rooms" doesn't exist

**Solution:**
1. This is normal - Spring Data JPA creates it automatically
2. Check `application.properties` has `spring.jpa.hibernate.ddl-auto=update`
3. Restart the application

### Issue: Port 8080 already in use

**Solution:**
1. Change port in `application.properties`: `server.port=8081`
2. Or kill the process using port 8080

### Issue: Gradle build fails

**Solution:**
```bash
# Clear gradle cache
gradlew clean

# Build again
gradlew build
```

## 📦 Build and Deployment

### Using Gradle

```bash
# Build the project
gradlew build

# Run tests
gradlew test

# Clean build
gradlew clean build

# Run application
gradlew bootRun

# Create JAR file (Production)
gradlew bootJar
```

### Run the JAR file

```bash
java -jar build/libs/lodging-0.0.1-SNAPSHOT.jar
```

## 🌐 GitHub Setup

### Initialize Git Repository

```bash
# Initialize git (if not already done)
git init

# Add all files
git add .

# Create first commit
git commit -m "Initial project setup"
```

### Push to GitHub

```bash
# Add remote repository
git remote add origin https://github.com/YOUR_USERNAME/lodging-management.git

# Push to GitHub
git branch -M main
git push -u origin main
```

### Useful Git Commands

```bash
# Check git status
git status

# View commit history
git log --oneline

# Create feature branch
git checkout -b feature/new-feature

# Merge branch
git checkout main
git merge feature/new-feature

# Push specific branch
git push origin feature-branch
```

## 📋 File Purposes and Explanations

### Java Files

| File | Purpose |
|------|---------|
| `LodgingApplication.java` | Entry point of the application |
| `HomeController.java` | Handles home page route |
| `RoomController.java` | Handles all room CRUD routes |
| `Room.java` | Database entity for room data |
| `RoomRepository.java` | Interface for database CRUD operations |
| `RoomService.java` | Business logic for room operations |

### HTML Files (Thymeleaf Templates)

| File | Purpose |
|------|---------|
| `index.html` | Welcome/home page |
| `rooms.html` | Displays all rooms in a table |
| `add-room.html` | Form to create new room |
| `edit-room.html` | Form to edit existing room |

### Configuration Files

| File | Purpose |
|------|---------|
| `application.properties` | Database and Spring Boot settings |
| `build.gradle` | Project dependencies and build configuration |
| `settings.gradle` | Gradle project settings |

## 🎓 Learning Outcomes

After completing this project, you will understand:
- ✅ Spring Boot project structure and conventions
- ✅ CRUD operations with Spring Data JPA
- ✅ Building web controllers and handling HTTP requests
- ✅ Using Thymeleaf for dynamic HTML rendering
- ✅ Working with MySQL database
- ✅ Gradle build automation
- ✅ MVC (Model-View-Controller) architecture
- ✅ Spring annotations and dependency injection

## 📝 Future Enhancements

Possible features to add later:
- Authentication and authorization
- Restaurant menu management
- Booking system
- Payment gateway integration
- Email notifications
- Search and filtering functionality
- Admin dashboard with statistics
- API endpoints (REST/GraphQL)

## 🤝 Contributing

This is a beginner-level educational project. Feel free to:
1. Fork the repository
2. Create a feature branch
3. Make improvements
4. Submit a pull request

## 📄 License

This project is open source and available for educational purposes.

## 👨‍💻 Project Information

- **Course**: BCA (Bachelor of Computer Applications)
- **Year**: 2nd Year
- **Type**: Semester Project
- **Duration**: Semester Project
- **Author**: [Your Name]
- **Date**: 2024

## 📞 Support

For issues, questions, or suggestions:
1. Check the troubleshooting section
2. Review Spring Boot documentation: https://spring.io/projects/spring-boot
3. Check Thymeleaf documentation: https://www.thymeleaf.org/
4. MySQL documentation: https://dev.mysql.com/doc/

---

**Happy Coding!** 🚀

If you found this project helpful, please ⭐ star the repository!
