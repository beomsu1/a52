package org.zerock.a52.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class MemberDTO extends User{

    private String mname;
    
    // 생성자 만들기 /항상 부모클래스의 생성자를 호출해야함
    public MemberDTO(String email, String mpw, String mname, List<String> roleNames){

        super(email,mpw , roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_"+str)).collect(Collectors.toList())
        );
        // username 대신에 email

        this.mname = mname;

    }

    // 이 생성자는 의미가 없음
    // public MemberDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
    //     super(username, password, authorities);
    // }
    
}
