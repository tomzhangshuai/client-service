package com.wufanbao.api.distributionservice.dao;

import com.wufanbao.api.distributionservice.entities.Company;
import com.wufanbao.api.distributionservice.entities.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface EmployeeDao {

    Employee getEmployeeByLoginNo( @Param("loginNo") String loginNo);

}
