package cn.hzq.openai.data.domain.auth.service;

import cn.hzq.openai.data.domain.auth.model.entity.AuthStateEntity;
import cn.hzq.openai.data.domain.auth.model.valobj.AuthTypeVO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @author 黄照权
 * @Date 2024/4/22
 * @Description 鉴权验证服务接口抽象类
 **/
@Slf4j
public abstract class AbstractAuthService implements IAuthService {
    /**
     * SecretKey 要替换为你自己的，并且最好是通过配置的方式使用
     */
    private static final String defaultBase64EncodedSecretKey = "B*B^D%fe";
    private final String base64EncodedSecretKey = Base64.encodeBase64String(defaultBase64EncodedSecretKey.getBytes());
    private final Algorithm algorithm = Algorithm.HMAC256(Base64.decodeBase64(Base64.encodeBase64String(defaultBase64EncodedSecretKey.getBytes())));

    @Override
    public AuthStateEntity doLogin(String code) {
        // 1. 如果不是4位有效数字字符串，则返回验证码无效
        if (!code.matches("\\d{4}")) {
            log.info("鉴权，用户输入验证码无效{}", code);
            return AuthStateEntity.builder()
                    .code(AuthTypeVO.A0002.getCode())
                    .info(AuthTypeVO.A0002.getInfo())
                    .build();
        }
        // 2. 校验判断，非成功则直接返回
        AuthStateEntity authStateEntity = this.checkCode(code);
        if (!authStateEntity.getCode().equals(AuthTypeVO.A0000.getCode())) {
            return authStateEntity;
        }

        // 3. 获取 Token 并返回
        Map<String, Object> chaim = new HashMap<>();
        chaim.put("openId", authStateEntity.getOpenId());
        String token = encode(authStateEntity.getOpenId(), 7 * 24 * 60 * 60 * 1000, chaim);
        authStateEntity.setToken(token);
        return authStateEntity;
    }

    /**
     * 验证码校验
     *
     * @param code 验证码
     * @return 鉴权结果
     */
    protected abstract AuthStateEntity checkCode(String code);

    /**
     * 这里就是产生jwt字符串的地方
     * jwt字符串包括三个部分
     * 1. header
     * -当前字符串的类型，一般都是“JWT”
     * -哪种算法加密，“HS256”或者其他的加密算法
     * 所以一般都是固定的，没有什么变化
     * 2. payload
     * 一般有四个最常见的标准字段（下面有）
     * iat：签发时间，也就是这个jwt什么时候生成的
     * jti：JWT的唯一标识
     * iss：签发人，一般都是username或者userId
     * exp：过期时间
     *
     * @param issuer    签发人
     * @param ttlMillis 有效时间
     * @param claims    想要在jwt中存储的一些非隐私信息
     * @return token
     */
    protected String encode(String issuer, long ttlMillis, Map<String, Object> claims) {
        if (claims == null) {
            claims = new HashMap<>();
        }
        long nowMillis = System.currentTimeMillis();
        JwtBuilder jwtBuilder = Jwts.builder()
                // 载荷部分
                .setClaims(claims)
                // JWT的唯一标识，通过UUID产生
                .setId(UUID.randomUUID().toString())
                // 签发时间
                .setIssuedAt(new Date(nowMillis))
                // 签发人，JWT是给谁的
                .setSubject(issuer)
                // jwt使用的算法和秘钥
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            jwtBuilder.setExpiration(exp);
        }

        return jwtBuilder.compact();
    }

    // 相当于encode的方向，传入jwtToken生成对应的username和password等字段。Claim就是一个map
    // 也就是拿到荷载部分所有的键值对

    /**
     * 获取token载荷部分
     * @param jwtToken token
     * @return Claim
     */
    protected Claims decode(String jwtToken) {
        // 得到 DefaultJwtParser
        return Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(base64EncodedSecretKey)
                // 设置需要解析的 jwt
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    /**
     * 判断token是否合法
     * @param jwtToken token
     * @return boolean
     */
    protected boolean isVerify(String jwtToken){
        try{
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(jwtToken);
            // 校验不通过会抛出异常
            // 判断合法的标准：1. 头部和荷载部分没有篡改过。2. 没有过期
            return true;
        }catch (Exception e){
            log.error("jwtToken isVerify err,jwtToken不合法,校验不通过",e);
            return false;
        }
    }

}


