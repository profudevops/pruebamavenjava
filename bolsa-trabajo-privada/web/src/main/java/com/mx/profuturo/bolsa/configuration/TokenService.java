package com.mx.profuturo.bolsa.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.model.service.auth.response.UserDataVO;
import com.mx.profuturo.bolsa.security.GrantingUserSettings;
import com.mx.profuturo.bolsa.util.encrypter.Base64Util;
import com.mx.profuturo.bolsa.util.formatter.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TokenService {
	
	@Autowired
	FileUtil fileUtil;
	
	@Value("classpath:/files/tkn")
	private Resource tknResource;
	
    public static String TOKEN_SECRET = null;
    
    private String getTokenSecret() {
    	if(TOKEN_SECRET == null) {
    		this.setTokenSecretContent();
    	}
    	return TOKEN_SECRET;
    }
    
    private void setTokenSecretContent() {
    	
    	TOKEN_SECRET = fileUtil.getFileAsString(tknResource);
    	
    	
    }

    
    @Autowired
    private GrantingUserSettings userSettings;
    private long elapsedTimeInMinutes = 6;
    
    public String createToken(UserCredentialsVO userCredentialsVO, Boolean renewal) {
        try {
        	Long time = (renewal)? System.currentTimeMillis(): userCredentialsVO.getSessionTime();
            Algorithm algorithm = Algorithm.HMAC256(getTokenSecret());
            String token = JWT.create()
                    .withClaim("userId", userCredentialsVO.getId())
                    .withClaim("role", userCredentialsVO.getRole())
                    .withClaim("branch",userCredentialsVO.getBranch())
                    .withClaim("userFirstName", userCredentialsVO.getFirstName())
                    .withClaim("userLastName", userCredentialsVO.getFirstName())
                    .withClaim("idBranch", userCredentialsVO.getIdBranch())
                    .withClaim("createdAt", time)
                    .sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding messageF
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
            //log Token Signing Failed
        }
        return null;
    }
    
    
    public String createToken(UserCredentialsVO userCredentialsVO) {
    	
    	return this.createToken(userCredentialsVO, false);
    	
    }
    
    
  
    
    
    public Long getTokenRemainTime(String token) {
    	
    	UserCredentialsVO data = this.getUserFromToken(token);
    	Long time = data.getSessionTime() + (elapsedTimeInMinutes * (60*1000));
    	Long l = time - System.currentTimeMillis();
    	
    	//return (l - (60*1000));
    	//l = (long) (30*1000);
    	return l;
    }

    public String getUserIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(getTokenSecret());
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("userId").asString();
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding message
            return null;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            //log Token Verification Failed
            return null;
        }
    }

    public String getUserBranch(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(getTokenSecret());
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("branch").asString();
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding message
            return null;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            //log Token Verification Failed
            return null;
        }
    }

    public Integer getBranchId(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(getTokenSecret());
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("idBranch").asInt();
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding message
            return null;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            //log Token Verification Failed
            return null;
        }
    }

    public String getUserRoleFromToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(getTokenSecret());
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("role").asString();

        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding message
            return null;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            //log Token Verification Failed
            return null;
        }
    }

    public boolean isTokenValid(String token) {
        String userId = this.getUserIdFromToken(token);
        boolean isTokenUnderTolerance = this.isTokenUnderTolerance(token);
        return isTokenUnderTolerance;
    }


    //public boolean isTokenValid(String token){

//        return this.isUserFromTokenEqualsToPassword(token);
    //	return true;
    //}

    private boolean isUserFromTokenEqualsToUser(String token) {
        String userFromToken = "";
        userFromToken = getUserIdFromToken(token);
        return  userFromToken.equals(userSettings.getUser());
    }

    private boolean isUserFromTokenEqualsToPassword(String token) {
        String userFromToken = "";
        userFromToken = getUserIdFromToken(token);
        String storedPassword = userSettings.getPassword();
        return  userFromToken.equals(storedPassword);
    }


    //establece vigencia
    private boolean isTokenUnderTolerance(String token) {
        boolean result = false;
        Date tokenCreationDate;
        
        try {
            Algorithm algorithm = Algorithm.HMAC256(getTokenSecret());
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            Long tokenDate = jwt.getClaim("createdAt").asLong();
            Date tknDate = new Date(tokenDate);
            if(isDifferenceTimeUnderTolerance(tknDate,elapsedTimeInMinutes)){
                result = true;
            }

        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding message
            result =  false;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            //log Token Verification Failed
            result =  false;
        }
        return result;
    }

    private boolean isDifferenceTimeUnderTolerance(Date tokenCreationDate, long elapsedTimeInMinutes) {
        long tokenTimeInMilliseconds ;
        boolean result = false;
        tokenTimeInMilliseconds = tokenCreationDate.getTime();
        Date now = new Date();
        long currentTimeInMilliseconds = now.getTime();
        long differenceTimeInMinutes = (currentTimeInMilliseconds - tokenTimeInMilliseconds )/(60*1000);
        if(elapsedTimeInMinutes>differenceTimeInMinutes){
            result = true;
        }
        return result;
    }

    /*public String generatePassword(UserCredentialsVO userCredentialsVO) {

        StringBuilder pwd = new StringBuilder(userCredentialsVO.getId())
                .append(userCredentialsVO.getSessionTime());
        String password = SecureEncrypter.encrypt(pwd.toString());
        return Base64Util.base64Encode(password);
    }*/
    
    public UserCredentialsVO getUserFromToken(String token) {
        Timestamp t =  new Timestamp(System.currentTimeMillis());
    	UserCredentialsVO vo = new UserCredentialsVO();
    	
    	try {
            Algorithm algorithm = Algorithm.HMAC256(getTokenSecret());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            vo.setRole(jwt.getClaim("role").asString());
            vo.setBranch(jwt.getClaim("branch").asString());
            vo.setId(jwt.getClaim("userId").asString());
            vo.setFirstName(jwt.getClaim("userFirstName").asString());
            vo.setLastName(jwt.getClaim("userLastName").asString());
            vo.setSessionTime(t.getTime());
            vo.setSessionTime(jwt.getClaim("createdAt").asLong());
            vo.setIdBranch(jwt.getClaim("idBranch").asInt());
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding message
            return null;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            //log Token Verification Failed
            return null;
        }
    	
		return vo;
    }


	public String refreshToken(String token) {
		UserCredentialsVO vo = this.getUserFromToken(token);
		String newToken = this.createToken(vo, true);
		return newToken;
	}


}
