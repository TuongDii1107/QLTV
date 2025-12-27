USE qltv;
DESCRIBE `user`;
/* ================= USER ================= */
ALTER TABLE `user`
    ADD COLUMN username VARCHAR(255) UNIQUE;
INSERT INTO `user` (id, username, password, email, status)
VALUES ('u1', 'admin', '123456', 'admin@qltv.edu.vn', 'ACTIVE'),
       ('u2', 'librarian', '123456', 'librarian@qltv.edu.vn', 'ACTIVE'),
       ('u3', 'sv001', '123456', 'sv001@student.edu.vn', 'ACTIVE'),
       ('u4', 'sv002', '123456', 'sv002@student.edu.vn', 'ACTIVE');

/* ================= ROLE ================= */
INSERT INTO role (id, name)
VALUES ('r1', 'ADMIN'),
       ('r2', 'LIBRARIAN'),
       ('r3', 'STUDENT');

/* ================= USER_ROLE ================= */
INSERT INTO user_role (user_id, role_id)
VALUES ('u1', 'r1'),
       ('u2', 'r2'),
       ('u3', 'r3'),
       ('u4', 'r3');

/* ================= STAFF ================= */
INSERT INTO staff (id, staff_code, status, user_id)
VALUES ('st1', 'TT001', 'ACTIVE', 'u2');

/* ================= STUDENT ================= */
INSERT INTO student (id, student_code, faculty, clazz, status, fine_balance, user_id)
VALUES ('sv1', 'SV001', 'Công nghệ thông tin', 'K26CNTT01', 'ACTIVE', 0, 'u3'),
       ('sv2', 'SV002', 'Quản trị kinh doanh', 'K26QTKD01', 'ACTIVE', 20000, 'u4');

/* ================= BOOK ================= */
INSERT INTO book (id, title, author, isbn, category, publisher, published_year, price, shelf_code)
VALUES ('b1', 'Lập Trình Java Cơ Bản', 'Nguyễn Văn A', '978604000001', 'CNTT', 'NXB Giáo Dục', '2022', 95000, 'IT-A1'),
       ('b2', 'Cơ Sở Dữ Liệu', 'Trần Văn B', '978604000002', 'CNTT', 'NXB Khoa Học', '2021', 120000, 'IT-A2'),
       ('b3', 'Kỹ Năng Giao Tiếp', 'Lê Thị C', '978604000003', 'Kỹ năng mềm', 'NXB Trẻ', '2020', 80000, 'SK-B1');

/* ================= BOOK_COPY ================= */
INSERT INTO book_copy (id, barcode, circulation_status, condition_status, book_id)
VALUES ('bc1', 'BC-JAVA-001', 'AVAILABLE', 'NEW', 'b1'),
       ('bc2', 'BC-JAVA-002', 'AVAILABLE', 'GOOD', 'b1'),
       ('bc3', 'BC-DB-001', 'BORROWED', 'GOOD', 'b2'),
       ('bc4', 'BC-SK-001', 'AVAILABLE', 'NEW', 'b3');

/* ================= LOAN ================= */
INSERT INTO loan (id, borrowed_at, due_date, status, student_id, book_copy_id)
VALUES ('l1', '2025-12-20 09:00:00', '2025-12-27 23:59:59', 'BORROWING', 'sv1', 'bc3');

/* ================= RETURN_TRANSACTION ================= */
INSERT INTO return_transaction (id, return_at, book_condition, note, loan_id, staff_id)
VALUES ('rt1', '2025-12-26 10:30:00', 'GOOD', 'Trả sách đúng hạn', 'l1', 'st1');

/* ================= FINE ================= */
INSERT INTO fine (id, student_id, loan_id, fine_type, amount, status)
VALUES ('f1', 'sv2', NULL, 'LATE_RETURN', 20000, 'UNPAID');

/* ================= FINE_PAYMENT ================= */
INSERT INTO fine_payment (id, amount, payment_method, paid_at, fine_id)
VALUES ('fp1', 20000, 'CASH', '2025-12-26 14:00:00', 'f1');

/* ================= RESERVATION ================= */
INSERT INTO reservation (id, reserved_at, expired_at, status, student_id, book_copy_id)
VALUES ('rs1', '2025-12-26 08:00:00', '2025-12-27 08:00:00', 'ACTIVE', 'sv2', 'bc2');

/* ================= REVIEW ================= */
INSERT INTO review (id, rating, comment, student_id, book_id)
VALUES ('rv1', 5, 'Sách dễ hiểu, phù hợp người mới học', 'sv1', 'b1');

/* ================= NOTIFICATION ================= */
INSERT INTO notification (id, title, content, type, is_read, user_id)
VALUES ('n1', 'Nhắc trả sách', 'Bạn còn 1 ngày để trả sách Cơ Sở Dữ Liệu', 'REMINDER', 0, 'u3');

/* ================= INCIDENT ================= */
INSERT INTO incident (id, title, description, priority, status)
VALUES ('i1', 'Sách hư hỏng', 'Phát hiện sách bị rách trang', 'HIGH', 'OPEN');

SELECT COUNT(*) FROM user;
SELECT COUNT(*) FROM role;
SELECT COUNT(*) FROM user_role;
SELECT COUNT(*) FROM staff;
SELECT COUNT(*) FROM student;
SELECT COUNT(*) FROM book;
SELECT COUNT(*) FROM book_copy;
SELECT COUNT(*) FROM loan;
SELECT COUNT(*) FROM return_transaction;
SELECT COUNT(*) FROM fine;
SELECT COUNT(*) FROM fine_payment;
SELECT COUNT(*) FROM reservation;
SELECT COUNT(*) FROM review;
SELECT COUNT(*) FROM notification;
SELECT COUNT(*) FROM incident;
