@echo off
REM === انتقل لمجلد المشروع مع اقتباس مزدوج ===
cd /d "D:\JAVACOURES\mahfza"

REM === شغل Spring Boot باستخدام Maven ===
start "" cmd /k "mvn spring-boot:run"

REM === فتح المتصفح بعد 5 ثواني ===
timeout /t 5
start "" "http://localhost:8080"

exit
