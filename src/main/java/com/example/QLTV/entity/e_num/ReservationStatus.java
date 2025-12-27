package com.example.QLTV.entity.e_num;

public enum ReservationStatus {
    PENDING,        // Chờ xử lý
    READY,          // Có sách – chờ nhận
    CONFIRMED,      // Đã nhận
    EXPIRED,        // Hết hạn giữ chỗ
    CANCELLED       // Bị hủy
}
