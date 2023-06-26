package org.zerock.a52.mappers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.a52.dto.MemberReadDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberMapperTests {
    
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void getTimeTest(){

        log.info(memberMapper.GetTime());
    }

    @Commit
    @Transactional
    @Test
    public void testInsert(){

        String email = "dbrudthdus@gmail.com";
        String mpw = passwordEncoder.encode("1111");
        String mname = "유경언니";

        String rolename = "ADMIN";
        
        memberMapper.insertmember(email, mpw, mname);
        memberMapper.insertMemberRole(email, rolename);
        memberMapper.insertMemberRole(email, "USER");

           
    }

    @Test
    public void selectOneTest(){

        String email = "dbrudthdus@gmail.com";

        MemberReadDTO dto = memberMapper.selectOne(email);

        log.info(dto);
    }

}
