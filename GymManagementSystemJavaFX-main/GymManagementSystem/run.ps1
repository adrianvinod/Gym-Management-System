# PowerShell script to run Gym Management System
Write-Host "Starting Gym Management System..." -ForegroundColor Green
Write-Host ""

# Change to script directory
Set-Location $PSScriptRoot

# Set JavaFX module path
$javafxPath = "javafx-sdk\lib"

# Set classpath
$classpath = "build\classes;dist\lib\*"

# Run the application
java --module-path $javafxPath --add-modules javafx.controls,javafx.fxml -cp $classpath gymmanagementsystem.GymManagementSystem

