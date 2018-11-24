package com.ford.qiye.model;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wanglijun on 16/12/3.
 */
public class OperationTypeHeanlder  extends BaseTypeHandler<OperationType>{

    @Override
    public OperationType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, OperationType operationType, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public OperationType getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public OperationType getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
