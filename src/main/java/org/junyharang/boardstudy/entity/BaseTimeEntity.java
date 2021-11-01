package org.junyharang.boardstudy.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass @Getter
@EntityListeners(value = { AuditingEntityListener.class})
public class BaseTimeEntity {

    @CreatedDate @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate @Column(name = "moddate")
    private LocalDateTime modDate;

} // BaseTimeEntity() 끝
