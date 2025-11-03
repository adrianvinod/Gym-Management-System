@echo off
echo Starting Gym Management System...
echo.

REM Change to the script's directory (where this batch file is located)
cd /d "%~dp0"

REM Verify we're in the right directory
if not exist "javafx-sdk\lib" (
    echo ERROR: javafx-sdk\lib not found!
    echo Please make sure you run this script from the GymManagementSystem directory.
    pause
    exit /b 1
)

if not exist "build\classes" (
    echo ERROR: build\classes not found!
    echo Please compile the project first.
    pause
    exit /b 1
)

REM Set JavaFX module path (absolute path)
set JAVAFX_PATH=%~dp0javafx-sdk\lib

REM Set classpath
set CLASSPATH=%~dp0build\classes;%~dp0dist\lib\*

echo Running from: %CD%
echo JavaFX Path: %JAVAFX_PATH%
echo.

REM Run the application
java --module-path "%JAVAFX_PATH%" --add-modules javafx.controls,javafx.fxml -cp "%CLASSPATH%" gymmanagementsystem.GymManagementSystem

if errorlevel 1 (
    echo.
    echo ERROR: Application failed to start!
    echo Please check:
    echo 1. Java is installed (java -version)
    echo 2. MySQL is running
    echo 3. You're in the correct directory
)

pause

