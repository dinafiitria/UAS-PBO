# Student Master - Java Swing, MySQL, JasperReports

Project UAS PBO: CRUD Student Master, UI background/image, report range Student ID, logo header, page number/page count, dan export PDF.

## Requirements
- JDK 17
- Maven
- MySQL Server

## Setup database
Jalankan `database/student_master.sql` di MySQL Workbench.

Default koneksi: root tanpa password. Jika memakai password di PowerShell:
```powershell
$env:DB_USER="root"
$env:DB_PASSWORD="password_mysql_kamu"
$env:DB_URL="jdbc:mysql://localhost:3306/student_master?useSSL=false&serverTimezone=Asia/Jakarta"
```

## Run di VS Code
Install Extension Pack for Java, buka folder project, lalu:
```powershell
mvn clean compile
mvn exec:java
```

## Captures
Setelah aplikasi berjalan, simpan screenshot asli:
- `captures/01-create.png`
- `captures/02-read.png`
- `captures/03-update.png`
- `captures/04-delete.png`
- `captures/05-report.png`

## GitHub
```powershell
git init
git add .
git commit -m "Initial Student Master CRUD and Jasper Report"
git branch -M main
git remote add origin URL_REPOSITORY_KAMU
git push -u origin main
```
