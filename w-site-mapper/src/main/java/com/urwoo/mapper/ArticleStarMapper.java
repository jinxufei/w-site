package com.urwoo.mapper;

import com.urwoo.po.ArticlePo;
import com.urwoo.po.ArticleStarPo;
import com.urwoo.request.ArticleStarQueryReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleStarMapper {

    int save(ArticleStarPo articleStarPo);


    int batchUpdateStatus(@Param("ids") List<Long> ids,
                          @Param("status") Integer status);


    ArticlePo getById(@Param("id") Long id);


    Long count(@Param("param") ArticleStarQueryReq param);
}
