# 📁 Project Structure Explanation

Detailed explanation of every file and folder in the Lodgings & Restaurant Management System project.

## 📂 Root Directory Structure

```
JAVA PROJECT/
├── gradle/                          # Gradle wrapper files (auto-generated)
│   ├── wrapper/
│   │   ├── gradle-wrapper.jar
│   │   └── gradle-wrapper.properties
│   └── ...
│
├── src/                             # Source code root directory
│   ├── main/                        # Production code
│   │   ├── java/                   # Java source files
│   │   │   └── com/lodging/project/    # Package: com.lodging.project
│   │   │       ├── controller/     # Web controllers (handle HTTP requests)
│   │   │       ├── entity/         # Database entities (models)
│   │   │       ├── repository/     # Data access interfaces
│   │   │       ├── service/        # Business logic layer
│   │   │       └── LodgingApplication.java   # Main application class
│   │   │
│   │   └── resources/              # Configuration and static files
│   │       ├── templates/          # Thymeleaf HTML templates
│   │       ├── static/             # Static resources (CSS, JS, images)
│   │       └── application.properties    # Configuration file
│   │
│   └── test/                        # Test code (unit tests)
│
├── build/                           # Generated during build (ignored by Git)
│   ├── classes/                     # Compiled Java classes
│   ├── libs/                        # JAR files
│   └── ...
│
├── .gradle/                         # Gradle cache (ignored by Git)
├── build.gradle                     # Gradle build configuration
├── settings.gradle                  # Gradle project settings
├── .gitignore                       # Files to ignore in Git
├── README.md                        # Project documentation
├── SETUP.md                         # Setup instructions
├── PROJECT_STRUCTURE.md             # This file
└── database-setup.sql               # SQL database setup script
```

---

## 🗂️ Detailed File Descriptions

### 1. Configuration Files

#### `build.gradle` ⚙️
**Purpose:** Gradle build configuration file

**Contains:**
- Project metadata (group, version, name)
- Plugins (Spring Boot, Dependency Management)
- Repository configuration (Maven Central)
- Dependencies (Spring Boot, MySQL, Thymeleaf, etc.)
- Build tasks

**Key Dependencies:**
- `spring-boot-starter-web` - Web framework
- `spring-boot-starter-data-jpa` - ORM framework
- `spring-boot-starter-thymeleaf` - Template engine
- `mysql-connector-j` - MySQL driver
- `lombok` - Code generation library

**Why Important:**
- Tells Gradle how to compile, build, and package the project
- Manages all external library versions
- Without it, project can't build

#### `settings.gradle` ⚙️
**Purpose:** Gradle project settings

**Contains:**
- Root project name: `lodging-management-system`

**Why Important:**
- Identifies the project structure for Gradle
- Must match the IntelliJ project name

#### `application.properties` 🔧
**Location:** `src/main/resources/application.properties`

**Purpose:** Spring Boot configuration file

**Contains:**
- Server port: `8080`
- MySQL connection details (URL, username, password)
- JPA/Hibernate settings
- Thymeleaf configuration
- Logging levels

**Example:**
```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/lodging_db
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

**Why Important:**
- Configures database connection
- Without correct settings, application can't connect to database

---

### 2. Java Source Files

#### **Controller Layer** 🎮
Location: `src/main/java/com/lodging/project/controller/`

##### `HomeController.java`
**Purpose:** Handles home page requests

**What it does:**
- Maps URL: `GET /`
- Returns: `index.html`

**Code Flow:**
```
User visits http://localhost:8080
     ↓
HomeController receives request
     ↓
Returns index.html template
     ↓
User sees home page
```

**Methods:**
- `home()` - GET request handler for `/`

##### `RoomController.java`
**Purpose:** Handles all room-related requests (CRUD operations)

**What it does:**
- Manages room creation, reading, updating, deletion
- Handles form submissions
- Works with RoomService for business logic

**Methods:**
| Method | HTTP Method | URL | Purpose |
|--------|------------|-----|---------|
| `getAllRooms()` | GET | `/rooms` | Show all rooms |
| `addRoomForm()` | GET | `/rooms/add` | Show add room form |
| `saveRoom()` | POST | `/rooms/save` | Save new/updated room |
| `editRoomForm()` | GET | `/rooms/edit/{id}` | Show edit form |
| `deleteRoom()` | GET | `/rooms/delete/{id}` | Delete a room |

**Code Flow Example (Add Room):**
```
User clicks "Add Room" button
     ↓
GET /rooms/add
     ↓
RoomController.addRoomForm() called
     ↓
Returns add-room.html with empty Room object
     ↓
User fills form and clicks Save
     ↓
POST /rooms/save (form data)
     ↓
RoomController.saveRoom() called
     ↓
Calls RoomService.saveRoom()
     ↓
Room saved to database
     ↓
Redirects to /rooms
     ↓
getAllRooms() shows updated list
```

#### **Entity Layer** 📊
Location: `src/main/java/com/lodging/project/entity/`

##### `Room.java`
**Purpose:** Represents a room in the database

**What it is:**
- A JPA Entity (maps to database table)
- Model class with fields and getters/setters
- Uses Lombok annotations to reduce code

**Database Mapping:**
```java
@Entity
@Table(name = "rooms")
public class Room {
    @Id @GeneratedValue
    private Long id;          // Column: id (PRIMARY KEY, AUTO_INCREMENT)
    
    @Column(nullable = false, unique = true)
    private String roomNumber; // Column: room_number
    
    @Column(nullable = false)
    private String roomType;   // Column: room_type
    
    @Column(nullable = false)
    private Double price;      // Column: price
    
    @Column(nullable = false)
    private String status;     // Column: status
}
```

**Generated SQL Table:**
```sql
CREATE TABLE rooms (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_number VARCHAR(50) NOT NULL UNIQUE,
    room_type VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    status VARCHAR(50) NOT NULL
);
```

**Lombok Annotations:**
- `@Getter` - Generates getter methods for all fields
- `@Setter` - Generates setter methods for all fields
- `@NoArgsConstructor` - Generates empty constructor
- `@AllArgsConstructor` - Generates constructor with all fields

#### **Repository Layer** 🗄️
Location: `src/main/java/com/lodging/project/repository/`

##### `RoomRepository.java`
**Purpose:** Database access interface for Room entity

**What it is:**
- A JPA Repository interface
- Extends `JpaRepository<Room, Long>`
- Automatically provides CRUD methods

**Auto-Generated Methods:**
```java
interface RoomRepository extends JpaRepository<Room, Long> {
    // Inherited methods (auto-implemented):
    
    // Create
    save(Room room)                    // Insert or update
    saveAll(List<Room> rooms)         // Batch save
    
    // Read
    findById(Long id)                 // Get room by ID
    findAll()                         // Get all rooms
    findAll(Pageable)                // Get rooms with pagination
    count()                           // Count total rooms
    existsById(Long id)               // Check if room exists
    
    // Update
    save(Room room)                   // Same as create
    
    // Delete
    deleteById(Long id)               // Delete by ID
    delete(Room room)                 // Delete room object
    deleteAll()                       // Delete all rooms
    deleteAll(List<Room> rooms)       // Batch delete
}
```

**Why Important:**
- No need to write SQL queries
- Spring Data JPA generates implementations automatically
- Provides type-safe database operations

#### **Service Layer** ⚙️
Location: `src/main/java/com/lodging/project/service/`

##### `RoomService.java`
**Purpose:** Business logic layer for room operations

**What it does:**
- Contains business logic methods
- Acts as middleman between Controller and Repository
- Handles validation and error handling

**Methods:**
```java
public List<Room> getAllRooms()           // Get all rooms
public Optional<Room> getRoomById(Long id) // Get room by ID
public Room saveRoom(Room room)           // Save room
public void deleteRoom(Long id)           // Delete room
public boolean roomExists(Long id)        // Check room exists
```

**Design Pattern:**
```
Controller calls Service
     ↓
Service calls Repository
     ↓
Repository executes Database query
     ↓
Returns result back through Service to Controller
```

**Why Important:**
- Separates business logic from web handling
- Easier to test
- Reusable logic

#### **Main Application Class** 🚀
Location: `src/main/java/com/lodging/project/LodgingApplication.java`

**Purpose:** Entry point of the application

**What it does:**
- Contains `main()` method to start Spring Boot
- Marked with `@SpringBootApplication` annotation
- Triggers component scanning and auto-configuration

**Code:**
```java
@SpringBootApplication
public class LodgingApplication {
    public static void main(String[] args) {
        SpringApplication.run(LodgingApplication.class, args);
    }
}
```

**Process:**
```
Run LodgingApplication.main()
     ↓
SpringApplication.run() starts Spring Boot
     ↓
Scans all packages under com.lodging.project
     ↓
Auto-configures Spring beans (@Controller, @Service, @Repository)
     ↓
Initializes database connection
     ↓
Starts embedded Tomcat server on port 8080
     ↓
Application ready to handle requests
```

---

### 3. HTML Templates

Location: `src/main/resources/templates/`

**Template Engine:** Thymeleaf (allows dynamic content with Java objects)

#### `index.html` 🏠
**Purpose:** Home/welcome page

**What it shows:**
- Welcome message
- System information
- Navigation links
- Quick action buttons

**Features:**
- Responsive design
- CSS styling via `th:href="@{/css/style.css}"`
- Thymeleaf namespace: `xmlns:th="http://www.thymeleaf.org"`

**Routes to:**
- Home (`/`) - via navigation
- Manage Rooms (`/rooms`) - via quick links

#### `rooms.html` 📋
**Purpose:** Display all rooms in table format

**What it shows:**
- Table of all rooms with columns:
  - Room ID
  - Room Number
  - Room Type
  - Price
  - Status (with color badges)
  - Actions (Edit/Delete buttons)
- Statistics section (total, available, occupied, maintenance)
- "No rooms" message if empty
- "Add New Room" button

**Thymeleaf Features:**
- `th:each="room : ${rooms}"` - Loop through rooms
- `th:text="${room.id}"` - Display room ID
- `th:classappend` - Conditional CSS classes for status badges
- `th:href="@{/rooms/edit/{id}(id=${room.id})}"` - Dynamic URLs
- `th:if="${rooms.isEmpty()}"` - Conditional rendering

#### `add-room.html` ➕
**Purpose:** Form to create new room

**Form Fields:**
- Room Number (text input)
- Room Type (dropdown select)
- Price (number input)
- Status (dropdown select)

**Thymeleaf Features:**
- `th:object="${room}"` - Bind form to Room object
- `th:field="*{roomNumber}"` - Two-way binding
- `method="POST"` - Submit form data
- `action="@{/rooms/save}"` - Form submission URL

**Form Submission:**
```
User fills form → Clicks "Save Room"
     ↓
POST /rooms/save with form data
     ↓
RoomController.saveRoom() receives Room object
     ↓
RoomService.saveRoom() saves to database
     ↓
Redirects to /rooms
     ↓
Lists all rooms including newly added one
```

#### `edit-room.html` ✏️
**Purpose:** Form to edit existing room

**Same as add-room.html but:**
- Pre-fills existing room data
- Has "Update Room" button instead of "Save"
- Has "Delete Room" button
- Room ID hidden in form (not modified)

**URL Parameter:**
- `/rooms/edit/{id}` where `{id}` is room ID
- Example: `/rooms/edit/1`

---

### 4. Static Files

Location: `src/main/resources/static/`

#### `css/style.css` 🎨
**Purpose:** All styling for the application

**Contains:**
- Global styles (fonts, colors, spacing)
- Navbar styling
- Form styling
- Table styling
- Button styling
- Responsive design (mobile-friendly)
- Status badge colors

**Key Classes:**
- `.container` - Main content wrapper
- `.navbar` - Navigation bar
- `.btn` - Button styling (variations: btn-primary, btn-success, btn-delete)
- `.table` - Table styling
- `.form` - Form styling
- `.status-badge` - Status color indicators

**Responsive Breakpoints:**
- Desktop: 1200px+
- Tablet: 768px - 1199px
- Mobile: < 768px

---

### 5. Database Files

#### `database-setup.sql` 💾
**Purpose:** Optional SQL script to set up database

**Contains:**
- Create database: `CREATE DATABASE lodging_db;`
- Create table: `CREATE TABLE rooms (...)`
- Sample data: `INSERT INTO rooms VALUES (...)`
- Useful queries

**When to use:**
- Manual database setup if auto-creation fails
- Reference for table structure
- Sample data for testing

---

### 6. Documentation Files

#### `README.md` 📖
**Purpose:** Project overview and documentation

**Contains:**
- Project description
- Features list
- Technology stack
- Setup instructions
- Code explanations
- Troubleshooting guide
- GitHub push steps

#### `SETUP.md` 🚀
**Purpose:** Detailed step-by-step setup guide

**Contains:**
- Prerequisites
- IntelliJ setup
- MySQL setup
- Gradle build instructions
- Application testing steps
- GitHub initialization
- Comprehensive troubleshooting

#### `PROJECT_STRUCTURE.md` 📂
**Purpose:** This file - explains every file in the project

---

### 7. Git Configuration

#### `.gitignore` 🔐
**Purpose:** Specifies which files Git should ignore

**Ignores:**
- Build artifacts (`build/`, `*.jar`)
- IDE files (`.idea/`, `*.iml`)
- Gradle cache (`.gradle/`)
- OS files (`.DS_Store`, `Thumbs.db`)
- Logs and temporary files
- Environment-specific files

**Why Important:**
- Prevents committing unnecessary files
- Keeps repository clean and small
- Avoids sharing sensitive information

---

## 📊 Data Flow Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                    WEB BROWSER                              │
│                 http://localhost:8080                       │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                    SPRING WEB SERVER                        │
│                    (Port 8080)                              │
│                  DispatcherServlet                          │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│              CONTROLLER LAYER (Web Handler)                 │
│  • HomeController                                           │
│  • RoomController (@RequestMapping("/rooms"))              │
│    - getAll(), add(), save(), edit(), delete()             │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│              SERVICE LAYER (Business Logic)                 │
│  • RoomService                                              │
│    - getAllRooms()                                          │
│    - getRoomById()                                          │
│    - saveRoom()                                             │
│    - deleteRoom()                                           │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│            REPOSITORY LAYER (Data Access)                   │
│  • RoomRepository extends JpaRepository<Room, Long>         │
│    - findAll(), findById(), save(), delete()               │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│          ENTITY/MODEL LAYER (Data Mapping)                  │
│  • Room.java                                                │
│    @Entity → Maps to 'rooms' table                         │
│    Fields: id, roomNumber, roomType, price, status         │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                  MYSQL DATABASE                             │
│              Database: lodging_db                           │
│              Table: rooms                                   │
│    (Stores actual room data persistently)                  │
└─────────────────────────────────────────────────────────────┘
```

---

## 📈 CRUD Operation Flow

### Create (Add Room)
```
User fills form (index.html/rooms.html)
         ↓
POST /rooms/save
         ↓
RoomController.saveRoom(Room room)
         ↓
RoomService.saveRoom(room)
         ↓
RoomRepository.save(room) → Hibernate generates INSERT SQL
         ↓
MySQL executes: INSERT INTO rooms VALUES (...)
         ↓
Return: Room with generated ID
         ↓
Redirect: /rooms (show updated list)
```

### Read (View Rooms)
```
GET /rooms or GET /rooms/edit/{id}
         ↓
RoomController.getAllRooms() or getRoomById(id)
         ↓
RoomService.getAllRooms() or getRoomById(id)
         ↓
RoomRepository.findAll() or findById(id)
         ↓
Hibernate generates: SELECT * FROM rooms...
         ↓
MySQL returns room data
         ↓
Return: List<Room> or Optional<Room>
         ↓
Pass to template via Model
         ↓
Thymeleaf renders: rooms.html or edit-room.html
```

### Update (Edit Room)
```
GET /rooms/edit/{id}
         ↓
RoomController.editRoomForm(id, Model)
         ↓
Get room from RoomService.getRoomById(id)
         ↓
Load edit-room.html with room data
         ↓
User edits form
         ↓
POST /rooms/save (same as Create)
         ↓
Save method detects Room has ID
         ↓
Hibernate generates: UPDATE rooms SET ... WHERE id = ?
         ↓
Room updated in database
```

### Delete (Remove Room)
```
User clicks delete button
         ↓
GET /rooms/delete/{id}
         ↓
RoomController.deleteRoom(id)
         ↓
RoomService.deleteRoom(id)
         ↓
RoomRepository.deleteById(id)
         ↓
Hibernate generates: DELETE FROM rooms WHERE id = ?
         ↓
Room deleted from database
         ↓
Redirect: /rooms (show updated list)
```

---

## 🔗 File Relationships

```
                LodgingApplication.java
                        ↓
                    (starts)
                        ↓
            ┌───────────────────────┐
            ↓                       ↓
    HomeController          RoomController
            ↓                       ↓
        index.html            RoomService
                            ↙   ↓   ↘
                        (uses)
                        ↓
                RoomRepository
                        ↓
                    Room.java
                        ↓
            MySQL Database (lodging_db)
                    rooms table
```

---

## ✅ File Checklist

Before running the project, verify all files exist:

```
☐ build.gradle
☐ settings.gradle
☐ .gitignore
☐ README.md
☐ SETUP.md
☐ PROJECT_STRUCTURE.md
☐ database-setup.sql
☐ src/main/java/com/lodging/project/LodgingApplication.java
☐ src/main/java/com/lodging/project/controller/HomeController.java
☐ src/main/java/com/lodging/project/controller/RoomController.java
☐ src/main/java/com/lodging/project/entity/Room.java
☐ src/main/java/com/lodging/project/repository/RoomRepository.java
☐ src/main/java/com/lodging/project/service/RoomService.java
☐ src/main/resources/application.properties
☐ src/main/resources/templates/index.html
☐ src/main/resources/templates/rooms.html
☐ src/main/resources/templates/add-room.html
☐ src/main/resources/templates/edit-room.html
☐ src/main/resources/static/css/style.css
```

Total: 20 files

---

This structure follows Spring Boot best practices and makes the code:
- **Organized** - Each layer has clear responsibility
- **Maintainable** - Easy to find and modify code
- **Testable** - Layers can be tested independently
- **Scalable** - Easy to add new features

Good luck with your project! 🚀
