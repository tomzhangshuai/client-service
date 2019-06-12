package com.wufanbao.api.olddriverservice.service;

import com.wufanbao.api.olddriverservice.entity.SupplementDetails;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-12
 * Time:10:05
 */
public interface SupplementDetailsService {
    /**
     * 补货单详情
     *
     * @param supplementOrderId
     * @return
     */
    public List<SupplementDetails> querySupplementDetails(long supplementOrderId);

}
