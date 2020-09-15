package com.mayikt.member;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    @Insert("insert into users(name ,age) values(#{name},#{age})")
    public int addUser(@Param("name") String name,@Param("age") int age);
}
