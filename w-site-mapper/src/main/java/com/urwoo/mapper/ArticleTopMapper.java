package com.urwoo.mapper;

import com.urwoo.po.ArticlePo;
import com.urwoo.po.ArticleTopPo;
import com.urwoo.request.ArticleTopQueryReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleTopMapper {

    int save(ArticleTopPo articleTopPo);


    int batchUpdateStatus(@Param("ids") List<Long> ids,
                          @Param("status") Integer status);


    ArticlePo getById(@Param("id") Long id);


    Long count(@Param("param") ArticleTopQueryReq param);
}
