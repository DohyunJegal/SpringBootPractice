package com.example.website.domain.board;

import com.example.website.domain.BaseTimeEntity;
import com.example.website.domain.reply.Reply;
import com.example.website.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터 저장
    private String content;

    @Column
    private int count;

    // 누가 작성했는지 알기 위해 User 테이블과 조인(ORM문법이 알아서 진행), id값으로 foreign키 생성
    // 한 유저가 여러 게시글을 작성할 수 있으므로 ManyToOne 사용, @ManyToOne의 FetchType의 디폴트값이 EAGER(조인할 때 관련된 데이터들을 모두 가져옴)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId") // foreign키의 컬럼명 설정
    private User user;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @OrderBy("id desc") // 댓글을 최근 순으로 볼 수 있도록 설정
    @JsonIgnoreProperties({"board"}) // 무한참조 방지
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // replyList는 DB에 FK로 생성되면 안되기 때문에 mappedby 사용
    private List<Reply> replyList;
}
