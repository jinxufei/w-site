package com.urwoo.mapper;

import com.urwoo.po.ArticlePo;
import com.urwoo.po.ArticleSharePo;
import com.urwoo.request.ArticleShareQueryReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleShareMapper {

    int save(ArticleSharePo articleSharePo);


    int batchUpdateStatus(@Param("ids") List<Long> ids,
                          @Param("status") Integer status);


    ArticlePo getById(@Param("id") Long id);


    Long count(@Param("param") ArticleShareQueryReq param);
}
