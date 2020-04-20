/**
 * 
 */
package com.basic.integrate.util;

import java.util.Calendar;
import java.util.Date;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * @author sunjinpeng
 * @description
 * @creatDate 2019年3月25日
 * @lastModifyDate 2019年3月25日
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTUtil {

	/**
	 * 秘钥
	 */
	private static String secret;
	/**
	 * 过期时间（秒）
	 */
	private int expire;

	public static String getSecret() {
		return secret;
	}

	public static void setSecret(String secret) {
		JWTUtil.secret = secret;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	/**
	 * 创建token
	 * 
	 * @auth sunjinpeng
	 * @description
	 * @creatDate 2019年3月25日
	 * @lastModifyDate 2019年3月25日
	 * @param username
	 * @param expires  过期时间（小时）
	 * @return
	 */
	public static String createToken(String userid, String officeid, String userNo, String name) {
		// 签发时间
		Date iatDate = new Date();
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.HOUR, 24);
		// 过期时间
		Date expiresDate = nowTime.getTime();

		nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, -5);
		// 开始使用时间
		Date notBefore = nowTime.getTime();

		Algorithm algorithm = Algorithm.HMAC256(secret);

		String token = JWT.create().withIssuedAt(iatDate).withExpiresAt(expiresDate).withClaim("userid", userid)
				.withClaim("officeid", officeid).withClaim("userNo", userNo).withClaim("username", name)
				.withNotBefore(notBefore).sign(algorithm);
		return token;
	}
}
