package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.entity.Machine;
import com.wufanbao.api.oldclientservice.service.UpdateTestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UpdateTestTypeController {
    @Autowired
    private UpdateTestTypeService updateTestTypeService;

    /**
     * 修改用户订单状态
     *
     * @return
     */
    @RequestMapping(value = "updateUserOrderType", method = RequestMethod.POST)
    public void updateUserOrderType(HttpServletRequest req) {
        long userOrderId = Long.parseLong(req.getParameter("userOrderId"));
        int status = Integer.valueOf(req.getParameter("status"));
        int count = updateTestTypeService.updateUserOrderType(userOrderId, status);
    }

    /**
     * 修改补货单状态
     *
     * @return
     */
    @RequestMapping(value = "updateSupplementOrderType", method = RequestMethod.POST)
    public void updateSupplementOrderType(HttpServletRequest req) {
        long supplementOrderId = Long.parseLong(req.getParameter("supplementOrderId"));
        int supplementStatus = Integer.valueOf(req.getParameter("supplementStatus"));
        int count = updateTestTypeService.updateSupplementOrderType(supplementOrderId, supplementStatus);
    }

    @RequestMapping(value = "updateDistributionType", method = RequestMethod.POST)
    public void updateDistributionType(HttpServletRequest req, HttpServletResponse rep) {
        long distributionOrderId = Long.parseLong(req.getParameter("distributionOrderId"));
        int distributionStatus = Integer.valueOf(req.getParameter("distributionStatus"));
        int count = updateTestTypeService.updateDistributionType(distributionOrderId, distributionStatus);

    }

    @RequestMapping(value = "selectMachine", method = RequestMethod.POST)
    public void selectMachine(long userOrderId, HttpServletResponse rep, HttpServletRequest req) throws IOException {
        Machine machine = updateTestTypeService.selectMachineId(userOrderId);
        long machineId = machine.getMachineId();
        req.getSession().setAttribute("machineId", machineId);
        rep.sendRedirect("index.jsp");
    }

}
