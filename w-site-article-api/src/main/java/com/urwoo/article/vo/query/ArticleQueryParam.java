package com.urwoo.article.vo.query;

import com.urwoo.query.BaseQueryParam;
import lombok.Data;

@Data
public class ArticleQueryParam extends BaseQueryParam {

    private String tile;
    private Long[] cateIds;
}
