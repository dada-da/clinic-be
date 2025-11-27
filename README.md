# Clinic Management System - Backend API

## 📋 Mô Tả Dự Án

Hệ thống quản lý phòng khám (Clinic Management System) là một REST API backend được xây dựng bằng Java, cung cấp các chức năng quản lý bệnh nhân, bác sĩ, lịch hẹn và hồ sơ y tế. Hệ thống sử dụng kiến trúc 3 lớp (Controller-Service-Repository) và database migration với Flyway.

## 🛠 Công Nghệ Sử Dụng

### Core Technologies
- **Java 21** - Ngôn ngữ lập trình chính
- **Maven** - Công cụ quản lý dependencies và build project

### Frameworks & Libraries
- **Javalin 5.6.3** - Lightweight web framework để xây dựng REST API
- **MySQL Connector/J 9.1.0** - JDBC driver cho MySQL database
- **Flyway 10.0.0** - Database migration tool
- **Jackson 2.17.2** - JSON serialization/deserialization
- **SLF4J Simple 2.0.9** - Logging framework
- **Dotenv Java 3.0.0** - Quản lý biến môi trường từ file .env

### Database
- **MySQL** - Hệ quản trị cơ sở dữ liệu quan hệ

## 📁 Cấu Trúc Dự Án

```
clinic-be/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── clinic/
│       │           ├── config/              # Cấu hình
│       │           │   ├── DatabaseConnection.java
│       │           │   ├── EnvConfig.java   # Quản lý biến môi trường
│       │           │   └── FlywayMigration.java
│       │           ├── controller/          # REST API Controllers
│       │           │   ├── AppointmentController.java
│       │           │   ├── DoctorController.java
│       │           │   ├── MedicalRecordController.java
│       │           │   └── PatientController.java
│       │           ├── model/               # Entity Models
│       │           │   ├── Appointment.java
│       │           │   ├── Doctor.java
│       │           │   ├── MedicalRecord.java
│       │           │   └── Patient.java
│       │           ├── repository/          # Data Access Layer
│       │           │   ├── AppointmentRepository.java
│       │           │   ├── DoctorRepository.java
│       │           │   ├── MedicalRecordRepository.java
│       │           │   └── PatientRepository.java
│       │           ├── service/             # Business Logic Layer
│       │           │   ├── AppointmentService.java
│       │           │   ├── DoctorService.java
│       │           │   ├── MedicalRecordService.java
│       │           │   └── PatientService.java
│       │           └── Main.java            # Entry point
│       └── resources/
│           ├── db/
│           │   └── migration/               # Database migration scripts
│           │       ├── V1__init_schema.sql  # Tạo schema
│           │       └── V2__insert_sample_data.sql  # Dữ liệu mẫu
│           └── simplelogger.properties      # Logging configuration
├── pom.xml                                  # Maven configuration
├── flyway.conf.example                      # Flyway config example
└── README.md                                # File này

```

### Kiến Trúc
- **Controller Layer**: Xử lý HTTP requests/responses, validation đầu vào
- **Service Layer**: Chứa business logic, xử lý nghiệp vụ
- **Repository Layer**: Tương tác trực tiếp với database
- **Model Layer**: Định nghĩa các entity/POJO

## 📦 Yêu Cầu Hệ Thống

### Phần Mềm Cần Cài Đặt

1. **Java Development Kit (JDK) 21**
   - Tải về từ: https://www.oracle.com/java/technologies/downloads/#java21
   - Hoặc sử dụng OpenJDK: https://adoptium.net/
   - Kiểm tra cài đặt: `java -version`

2. **Apache Maven 3.6+**
   - Tải về từ: https://maven.apache.org/download.cgi
   - Hướng dẫn cài đặt: https://maven.apache.org/install.html
   - Kiểm tra cài đặt: `mvn -version`

3. **MySQL Server 8.0+**
   - Tải về từ: https://dev.mysql.com/downloads/mysql/
   - Hoặc cài qua package manager:
     - macOS: `brew install mysql`
     - Ubuntu/Debian: `sudo apt-get install mysql-server`
     - Windows: Download installer từ MySQL website
   - Kiểm tra cài đặt: `mysql --version`

### IDE Đề Xuất

- **IntelliJ IDEA** (Recommended)
  - Download: https://www.jetbrains.com/idea/download/
  - Có tích hợp sẵn Maven và hỗ trợ tốt Java 21

- **Eclipse IDE**
  - Download: https://www.eclipse.org/downloads/
  - Cần cài plugin Maven

- **VS Code**
  - Download: https://code.visualstudio.com/
  - Cần cài extensions: Java Extension Pack, Maven for Java

## 🚀 Hướng Dẫn Cài Đặt và Chạy Project

### Bước 1: Clone Repository

```bash
git clone <repository-url>
cd clinic-be
```

### Bước 2: Cài Đặt MySQL

#### 2.1. Cài đặt MySQL Server

**macOS (Homebrew):**
```bash
brew install mysql
brew services start mysql
```

**Ubuntu/Debian:**
```bash
sudo apt-get update
sudo apt-get install mysql-server
sudo systemctl start mysql
sudo systemctl enable mysql
```

**Windows:**
- Tải MySQL Installer từ https://dev.mysql.com/downloads/installer/
- Chạy installer và làm theo hướng dẫn

#### 2.2. Tạo Database

Đăng nhập vào MySQL:
```bash
mysql -u root -p
```

Tạo database mới:
```sql
CREATE DATABASE clinic_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Tạo user và cấp quyền (tùy chọn, có thể dùng root):
```sql
CREATE USER 'clinic_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON clinic_db.* TO 'clinic_user'@'localhost';
FLUSH PRIVILEGES;
```

Thoát MySQL:
```sql
EXIT;
```

### Bước 3: Cấu Hình Environment Variables

Tạo file `.env` ở thư mục gốc của project (cùng cấp với `pom.xml`):

```bash
touch .env
```

Sao chép nội dung từ `flyway.conf.example` và tạo file `.env` với nội dung sau:

```env
# Database Configuration
DB_HOST=localhost
DB_PORT=3306
DB_NAME=clinic_db
DB_USER=root
DB_PASSWORD=your_mysql_password

# Application Configuration
APP_PORT=7000
APP_ENV=development
```

**Lưu ý:** 
- Thay `your_mysql_password` bằng mật khẩu MySQL của bạn
- Nếu bạn tạo user riêng, thay `DB_USER` và `DB_PASSWORD` tương ứng
- Đảm bảo file `.env` đã được thêm vào `.gitignore` để không commit lên git

### Bước 4: Build Project với Maven

```bash
# Download dependencies và compile project
mvn clean compile

# Hoặc build toàn bộ (compile + test + package)
mvn clean package
```

### Bước 5: Chạy Database Migrations

Flyway sẽ tự động chạy migrations khi khởi động ứng dụng. Tuy nhiên, bạn cũng có thể chạy thủ công:

```bash
# Chạy migrations với Flyway Maven plugin
mvn flyway:migrate -Dflyway.configFiles=flyway.conf
```

Hoặc tạo file `flyway.conf` từ `flyway.conf.example`:

```bash
cp flyway.conf.example flyway.conf
```

Sau đó chỉnh sửa `flyway.conf` với thông tin database của bạn.

### Bước 6: Chạy Application

**Cách 1: Chạy trực tiếp với Maven**
```bash
mvn exec:java -Dexec.mainClass="com.clinic.Main"
```

**Cách 2: Chạy từ JAR file**
```bash
# Build JAR file
mvn clean package

# Chạy JAR
java -jar target/clinic-be-1.0-SNAPSHOT.jar
```

**Cách 3: Chạy từ IDE (IntelliJ IDEA)**
1. Mở project trong IntelliJ IDEA
2. File → Open → Chọn thư mục `clinic-be`
3. IDE sẽ tự động download Maven dependencies
4. Mở file `src/main/java/com/clinic/Main.java`
5. Click chuột phải → Run 'Main.main()'

### Bước 7: Kiểm Tra Application

Sau khi chạy thành công, bạn sẽ thấy log tương tự:

```
=== Clinic REST API Starting ===

1. Loading Configuration...
=== Environment Configuration ===
DB_HOST: localhost
DB_PORT: 3306
DB_NAME: clinic_db
...
2. Testing Database Connection...
   ✓ Database connection successful

3. Running Database Migrations...
✓ Successfully applied 2 migration(s)

4. Starting REST API Server...
✓ REST API Server started successfully
   URL: http://localhost:7000
   Health Check: http://localhost:7000/health
```

Kiểm tra health endpoint:
```bash
curl http://localhost:7000/health
```

Hoặc mở trình duyệt: http://localhost:7000/health

## 📡 API Endpoints

### Health Check
- `GET /` - Trang chủ
- `GET /health` - Health check endpoint

### Patient Endpoints
- `GET /api/patients` - Lấy danh sách tất cả bệnh nhân
- `GET /api/patients/{id}` - Lấy thông tin bệnh nhân theo ID
- `GET /api/patients/social/{socialId}` - Lấy thông tin bệnh nhân theo số CMND/CCCD
- `GET /api/patients/search?q={query}` - Tìm kiếm bệnh nhân
- `POST /api/patients` - Tạo bệnh nhân mới
- `PUT /api/patients/{id}` - Cập nhật thông tin bệnh nhân
- `DELETE /api/patients/{id}` - Xóa bệnh nhân

### Doctor Endpoints
- `GET /api/doctors` - Lấy danh sách tất cả bác sĩ
- `GET /api/doctors/{id}` - Lấy thông tin bác sĩ theo ID
- `GET /api/doctors/specialty/{specialty}` - Lấy danh sách bác sĩ theo chuyên khoa

### Appointment Endpoints
- `GET /api/appointments` - Lấy danh sách tất cả lịch hẹn
- `GET /api/appointments/{id}` - Lấy thông tin lịch hẹn theo ID
- `GET /api/appointments/today` - Lấy lịch hẹn hôm nay
- `GET /api/appointments/date/{date}` - Lấy lịch hẹn theo ngày (format: yyyy-MM-dd)
- `POST /api/appointments` - Tạo lịch hẹn mới
- `PUT /api/appointments/{id}/complete` - Đánh dấu lịch hẹn đã hoàn thành

### Medical Record Endpoints
- `GET /api/medical-records/appointment/{appointmentId}` - Lấy hồ sơ y tế theo ID lịch hẹn
- `POST /api/medical-records` - Tạo hồ sơ y tế mới

## 📝 Ví Dụ Sử Dụng API

### Tạo bệnh nhân mới
```bash
curl -X POST http://localhost:7000/api/patients \
  -H "Content-Type: application/json" \
  -d '{
    "socialId": "001234567890",
    "fullName": "Nguyễn Văn A",
    "dob": "1990-01-15",
    "gender": "MALE",
    "address": "123 Đường ABC, Quận 1, TP.HCM",
    "phone": "0901234567",
    "email": "nguyenvana@example.com"
  }'
```

### Lấy danh sách bệnh nhân
```bash
curl http://localhost:7000/api/patients
```

### Tạo lịch hẹn
```bash
curl -X POST http://localhost:7000/api/appointments \
  -H "Content-Type: application/json" \
  -d '{
    "patientId": 1,
    "doctorId": 1,
    "dateTime": "2024-12-20T10:00:00",
    "reason": "Khám định kỳ"
  }'
```

## 🔧 Troubleshooting

### Lỗi kết nối database
- Kiểm tra MySQL đã chạy chưa: `mysql -u root -p`
- Kiểm tra thông tin trong file `.env` đúng chưa
- Kiểm tra database đã được tạo chưa: `SHOW DATABASES;`

### Lỗi port đã được sử dụng
- Thay đổi `APP_PORT` trong file `.env` sang port khác (ví dụ: 7001)
- Hoặc tìm và kill process đang dùng port 7000:
  - macOS/Linux: `lsof -ti:7000 | xargs kill -9`
  - Windows: `netstat -ano | findstr :7000` sau đó `taskkill /PID <pid> /F`

### Lỗi migration
- Kiểm tra file migration trong `src/main/resources/db/migration/`
- Xóa bảng `flyway_schema_history` nếu cần chạy lại từ đầu:
  ```sql
  DROP TABLE IF EXISTS flyway_schema_history;
  ```

### Lỗi Java version
- Đảm bảo đã cài JDK 21: `java -version`
- Kiểm tra JAVA_HOME đã được set đúng chưa
