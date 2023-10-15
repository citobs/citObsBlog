package dev.citobs.citblog2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity //User class가 Mysql에 테이블이 생성됨
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
//@DynamicInsert //insert할때 null인 값 제외
public class User {

    @Id //PK키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에 설정된 DB의 넘버링 전략을 따라감
    private int id; //시퀀스, auto_increment


    @Column(nullable = false, length = 30)
    private String username; //id

    @Column(nullable = false, length = 100) //123456 => 해쉬(비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("'user'")//줄때 따옴표안에 홑따옴표로 줘야한다. //ENUM 추가후 제거
//    private String role; //Enum을 쓰는게 좋다./admin, user, manager
    //DB는 RoleType이라는게 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp // 시간이 자동으로 입력됨
    private Timestamp createDate;


}
