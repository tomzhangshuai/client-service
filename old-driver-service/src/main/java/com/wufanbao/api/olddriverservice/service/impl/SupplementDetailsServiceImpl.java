package com.wufanbao.api.olddriverservice.service.impl;

import com.wufanbao.api.olddriverservice.dao.SupplementDetailsDao;
import com.wufanbao.api.olddriverservice.entity.SupplementDetails;
import com.wufanbao.api.olddriverservice.service.SupplementDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-12
 * Time:10:07
 */
@Service
@Transactional
public class SupplementDetailsServiceImpl implements SupplementDetailsService {

    @Autowired
    private SupplementDetailsDao supplementDetailsDao;

    @Override
    public List<SupplementDetails> querySupplementDetails(long supplementOrderId) {
        return supplementDetailsDao.querySupplementDetails(supplementOrderId);
    }
}
