package com.urwoo.mapper;

import com.urwoo.po.ArticleInfoPo;
import com.urwoo.po.ArticlePo;
import com.urwoo.request.ArticleQueryReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {

    int save(ArticlePo articlePo);

    int update(ArticlePo articlePo);

    int batchUpdateStatus(@Param("ids") List<Long> ids,
                          @Param("status") Integer status);

    int batchUpdateCheckStatus(@Param("ids") List<Long> ids,
                           @Param("checkStatus") Integer checkStatus);

    ArticlePo getById(@Param("id") Long id);

    ArticlePo getByTitle(@Param("userId") Long userId, @Param("title") String title);

    List<ArticleInfoPo> query(@Param("param") ArticleQueryReq param,
                              @Param("start") Long start,
                              @Param("limit") Integer limit);

    ArticleInfoPo getInfo(@Param("id") Long id);

    Long count(@Param("param") ArticleQueryReq param);
}
