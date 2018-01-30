package com.urwoo.user.adapter;

import com.urwoo.enums.Gender;
import com.urwoo.enums.Level;
import com.urwoo.enums.LoginType;
import com.urwoo.enums.Status;
import com.urwoo.po.UserPo;
import com.urwoo.tools.ObjectTools;
import com.urwoo.user.vo.User;

public class UserAdapter {

    public static UserPo user2Po(User user) {

        if (ObjectTools.isNull(user))
            return null;

        UserPo po = new UserPo();
        po.setId(user.getId());
        po.setMeta(user.getMeta());
        po.setNickname(user.getNickname());
        po.setUsername(user.getUsername());

        po.setPhone(user.getPhone());
        po.setAvatar(user.getAvatar());
        po.setEmail(user.getEmail());
        po.setMd5(user.getMd5());
        po.setPassword(user.getPassword());

        //
        if (ObjectTools.isNull(user.getGender())) {
            po.setGender(Gender.UNKNOWN.code());
        } else {
            po.setGender(user.getGender().code());
        }
        //
        if (ObjectTools.isNull(user.getGender())) {
            po.setLevel(user.getLevel().code());
        } else {
            po.setLevel(Level.LEVEL_1.code());
        }
        //
        if (ObjectTools.isNull(user.getStatus())) {
            po.setStatus(Status.ON.code());
        } else {
            po.setStatus(user.getStatus().code());
        }
        return po;
    }

    public static User po2User(UserPo po) {

        if (ObjectTools.isNull(po))
            return null;

        User user = new User();

        user.setId(po.getId());
        user.setMeta(po.getMeta());
        user.setNickname(po.getNickname());
        user.setUsername(po.getUsername());
        user.setPhone(po.getPhone());
        user.setAvatar(po.getAvatar());
        user.setEmail(po.getEmail());
        user.setMd5(po.getMd5());
        user.setPassword(po.getPassword());
        user.setCreateTime(po.getCreateTime());
        user.setModifyTime(po.getModifyTime());
        user.setGender(Gender.gender(po.getGender()));
        user.setLevel(Level.level(po.getLevel()));
        user.setStatus(Status.status(po.getStatus()));

        return user;
    }
}
