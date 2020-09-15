package com.mayikt.mapper;

import com.mayikt.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from users where name=#{name}")
    User findbyName(@Param("name") String name);


    @Insert("insert into users(name,age)values(#{name},#{age})")
    int insert(@Param("name") String name,@Param("age") Integer age);
}
