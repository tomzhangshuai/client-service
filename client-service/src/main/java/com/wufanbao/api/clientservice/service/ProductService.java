package com.wufanbao.api.clientservice.service;

import com.wufanbao.api.clientservice.common.CommonFun;
import com.wufanbao.api.clientservice.config.ClientSetting;
import com.wufanbao.api.clientservice.dao.ProductDao;
import com.wufanbao.api.clientservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CommonFun commonFun;

    public List<Product> getProductsByMachineId(long machineId) {
        List<Product> products = productDao.getProductsByMachineId(machineId);
        for (Product p : products) {
            p.setImageUrl(commonFun.sourceImage(p.getImageUrl()));
        }
        return products;
    }
}
