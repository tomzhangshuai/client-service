package com.wufanbao.api.clientservice.service;

import com.wufanbao.api.clientservice.common.Data;
import com.wufanbao.api.clientservice.dao.ProductoffDao;
import com.wufanbao.api.clientservice.entity.ProductOff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoffService {

    @Autowired
    private ProductoffDao productoffDao;

    public List<Data> getProductoff( ){
       List<Data> productoff=productoffDao.getProductoff();
       return productoff;
    }

    @Transactional(rollbackFor = ApiServiceException.class)
    public int updateMessageStatus(long userOrderId){
        return  productoffDao.updateMessageStatus(userOrderId);
    }

}
