# Simple Book Store API Spring Boot

![Spring Boot Logo](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKMMePCiMt-xw9OHccOXx2Z3gXV0WI2XbH7eRNcq1SOuB9o1DGYhBe&usqp=CAE&s)

Proyek Simple Book Store API merupakan API yang bertujuan untuk menyimpan data buku dan author yang akan dijual.

## Fitur Utama

- **Manajemen Buku**: CMS Buku yang bertujuan untuk menambah, melihat, memperbarui, serta menghapus data buku.
- **Manajemen Pengarang**: CMS Pengarang yang bertujuan untuk menambah, melihat, memperbarui, serta menghapus data pengarang.
- **Buku API**: API untuk melihat semua buku dengan status aktif dan melihat detail buku.

## Teknologi yang Digunakan

- [Spring Boot](https://spring.io/projects/spring-boot): Framework utama untuk membangun aplikasi Java berbasis mikroservis.
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa): Pemetaan objek-relasional (ORM) untuk mengelola data di database.
- [Swagger](https://swagger.io/): Membuat dokumentasi API secara otomatis.
- [Lombok](https://projectlombok.org/): Meringkas boilerplate code untuk Java dan membuat kodenya lebih bersih.
- [PostgreSQL](https://www.postgresql.org/): Database untuk menyimpan data aplikasi.

## Setup Proyek

1. **Prasyarat**: Pastikan Anda telah menginstal:
   1. [Java LTS v17](https://www.oracle.com/java/technologies/downloads/#jdk17-windows).
   2. [Maven](https://maven.apache.org/install.html) : untuk maven opsional, boleh tidak diinstall.
2. **Clone Repositori**: `git clone https://github.com/fazztrack/simple-bookstore-api.git`
3. **Setup Database**: sesuaikan credential database dan nama database pada file application.properties.
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdb
spring.datasource.username=yourusernamedb
spring.datasource.password=yourpassowrddb
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
jika menggunakan database MySQL dapat mengganti dependency database PostgreSQL pada **pom.xml** dengan dependency berikut
```xml
<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <scope>runtime</scope>
</dependency>
```
dan ubah application.properties menjadi:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/yourdb
spring.datasource.username=yourusernamedb
spring.datasource.password=yourpassowrddb
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

4. **Build dan Jalankan**: 
   1. Jika menginstall maven: `mvn spring-boot:run`
   2. Jika **tidak** menginstall maven: `mvnw spring-boot:run`
5. **Akses API**: Buka [http://localhost:4000/v1/swagger-ui/index.html](http://localhost:4000/v1/swagger-ui/index.html) di browser Anda.

## Dokumentasi API

Dokumentasi API dapat diakses melalui Swagger. Setelah menjalankan proyek, buka [http://localhost:4000/v1/swagger-ui/index.html](http://localhost:4000/v1/swagger-ui/index.html) di browser Anda.
