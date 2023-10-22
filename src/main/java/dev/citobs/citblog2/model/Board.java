package dev.citobs.citblog2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; //섬머노트 라이브러리(html)섞여서 디자인


    private int count; //조회수

    @ManyToOne //Many = Board, User = One //연관관계
    @JoinColumn(name="userId")
    private User user; //DB는 오브젝트를 저장할 수 없다, 그래서 fk를 사용함. 자바는 오브젝트를 저장할 수 있음

    @OneToMany(mappedBy = "board") //mapby가 있으면 연관관계의 주인이 아니다.
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp creatDate;
}
