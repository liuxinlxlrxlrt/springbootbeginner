package com.mayikt.order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    @Insert("insert into order_number(number) values(#{number})")
    int insertOrder(@Param("number") String number);
}
