package com.wufanbao.api.olddriverservice.service.impl;

import com.wufanbao.api.olddriverservice.dao.MachineDao;
import com.wufanbao.api.olddriverservice.entity.Machine;
import com.wufanbao.api.olddriverservice.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-10-24
 * Time:20:28
 */
@Service
@Transactional
public class MachineServiceImpl implements MachineService {
    @Autowired
    private MachineDao machineDao;

    @Override
    public int updateMachine(Machine machine) {
        return machineDao.updateMachine(machine);
    }

    @Override
    public List<Machine> queryMachine(Long machineId) {
        return machineDao.queryMachine(machineId);
    }

    @Override
    public int updateMachineOne(Machine machine) {
        return machineDao.updateMachineOne(machine);
    }
}
