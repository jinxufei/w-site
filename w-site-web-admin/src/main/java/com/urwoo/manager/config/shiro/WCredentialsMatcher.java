package com.urwoo.manager.config.shiro;

import com.urwoo.tools.Md5Tools;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.codec.CodecSupport;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class WCredentialsMatcher extends CodecSupport implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Object tokenCredentials = token.getCredentials();
        Object accountCredentials = info.getCredentials();
        log.info("doCredentialsMatch() : token={}, info={}", tokenCredentials, accountCredentials);
        return this.equals(tokenCredentials, accountCredentials);
    }

    //
    private boolean equals(Object tokenCredentials, Object accountCredentials) {
        try {
            return Md5Tools.getInstance().validPassword(
                     this.toString(tokenCredentials), this.toString(accountCredentials));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error("password verify cause error!", e);
            return false;
        }
    }
}
