// UserMapper.java
package com.lx.novel_writing_backend.mapper;

import com.lx.novel_writing_backend.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    // 已有的查询方法...
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM users WHERE email = #{email}")
    User findByEmail(@Param("email") String email);

    @Select("SELECT * FROM users WHERE username = #{username} OR email = #{email}")
    User findByUsernameOrEmail(@Param("username") String username, @Param("email") String email);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(@Param("id") Long id);

    // 新增：注册用户
    @Insert("INSERT INTO users (username, name, email, phone, password_hash) " +
            "VALUES (#{username}, #{name}, #{email}, #{phone}, #{passwordHash})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    // 新增：通过姓名、邮箱、电话查询用户（用于密码重置验证）
    @Select("SELECT * FROM users WHERE name = #{name} AND email = #{email} AND phone = #{phone}")
    User findByNameEmailPhone(@Param("name") String name,
                              @Param("email") String email,
                              @Param("phone") String phone);

    // 新增：更新密码
    @Update("UPDATE users SET password_hash = #{passwordHash}, updated_at = NOW() WHERE id = #{id}")
    void updatePassword(@Param("id") Long id, @Param("passwordHash") String passwordHash);
}
