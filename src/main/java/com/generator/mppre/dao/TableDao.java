package com.generator.mppre.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TableDao {

    @Select("select * from information_schema.TABLES where TABLE_SCHEMA=(select database())")
    List<Map<String, Object>> listTable();

    @Select("select * from information_schema.COLUMNS where TABLE_SCHEMA = (select database()) and TABLE_NAME=#{tableName}")
    List<Map<String, Object>> listTableColumn(String tableName);
}

