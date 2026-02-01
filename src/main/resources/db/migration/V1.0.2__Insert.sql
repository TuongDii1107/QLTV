INSERT INTO user (id, username, password, email, status)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'admin', '123456', 'admin@qltv.edu.vn', 'ACTIVE'),
       ('550e8400-e29b-41d4-a716-446655440001', 'librarian', '123456', 'librarian@qltv.edu.vn', 'ACTIVE'),
       ('550e8400-e29b-41d4-a716-446655440002', 'sv001', '123456', 'sv001@student.edu.vn', 'ACTIVE'),
       ('550e8400-e29b-41d4-a716-446655440003', 'sv002', '123456', 'sv002@student.edu.vn', 'ACTIVE'),
       ('550e8400-e29b-41d4-a716-446655440004', 'sv003', '123456', 'sv003@student.edu.vn', 'ACTIVE');
INSERT INTO role (id, name, description)
VALUES ('660e8400-e29b-41d4-a716-446655440000', 'ADMIN', 'Quản trị'),
       ('660e8400-e29b-41d4-a716-446655440001', 'LIBRARIAN', 'Thủ thư'),
       ('660e8400-e29b-41d4-a716-446655440002', 'STUDENT', 'Sinh viên');
INSERT INTO user_role (user_id, role_id)
VALUES ('550e8400-e29b-41d4-a716-446655440000', '660e8400-e29b-41d4-a716-446655440000'),
       ('550e8400-e29b-41d4-a716-446655440001', '660e8400-e29b-41d4-a716-446655440001'),
       ('550e8400-e29b-41d4-a716-446655440002', '660e8400-e29b-41d4-a716-446655440002'),
       ('550e8400-e29b-41d4-a716-446655440003', '660e8400-e29b-41d4-a716-446655440002'),
       ('550e8400-e29b-41d4-a716-446655440004', '660e8400-e29b-41d4-a716-446655440002');
INSERT INTO staff (id, staff_code, status, user_id)
VALUES ('770e8400-e29b-41d4-a716-446655440000', 'TT001', 'ACTIVE', '550e8400-e29b-41d4-a716-446655440001');
INSERT INTO student (id, student_code, faculty, clazz, status, fine_balance, user_id)
VALUES ('880e8400-e29b-41d4-a716-446655440000', 'SV001', 'CNTT', 'K26CNTT01', 'ACTIVE', 0,
        '550e8400-e29b-41d4-a716-446655440002'),
       ('880e8400-e29b-41d4-a716-446655440001', 'SV002', 'QTKD', 'K26QTKD01', 'ACTIVE', 20000,
        '550e8400-e29b-41d4-a716-446655440003'),
       ('880e8400-e29b-41d4-a716-446655440002', 'SV003', 'KT', 'K26KT01', 'ACTIVE', 0,
        '550e8400-e29b-41d4-a716-446655440004');
INSERT INTO book (id, title, author, isbn, category, publisher, published_year, price, shelf_code)
VALUES ('990e8400-e29b-41d4-a716-446655440000', 'Lập Trình Java', 'Nguyễn Văn A', '978604000001', 'CNTT', 'NXB GD',
        '2022', 95000, 'IT-A1'),
       ('990e8400-e29b-41d4-a716-446655440001', 'Cơ Sở Dữ Liệu', 'Trần Văn B', '978604000002', 'CNTT', 'NXB KH', '2021',
        120000, 'IT-A2'),
       ('990e8400-e29b-41d4-a716-446655440002', 'Spring Boot', 'Phạm Văn D', '978604000004', 'CNTT', 'NXB GD', '2023',
        150000, 'IT-A3'),
       ('990e8400-e29b-41d4-a716-446655440003', 'Kỹ Năng Giao Tiếp', 'Lê Thị C', '978604000003', 'Kỹ năng', 'NXB Trẻ',
        '2020', 80000, 'SK-B1');
INSERT INTO book_copy (id, barcode, circulation_status, condition_status, book_id)
VALUES ('a10e8400-e29b-41d4-a716-446655440000', 'BC-JAVA-001', 'AVAILABLE', 'NORMAL',
        '990e8400-e29b-41d4-a716-446655440000'),
       ('a10e8400-e29b-41d4-a716-446655440001', 'BC-JAVA-002', 'BORROWED', 'DAMAGED_LIGHT',
        '990e8400-e29b-41d4-a716-446655440000'),
       ('a10e8400-e29b-41d4-a716-446655440002', 'BC-JAVA-003', 'RESERVED', 'NORMAL',
        '990e8400-e29b-41d4-a716-446655440000'),

       ('a10e8400-e29b-41d4-a716-446655440003', 'BC-DB-001', 'AVAILABLE', 'NORMAL',
        '990e8400-e29b-41d4-a716-446655440001'),
       ('a10e8400-e29b-41d4-a716-446655440004', 'BC-DB-002', 'DAMAGED', 'DAMAGED_MEDIUM',
        '990e8400-e29b-41d4-a716-446655440001'),

       ('a10e8400-e29b-41d4-a716-446655440005', 'BC-SPR-001', 'AVAILABLE', 'NORMAL',
        '990e8400-e29b-41d4-a716-446655440002'),
       ('a10e8400-e29b-41d4-a716-446655440006', 'BC-SPR-002', 'LOST', 'LOST', '990e8400-e29b-41d4-a716-446655440002'),

       ('a10e8400-e29b-41d4-a716-446655440007', 'BC-SK-001', 'AVAILABLE', 'NORMAL',
        '990e8400-e29b-41d4-a716-446655440003');
INSERT INTO loan (id, borrowed_at, due_date, status, student_id, book_copy_id)
VALUES ('b20e8400-e29b-41d4-a716-446655440000', '2025-12-20 09:00:00', '2025-12-27 23:59:59', 'BORROWING',
        '880e8400-e29b-41d4-a716-446655440000', 'a10e8400-e29b-41d4-a716-446655440001');
INSERT INTO return_transaction (id, return_at, book_condition, note, loan_id, staff_id)
VALUES ('c30e8400-e29b-41d4-a716-446655440000', '2025-12-26 10:30:00',
        'DAMAGED_LIGHT', 'Trả sách có trầy xước',
        'b20e8400-e29b-41d4-a716-446655440000',
        '770e8400-e29b-41d4-a716-446655440000');
