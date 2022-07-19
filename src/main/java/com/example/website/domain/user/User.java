package com.example.website.domain.user;

import com.example.website.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 선언된 모든 필드의 getter 메소드 생성
@Builder
@AllArgsConstructor
@NoArgsConstructor // 빈 생성자 생성
@Entity // 해당 클래스가 엔티티를 위함이며 인스턴스들이 JPA로 관리되는 엔티티 객체(테이블)임을 의미
public class User extends BaseTimeEntity {
    @Id // 테이블의 Primary Key(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 자동 생성
    private Long id;

    @Column(nullable = false, length = 20, unique = true) // 해당 필드가 열이며 필드가 null이 될 수 없음, 길이 제한
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Enumerated(EnumType.STRING) // JPA를 통해 DB에 저장 시 Enum 값을 어떤 형태로 저장할 지 지정
    @Column(nullable = false)
    private Role role;

    public String getRoleKey() {
        return this.role.getKey();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void update(String password, String nickname) {
        this.password = password;
        this.nickname = nickname;
    }
}