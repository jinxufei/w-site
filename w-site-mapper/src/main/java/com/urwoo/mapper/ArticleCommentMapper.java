package com.urwoo.mapper;

import com.urwoo.po.ArticleCommonPo;
import com.urwoo.request.ArticleCommentQueryReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCommentMapper {

    int save(ArticleCommonPo ArticleCommonPo);

    int update(ArticleCommonPo ArticleCommonPo);

    int batchUpdateStatus(@Param("ids") List<Long> ids,
                          @Param("status") Integer status);

    ArticleCommonPo getById(@Param("id") Long id);

    List<ArticleCommonPo> query(@Param("param") ArticleCommentQueryReq param,
                                @Param("start") Long start,
                                @Param("limit") Integer limit);

    Long count(@Param("param") ArticleCommentQueryReq param);
}
