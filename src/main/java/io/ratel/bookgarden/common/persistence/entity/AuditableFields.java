package io.ratel.bookgarden.common.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@ToString
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass()
@Slf4j
public class AuditableFields{

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate()
    @Column(name = "CREATED_AT", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @CreatedBy()
    @Column(name = "CREATED_BY",
            nullable = false,
            length=255,
            columnDefinition = "VARCHAR(255) DEFAULT 'SYSTEM'")
    private String createdBy = "SYSTEM";

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate()
    @Column(name = "MODIFIED_AT",
            nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime modifiedAt;

    @LastModifiedBy()
    @Column(name = "MODIFIED_BY",
            nullable = false,
            length=255,
            columnDefinition = "VARCHAR(255) DEFAULT 'SYSTEM'")
    private String modifiedBy;

    @PrePersist
    protected void onCreate() {
        log.debug("AuditableFields createdAt : {}", createdAt);
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }


}
