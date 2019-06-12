package com.wufanbao.api.olddriverservice.service;

import com.wufanbao.api.olddriverservice.entity.Machine;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-10-24
 * Time:20:27
 */
public interface MachineService {
    /**
     * 修改机器数量
     *
     * @param machine
     * @return
     */
    public int updateMachine(Machine machine);


    public List<Machine> queryMachine(Long machineId);

    int updateMachineOne(Machine machine);
}
