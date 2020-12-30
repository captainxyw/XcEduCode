package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PageService {
    @Autowired
    CmsPageRepository cmsPageRepository;

    /**
     * 页面查询方法
     * @param page 页码，从1开始计数
     * @param size 煤业记录数
     * @param request 查询条件
     * @return
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest request) {
        if(request == null)
            request = new QueryPageRequest();
        //条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //条件值对象
        CmsPage cmsPage = new CmsPage();
        //站点id
        if(StringUtils.isNotEmpty(request.getSiteId()))
            cmsPage.setSiteId(request.getSiteId());
        //模板id
        if(StringUtils.isNotEmpty(request.getTemplateId()))
            cmsPage.setTemplateId(request.getTemplateId());
        //页面别名
        if(StringUtils.isNotEmpty(request.getPageAliase()))
            cmsPage.setPageAliase(request.getPageAliase());


        if (page <= 0) {
            page = 1;
        }
        page = page - 1;
        if(size <= 0) {
            size = 10;
        }
        Example<CmsPage> example =  Example.of(cmsPage, exampleMatcher);
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);       //自定义条件查询并且分页查询
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }
}