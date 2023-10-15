package dev.citobs.citblog2.test;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data //이게 getter, setter다
// @AllArgsConstructor //얜 생성자

public class Member {
    private int id;
    private String username; //data가 변경되지않게 final
    private String password;
    private String email;


}
