package com.wufanbao.api.distributionservice.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineDao {

    /**
     *
     * @param machineId
     * @return
     */
     String getMachineNo(@Param("machineId") long machineId);

    /**
     *
     * @param machineId
     * @return
     */
     boolean getMachineInmaintenance(@Param("machineId") long machineId);
}
