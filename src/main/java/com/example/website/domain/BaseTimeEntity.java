package com.example.website.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA 엔티티 클래스들이 BaseTimeEntity 상속할 경우 날짜 필드도 열로 인식
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate // 생성 시 자동 저장
    private LocalDateTime createDate;

    @LastModifiedDate // 수정 시 자동 저장
    private LocalDateTime modifiedDate;
}
