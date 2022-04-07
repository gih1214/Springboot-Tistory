package site.metacoding.blogv3.domain.love;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import site.metacoding.blogv3.domain.post.Post;
import site.metacoding.blogv3.domain.user.User;

@EntityListeners(AuditingEntityListener.class) // 이 부분 추가
@Entity
@Table( // 두 개의 컬럼을 동시에 유니크 주기
        uniqueConstraints = {
                @UniqueConstraint(name = "love_uk", // 유니크 이름
                        columnNames = { "postId", "userId" } // 어디에 줄건지
                )
        })
public class Love {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "postId")
    @ManyToOne
    private Post post;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    @CreatedDate // insert 할때만 동작
    private LocalDateTime createDate;
    @LastModifiedDate // update 할때만 동작
    private LocalDateTime updateDate;

}