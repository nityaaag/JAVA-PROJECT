# 🚀 Complete Setup Guide - Lodgings & Restaurant Management System

A step-by-step guide to set up and run the Lodgings & Restaurant Management System project.

## 📋 Table of Contents
1. [Prerequisites](#prerequisites)
2. [IntelliJ IDEA Project Setup](#intellij-idea-project-setup)
3. [MySQL Database Setup](#mysql-database-setup)
4. [Project Configuration](#project-configuration)
5. [Building with Gradle](#building-with-gradle)
6. [Running the Application](#running-the-application)
7. [Testing the Application](#testing-the-application)
8. [GitHub Setup](#github-setup)
9. [Troubleshooting](#troubleshooting)

---

## 📦 Prerequisites

Ensure you have the following installed on your system:

### 1. Java 21
```bash
# Check Java version
java -version

# Should output: java version "21.x.x" or higher
```

**Download Java 21:**
- Visit: https://www.oracle.com/java/technologies/downloads/#java21
- Download JDK 21 for your OS
- Install and set JAVA_HOME in environment variables

### 2. MySQL 8.0 or Higher
```bash
# Check MySQL version
mysql --version

# Should output: mysql Ver 8.0.x or higher
```

**Download MySQL:**
- Visit: https://dev.mysql.com/downloads/mysql/
- Download and install MySQL Community Server
- During installation, set root password

### 3. IntelliJ IDEA
- Download from: https://www.jetbrains.com/idea/
- Community Edition is free (sufficient for this project)
- Install and launch

### 4. Git
```bash
# Check Git version
git --version

# Should output: git version 2.x.x or higher
```

**Download Git:**
- Visit: https://git-scm.com/downloads
- Download and install for your OS

---

## 🏗️ IntelliJ IDEA Project Setup

### Step 1: Open Project in IntelliJ IDEA

1. **Launch IntelliJ IDEA**
2. **Open the project folder:**
   - Click `File` → `Open`
   - Navigate to `c:\JAVA PROJECT`
   - Click `OK`

3. **Trust the Project:**
   - When prompted, click `Trust Project`
   - IntelliJ will recognize this as a Gradle project

### Step 2: Verify Project Structure in IntelliJ

After opening, you should see:

```
JAVA PROJECT/
├── gradle/ (Gradle wrapper)
├── src/
│   ├── main/
│   │   ├── java/com/lodging/project/
│   │   │   ├── controller/
│   │   │   ├── entity/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── LodgingApplication.java
│   │   └── resources/
│   │       ├── templates/
│   │       ├── static/css/
│   │       └── application.properties
│   └── test/
├── build.gradle
├── settings.gradle
├── .gitignore
└── README.md
```

### Step 3: Configure Gradle in IntelliJ

1. Go to `File` → `Settings` → `Build, Execution, Deployment` → `Gradle`
2. Set **Gradle JVM** to Java 21
3. Click `Apply` → `OK`

---

## 🗄️ MySQL Database Setup

### Step 1: Start MySQL Server

**On Windows:**
```bash
# MySQL should auto-start if installed as a service
# Check if running: Services > MySQL80 (or your version)

# Or start from Command Prompt:
net start MySQL80
```

**On Mac:**
```bash
# If installed via Homebrew
brew services start mysql
```

**On Linux:**
```bash
sudo systemctl start mysql
```

### Step 2: Create Database

Open **MySQL Command Line Client** or use any MySQL tool:

**Option A: Using MySQL Command Line**
```bash
# Open Command Prompt/Terminal
mysql -u root -p

# Enter your root password when prompted
```

**Option B: Using MySQL Workbench** (GUI Tool)
1. Open MySQL Workbench
2. Connect to localhost

### Step 3: Execute Database Setup Script

Copy and paste this into MySQL:

```sql
-- Create database
CREATE DATABASE IF NOT EXISTS lodging_db;

-- Use the database
USE lodging_db;

-- Verify
SHOW DATABASES;
SHOW TABLES;
```

**Or run the provided script:**
```bash
mysql -u root -p lodging_db < database-setup.sql
```

---

## ⚙️ Project Configuration

### Step 1: Configure application.properties

Edit: `src/main/resources/application.properties`

Find and update these lines:

```properties
# MySQL Connection (Change password if needed)
spring.datasource.url=jdbc:mysql://localhost:3306/lodging_db
spring.datasource.username=root
spring.datasource.password=your_password
```

Replace `your_password` with your MySQL root password.

### Example Configuration:
```properties
# If MySQL password is empty
spring.datasource.password=

# If MySQL password is "admin123"
spring.datasource.password=admin123

# If using different MySQL port (not default 3306)
spring.datasource.url=jdbc:mysql://localhost:3307/lodging_db
```

### Step 2: Verify All Configuration Files

Check these files exist:
- ✅ `build.gradle` - Project dependencies
- ✅ `settings.gradle` - Project settings
- ✅ `application.properties` - Database and Spring config
- ✅ All Java classes in `src/main/java`
- ✅ All HTML templates in `src/main/resources/templates`
- ✅ `style.css` in `src/main/resources/static/css`

---

## 🔨 Building with Gradle

### Step 1: Open Terminal in IntelliJ

1. Click `View` → `Tool Windows` → `Terminal` (or press `Alt + F12`)
2. Terminal should open at project root: `c:\JAVA PROJECT>`

### Step 2: Build the Project

**Option A: Using Gradle Wrapper (Recommended)**
```bash
# On Windows Command Prompt
gradlew build

# On PowerShell (Windows)
.\gradlew build

# On Mac/Linux Terminal
./gradlew build
```

**Option B: Using Gradle directly** (if Gradle is in PATH)
```bash
gradle build
```

### Step 3: Understand Build Output

When build succeeds, you'll see:
```
BUILD SUCCESSFUL in 2m 35s
```

This means:
- ✅ All dependencies downloaded
- ✅ Code compiled successfully
- ✅ All tests passed (if any)

### Step 4: Verify Build

After successful build, check:
```
build/libs/lodging-management-system-1.0.0.jar
```

This is your packaged application.

---

## 🚀 Running the Application

### Option 1: Run from IntelliJ (Easiest for Development)

1. **Find and open:** `LodgingApplication.java`
   - Located at: `src/main/java/com/lodging/project/LodgingApplication.java`

2. **Run the application:**
   - Click the green ▶ (Play) button next to the class name
   - Or right-click → `Run 'LodgingApplication.main()'`
   - Or press `Shift + F10`

3. **Watch the Console:**
   - Application should print:
   ```
   ===================================
   🏨 Lodgings & Restaurant Management System
   ===================================
   Server running on: http://localhost:8080
   ===================================
   ```

### Option 2: Run from Terminal

```bash
# Using Gradle
gradlew bootRun

# Or PowerShell
.\gradlew bootRun

# Or Mac/Linux
./gradlew bootRun
```

### Option 3: Run Packaged JAR (Production)

```bash
# First, build the JAR
gradlew build

# Then run it
java -jar build/libs/lodging-management-system-1.0.0.jar
```

### Step 2: Access the Application

Open your browser and go to:
```
http://localhost:8080
```

You should see:
- 🏨 Welcome page with navigation
- Buttons to manage rooms
- Links to add/view rooms

### Step 3: Stop the Application

**In IntelliJ:**
- Click the red ⏹ (Stop) button in the console

**In Terminal:**
- Press `Ctrl + C`

---

## ✅ Testing the Application

### Test 1: Home Page
1. Navigate to: `http://localhost:8080/`
2. **Expected:** Welcome page with system information

### Test 2: View Rooms (Initial)
1. Click "View All Rooms" or go to: `http://localhost:8080/rooms`
2. **Expected:** "No rooms found" message (database is empty)

### Test 3: Add a Room
1. Click "Add New Room"
2. **Fill the form:**
   - Room Number: `101`
   - Room Type: `Deluxe Room`
   - Price: `5000`
   - Status: `Available`
3. Click "Save Room"
4. **Expected:** Redirected to rooms list with new room visible

### Test 4: View Added Room
1. Go to Rooms page: `http://localhost:8080/rooms`
2. **Expected:** Table showing:
   | ID | Room Number | Room Type | Price | Status | Actions |
   | 1 | 101 | Deluxe Room | ₹5,000 | Available | Edit Delete |

### Test 5: Edit Room
1. Click "Edit" button for room 101
2. **Change:**
   - Price: `6000`
   - Status: `Occupied`
3. Click "Update Room"
4. **Expected:** Room updated in list

### Test 6: Delete Room
1. Click "Delete" button
2. Click "OK" on confirmation dialog
3. **Expected:** Room removed from list

### Test 7: Add Multiple Rooms
Repeat Test 3 with different data:
```
Room 102 | Simple Room | 3000 | Available
Room 201 | Luxury Room | 8000 | Available
Room 202 | Suite Room | 12000 | Under Maintenance
```

### Test 8: View Statistics
After adding multiple rooms, go to rooms page:
1. Check statistics at bottom
2. **Expected:** Shows count by status (Available, Occupied, Under Maintenance)

---

## 📦 GitHub Setup

### Step 1: Initialize Git Repository

```bash
# Navigate to project folder
cd c:\JAVA PROJECT

# Initialize git
git init

# View git status
git status
```

### Step 2: Stage and Commit Files

```bash
# Stage all files
git add .

# Verify staged files
git status

# Create first commit
git commit -m "Initial project setup"

# View commit
git log --oneline
```

### Step 3: Create GitHub Repository

1. Go to https://github.com/new
2. **Fill in:**
   - Repository name: `lodging-management-system`
   - Description: "Hotel room management system with Spring Boot"
   - Visibility: Public (for learning) or Private
   - Initialize: NO (don't initialize with README)
3. Click "Create repository"

### Step 4: Push to GitHub

```bash
# Add remote repository
git remote add origin https://github.com/YOUR_USERNAME/lodging-management-system.git

# Rename branch to main
git branch -M main

# Push to GitHub
git push -u origin main

# Verify push
git remote -v
```

### Step 5: Make More Commits (as you work)

```bash
# After making changes
git status

# Stage changes
git add .

# Commit
git commit -m "Added room CRUD functionality"

# Push
git push origin main
```

### Useful Git Commands

```bash
# View all commits
git log --oneline --all

# Create feature branch
git checkout -b feature/restaurant-module

# Switch back to main
git checkout main

# Merge feature branch
git merge feature/restaurant-module

# View all branches
git branch -a

# Delete local branch
git branch -d feature/restaurant-module

# Push specific branch
git push origin feature/restaurant-module

# Pull latest changes
git pull origin main
```

---

## 🐛 Troubleshooting

### Problem 1: Port 8080 Already in Use

**Error Message:**
```
Failed to start application on port 8080: Address already in use
```

**Solution:**
```bash
# Option A: Kill process using port 8080 (Windows)
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Option B: Use different port
# Edit application.properties:
server.port=8081

# Then access: http://localhost:8081
```

### Problem 2: Cannot Connect to MySQL

**Error Message:**
```
Communications link failure
Unable to connect to host localhost:3306
```

**Solutions:**
1. **Check MySQL is running:**
   - Windows: Services → MySQL80 should be running
   - Mac: `brew services list`
   - Linux: `systemctl status mysql`

2. **Verify credentials in `application.properties`:**
   ```properties
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

3. **Check database exists:**
   ```bash
   mysql -u root -p
   SHOW DATABASES;
   ```

4. **Recreate database:**
   ```sql
   DROP DATABASE lodging_db;
   CREATE DATABASE lodging_db;
   ```

### Problem 3: Gradle Build Fails

**Error Message:**
```
BUILD FAILED
```

**Solutions:**
```bash
# Clear Gradle cache
gradlew clean

# Delete build folder
rmdir /s build

# Rebuild
gradlew build

# Check internet connection (downloads dependencies)
```

### Problem 4: Java Version Mismatch

**Error Message:**
```
Unable to parse class file: LodgingApplication.java
Compilation error: requires Java 21 or higher
```

**Solution:**
```bash
# Check Java version
java -version

# Install Java 21 if needed:
# https://www.oracle.com/java/technologies/downloads/#java21

# Set JAVA_HOME environment variable to Java 21 path
```

### Problem 5: Templates Not Found

**Error Message:**
```
Cannot resolve template location: classpath:/templates/index.html
```

**Solution:**
- Check folder structure: `src/main/resources/templates/`
- Verify file names (lowercase): `index.html`, `rooms.html`, etc.
- Restart IntelliJ: File → Invalidate Caches → Restart

### Problem 6: Static CSS Not Loading

**Issue:** Styled page appears unstyled

**Solution:**
1. Check file location: `src/main/resources/static/css/style.css`
2. Verify link in HTML: `<link rel="stylesheet" th:href="@{/css/style.css}">`
3. Clear browser cache: `Ctrl + Shift + Delete`
4. Hard refresh: `Ctrl + F5`

### Problem 7: Room Table Doesn't Exist

**Error Message:**
```
Table 'lodging_db.rooms' doesn't exist
```

**Solution:**
1. Verify `application.properties`:
   ```properties
   spring.jpa.hibernate.ddl-auto=update
   ```

2. Restart application - table will be created automatically

3. Or create manually:
   ```sql
   USE lodging_db;
   CREATE TABLE rooms (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       room_number VARCHAR(50) NOT NULL UNIQUE,
       room_type VARCHAR(100) NOT NULL,
       price DOUBLE NOT NULL,
       status VARCHAR(50) NOT NULL
   );
   ```

### Problem 8: Application Runs but Browser Shows Error

**Error:** "Whitelabel Error Page"

**Solution:**
1. Check browser console for errors: `F12`
2. Check IntelliJ console for exceptions
3. Verify URL is correct: `http://localhost:8080`
4. Check templates exist in `src/main/resources/templates/`

---

## 📚 Additional Resources

### Documentation
- **Spring Boot:** https://spring.io/projects/spring-boot
- **Spring Data JPA:** https://spring.io/projects/spring-data-jpa
- **Thymeleaf:** https://www.thymeleaf.org/
- **MySQL:** https://dev.mysql.com/doc/
- **Gradle:** https://gradle.org/guides/

### Tutorials
- Spring Boot Tutorial: https://www.baeldung.com/spring-boot
- JPA/Hibernate: https://www.baeldung.com/hibernate
- Thymeleaf Tutorial: https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html

### Tools
- **MySQL Workbench:** https://dev.mysql.com/products/workbench/
- **DBeaver:** https://dbeaver.io/ (free database tool)
- **Postman:** https://www.postman.com/ (API testing)
- **GitHub Desktop:** https://desktop.github.com/ (Git GUI)

---

## ✅ Verification Checklist

Before submitting/deploying, verify:

- [ ] Java 21 installed
- [ ] MySQL server running
- [ ] Database `lodging_db` created
- [ ] `application.properties` configured with correct credentials
- [ ] All Java files compile without errors
- [ ] All HTML templates exist in `src/main/resources/templates/`
- [ ] CSS file exists at `src/main/resources/static/css/style.css`
- [ ] Application starts successfully: `http://localhost:8080`
- [ ] Can view home page
- [ ] Can add room
- [ ] Can edit room
- [ ] Can delete room
- [ ] Can view rooms list
- [ ] Git repository initialized
- [ ] Files committed to GitHub

---

## 🎓 Project Submission Tips

1. **Code Quality:**
   - Keep code simple and readable
   - Add comments explaining logic
   - Follow Java naming conventions

2. **Testing:**
   - Test all CRUD operations
   - Test with different inputs
   - Document any bugs found

3. **Documentation:**
   - Keep README.md updated
   - Document any custom changes
   - Include setup instructions

4. **Git Best Practices:**
   - Commit regularly with meaningful messages
   - One feature per commit
   - Push to GitHub regularly

5. **For Viva/Presentation:**
   - Be able to explain the project structure
   - Know the purpose of each file
   - Be ready to demo the application
   - Understand the technology stack

---

**Happy Coding! Good luck with your project! 🚀**

If you have any questions, refer to the README.md file or contact your instructor.
