package com.wufanbao.api.olddriverservice.dao;


import com.wufanbao.api.olddriverservice.entity.SupplementDetails;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-12
 * Time:10:01
 */
public interface SupplementDetailsDao {

    /**
     * 前仓详情
     *
     * @param supplementOrderId
     * @return
     */
    public List<SupplementDetails> querySupplementDetails(long supplementOrderId);
}
