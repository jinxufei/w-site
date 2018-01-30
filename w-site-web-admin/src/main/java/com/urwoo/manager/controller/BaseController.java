package com.urwoo.manager.controller;

import com.urwoo.article.codes.ArticleBizCode;
import com.urwoo.article.codes.ArticleCategoryBizCode;
import com.urwoo.manager.responses.Message;
import com.urwoo.manager.responses.WResponses;
import com.urwoo.model.WResult;
import com.urwoo.sys.codes.SysUserBizCode;
import com.urwoo.sys.vo.SysUser;
import com.urwoo.tools.ObjectTools;
import com.urwoo.user.codes.UserBizCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController implements
        ArticleCategoryBizCode, ArticleBizCode, UserBizCode, SysUserBizCode {

    @Autowired
    protected Message message;

    public SysUser currentSysUser(){
        Subject subject = SecurityUtils.getSubject();
        if (ObjectTools.nonNull(subject))
            return (SysUser) subject.getPrincipal();
        return null;
    }

    protected WResponses articleCateCode(WResult wResult) {
        if (ObjectTools.nonNull(wResult)) {
            if (ObjectTools.equals(wResult.getCode(), SUCCESS)) {
                return WResponses.ok().put("data", wResult.getData());
            } else if (ObjectTools.equals(wResult.getCode(), ARTICLE_CATEGORY_DELETED)) {
                return WResponses.error(message.getArticleCateDeleted());
            } else if (ObjectTools.equals(wResult.getCode(), ARTICLE_CATEGORY_NOT_EXIST)) {
                return WResponses.error(message.getArticleCateNotExist());
            } else if (ObjectTools.equals(wResult.getCode(), ARTICLE_CATEGORY_NAME_IS_EXIST)) {
                return WResponses.error(message.getArticleCateNameIsExist());
            }
        }
        return WResponses.error();
    }

    protected WResponses articleCode(WResult wResult) {
        if (ObjectTools.nonNull(wResult)) {
            if (ObjectTools.equals(wResult.getCode(), SUCCESS)) {
                return WResponses.ok().put("data", wResult.getData());
            } else if (ObjectTools.equals(wResult.getCode(), ARTICLE_DELETED)) {
                return WResponses.error(message.getArticleDeleted());
            } else if (ObjectTools.equals(wResult.getCode(), ARTICLE_NOT_EXIST)) {
                return WResponses.error(message.getArticleNotExist());
            } else if (ObjectTools.equals(wResult.getCode(), ARTICLE_TITLE_IS_EXIST)) {
                return WResponses.error(message.getArticleTitleIsExist());
            }
        }
        return WResponses.error();
    }

    protected WResponses userCode(WResult wResult) {
        if (ObjectTools.nonNull(wResult)) {
            if (ObjectTools.equals(wResult.getCode(), SUCCESS)) {
                return WResponses.ok().put("data", wResult.getData());
            } else if (ObjectTools.equals(wResult.getCode(), USERNAME_IS_EXIST)) {
                return WResponses.error(message.getUsernameIsExist());
            } else if (ObjectTools.equals(wResult.getCode(), USER_EMAIL_NOT_EXIST)) {
                return WResponses.error(message.getUserEmailIsExist());
            } else if (ObjectTools.equals(wResult.getCode(), USER_PHONE_IS_EXIST)) {
                return WResponses.error(message.getUserPhoneIsExist());
            } else if (ObjectTools.equals(wResult.getCode(), USER_NOT_EXIST)) {
                return WResponses.error(message.getUserNotExist());
            }
        }
        return WResponses.error();
    }

    protected WResponses sysUserCode(WResult wResult) {
        if (ObjectTools.nonNull(wResult)) {
            if (ObjectTools.equals(wResult.getCode(), SUCCESS)) {
                return WResponses.ok().put("data", wResult.getData());
            } else if (ObjectTools.equals(wResult.getCode(), SYS_USER_NOT_EXIST)) {
                return WResponses.error(message.getSysUserNotExist());
            } else if (ObjectTools.equals(wResult.getCode(), SYS_USERNAME_EXIST)) {
                return WResponses.error(message.getSysUsernameIsExist());
            } else if (ObjectTools.equals(wResult.getCode(), SYS_USERNAME_NOT_MODIFIED)) {
                return WResponses.error(message.getSysUserUsernameNotModified());
            }
        }
        return WResponses.error();
    }

}
