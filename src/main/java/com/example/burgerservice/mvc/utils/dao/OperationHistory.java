package com.example.burgerservice.mvc.utils.dao;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "OPERATION_HISTORY")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationHistory {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    @Column(name = "RECORD_TIME")
    private LocalDateTime recordTime;

    @Column(name = "ACTION_TYPE")
    private String actionType;

    @Column(name = "RAW_DESCRIPTION")
    private String rawDescription;

    @Column(name = "BROAD_DESCRIPTION")
    private String broadDescription;

    @PrePersist
    public void prePersist() {
        this.recordTime = LocalDateTime.now();
    }
}
