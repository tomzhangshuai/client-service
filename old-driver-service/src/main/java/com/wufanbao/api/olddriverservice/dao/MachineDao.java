package com.wufanbao.api.olddriverservice.dao;

import com.wufanbao.api.olddriverservice.entity.Machine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-10-24
 * Time:20:21
 */
public interface MachineDao {
    /**
     * 修改机器数量
     *
     * @param machine
     * @return
     */
    public int updateMachine(Machine machine);

    public List<Machine> queryMachine(Long machineId);

    int updateMachineOne(Machine machine);

    Long getMachineIdByNo(@Param("machineNo") String machineNo);
}
