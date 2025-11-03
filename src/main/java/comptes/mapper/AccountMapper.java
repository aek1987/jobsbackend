package comptes.mapper;



import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import org.apache.ibatis.annotations.Select;

import jobplatform.comptes.Account;


@Mapper
public interface AccountMapper {
    @Select("SELECT * FROM account WHERE email = #{email} ")
    Account findByEmail(String email);

    @Select("SELECT * FROM account WHERE username = #{username}")
    Account findByUsername(String username);  
    

    @Insert("INSERT INTO account(email, username, password, role, ref_id) VALUES ( #{email}, #{username}, #{password}, #{role}, #{refId})")  
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Account account);
    
    @Select("SELECT COUNT(*) FROM accounts WHERE email = #{email}")
    int countByEmail(String email);

}
