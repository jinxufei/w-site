package com.urwoo.manager.config.shiro;

import com.urwoo.redis.JedisClient;
import com.urwoo.tools.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 利用Redis管理Session
 */
@Slf4j
public class RedisSessionDao extends AbstractSessionDAO{

    // Session超时时间，单位为毫秒
    private Integer expireTime = 30 * 60 * 60;  //30 minutes
    @Autowired
    private JedisClient jedisClient;

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        jedisClient.set((String) session.getId(), session2Str(session));
        jedisClient.expire((String) session.getId(), this.expireTime);
        log.info("doCreate() : sessionId={}", session.getId());
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            return null;
        }
        log.info("doReadSession() : sessionId={}", sessionId);
        return str2Session(jedisClient.get((String) sessionId));
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            return;
        }
        session.setTimeout(this.expireTime);
        jedisClient.set((String) session.getId(), session2Str(session));
        jedisClient.expire((String) session.getId(), this.expireTime);
    }

    @Override
    public void delete(Session session) {
        if (ObjectTools.isNull(session))
            return;
        jedisClient.del((String) session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<String> sessions = jedisClient.keys("*");
        List<Session> sessionList = new ArrayList<>();
        sessions.forEach(s -> sessionList.add(str2Session(s)));
        return sessionList;
    }

    //
    private String session2Str(Session session){
        if (ObjectTools.isNull(session))
            return null;
        byte[] bytes = SerializeTools.serialize(session);
        return ByteArrayTools.toHexString(bytes);
    }

    private Session str2Session(String str){
        if (StringTools.isNullOrEmpty(str))
            return null;
        byte[] bytes = ByteArrayTools.hexStringToByteArray(str);
        return SerializeTools.deserialize(bytes, Session.class);
    }
}
