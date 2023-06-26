package org.zerock.a52.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.a52.dto.MemberDTO;
import org.zerock.a52.dto.MemberReadDTO;
import org.zerock.a52.mappers.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final MemberMapper memberMapper;
    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    //     log.info("------------------------------");
    //     log.info("loadUserByUsername: " + username);
    //     log.info("------------------------------");

    //     UserDetails user = User.builder()
    //     .username(username)
    //     .password(passwordEncoder.encode("1111"))
    //     .authorities("ROLE_USER" , "ROLE_G1")
    //     .build();

    //     return user;
    // }

        @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername: " + username );

        MemberReadDTO readDTO = memberMapper.selectOne(username);

        MemberDTO memberDTO = 
        new MemberDTO(
        username, 
        readDTO.getMpw(),
        readDTO.getMname(),
        readDTO.getRolenames()
         );

        memberDTO.setMname("범수");
        return memberDTO;

    }
    
}
