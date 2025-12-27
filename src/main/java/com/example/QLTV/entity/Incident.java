package com.example.QLTV.entity;
import com.example.QLTV.entity.e_num.IncidentPriority;
import com.example.QLTV.entity.e_num.IncidentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Incident extends BaseEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "CHAR(36)")
    UUID id;

    String title;
    String description;

    @Enumerated(EnumType.STRING)
    IncidentPriority priority;

    @Enumerated(EnumType.STRING)
    IncidentStatus status;
}

