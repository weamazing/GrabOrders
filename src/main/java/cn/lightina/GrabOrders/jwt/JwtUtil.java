package cn.lightina.GrabOrders.jwt;

import cn.lightina.GrabOrders.pojo.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import javafx.util.Builder;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class JwtUtil {
    /*
    加密的密钥 只有服务端知道
     */
    private static final String salt="neverceasetocelebratelife";
    public String createToken(User user) throws UnsupportedEncodingException {
        Date nowTime=new Date();

        // TODO: 2018/5/2 设置过期时间
        Date expireTime=new Date();

        Map<String,Object>map=new HashMap<>();
        map.put("alg","HS256");
        map.put("typ","JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("userId",user.getUserId())
                .withExpiresAt(expireTime)
                .withIssuedAt(nowTime)
                .sign(Algorithm.HMAC256(salt));
        return token;
    }
    public Map<String,Claim>verifyToken(String token) throws UnsupportedEncodingException {
        JWTVerifier verifier=JWT.require(Algorithm.HMAC256(salt)).build();
        DecodedJWT jwt=null;
        try {
            jwt=verifier.verify(token);
        }catch (Exception e){
            throw new RuntimeException("登陆失败 请重新登录!");
        }
        return jwt.getClaims();
    }
}
