package com.wufanbao.api.distributionservice.dao;

import com.wufanbao.api.distributionservice.entities.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface EmployeeDao {

    public Employee getEmployeeByLoginNo( @Param("loginNo") String loginNo);
}
