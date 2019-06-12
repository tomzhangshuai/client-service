package com.wufanbao.api.clientservice.dao;

import com.wufanbao.api.clientservice.entity.Machine;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MachineDao {
    /**
     * 根据区域Id 获取机器
     *
     * @param areaId
     * @return
     */
    List<Machine> getMachinesByAreaId(@Param("areaId") int areaId);

    /**
     * 获取所有机器
     *
     * @return
     */
    List<Machine> getMachines();

    /**
     * 获取机器信息
     *
     * @param machineId
     * @return
     */
    Machine getMachine(@Param("machineId") long machineId);


}
