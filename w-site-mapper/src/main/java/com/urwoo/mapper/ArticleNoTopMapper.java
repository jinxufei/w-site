package com.urwoo.mapper;

import com.urwoo.po.ArticleNoTopPo;
import com.urwoo.po.ArticlePo;
import com.urwoo.request.ArticleNoTopQueryReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleNoTopMapper {

    int save(ArticleNoTopPo articleNoTopPo);


    int batchUpdateStatus(@Param("ids") List<Long> ids,
                          @Param("status") Integer status);


    ArticlePo getById(@Param("id") Long id);


    Long count(@Param("param") ArticleNoTopQueryReq param);
}
