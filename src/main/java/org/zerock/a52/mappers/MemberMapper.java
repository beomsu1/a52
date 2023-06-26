package org.zerock.a52.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.a52.dto.MemberReadDTO;

public interface MemberMapper {

    
    MemberReadDTO selectOne(String email);
    
    @Select("select now()")
    String GetTime();

    @Insert("insert into tbl_member (email , mpw , mname) values (#{email} , #{mpw} , #{mname})")
    // param은 파라미터가 여러개 일떄 사용, 마이바티스는 파라미터 하나밖에 못받는데 이걸 사용하면 여러개 사용가능 , 원래는 DTO를 만들어서 사용
    int insertmember(@Param("email") String email , @Param("mpw") String mpw , @Param("mname") String mname);

    @Insert("insert into tbl_member_role (email, rolename) values (#{email}, #{rolename})")
    int insertMemberRole(@Param("email") String email , @Param("rolename") String rolename);
    
}
