package com.urwoo.mapper;

import com.urwoo.po.ArticleCategoryPo;
import com.urwoo.request.ArticleCategoryQueryReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCategoryMapper {

    int save(ArticleCategoryPo articleCategoryPo);

    int update(ArticleCategoryPo articleCategoryPo);

    int batchUpdateStatus(@Param("ids") List<Long> ids,
                          @Param("status") Integer status);

    ArticleCategoryPo getById(@Param("id") Long id);

    ArticleCategoryPo getByName(@Param("name") String name);

    List<ArticleCategoryPo> query(@Param("param") ArticleCategoryQueryReq param,
                                  @Param("start") Long start,
                                  @Param("limit") Integer limit);

    List<ArticleCategoryPo> listByStatus(@Param("status") Integer status);

    Long count(@Param("param") ArticleCategoryQueryReq param);
}
