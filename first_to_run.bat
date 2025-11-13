@echo off
REM ========================================
REM 1. ضبط Java Home (JDK 21)
REM ========================================
set JAVA_HOME=C:\Program Files\Java\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%

REM ========================================
REM 2. ضبط Maven Path
REM ========================================
set MAVEN_HOME=C:\Users\Ahmed\Tools\Maven\apache-maven-3.9.11
set PATH=%MAVEN_HOME%\bin;%PATH%

REM ========================================
REM 3. انتقل لمجلد المشروع
REM ========================================
cd /d "D:\JAVACOURES\mahfza"

REM ========================================
REM 4. نظف المشروع و Package
REM ========================================
echo ========================================
echo Cleaning and packaging project...
echo ========================================
mvn clean package || (
    echo ===============================
    echo Maven build failed!
    echo ===============================
    pause
    exit /b 1
)

REM ========================================
REM 5. شغل الـ Jar الناتج مباشرة
REM ========================================
echo ========================================
echo Running Spring Boot jar...
echo ========================================
java -jar target\mahfza-0.0.1-SNAPSHOT.jar || (
    echo ===============================
    echo Failed to run jar!
    echo ===============================
    pause
    exit /b 1
)

REM ========================================
REM 6. افتح المتصفح على المشروع
REM ========================================
timeout /t 2
start "" "http://localhost:8080"

REM ========================================
REM 7. ابقى نافذة CMD مفتوحة بعد النهاية
REM ========================================
echo ========================================
echo Project finished. Press any key to exit.
echo ========================================
pause
