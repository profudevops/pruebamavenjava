package com.mx.profuturo.bolsa.service.auth;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.role.Branches;
import com.mx.profuturo.bolsa.model.service.auth.dto.SessionRegisterDTO;
import com.mx.profuturo.bolsa.model.service.auth.dto.UserAuthDTO;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.model.service.auth.response.UserDataVO;
import com.mx.profuturo.bolsa.model.service.sample.UserDataBean;
import com.mx.profuturo.bolsa.model.service.sample.UserInfoRequestBean;
import com.mx.profuturo.bolsa.model.service.sample.UserInfoResponseBean;
import com.mx.profuturo.bolsa.model.service.vacancies.dto.CrearVacanteDTO;
import com.mx.profuturo.bolsa.model.vo.common.PersonVO;
import com.mx.profuturo.bolsa.util.encrypter.Base64Util;
//import com.mx.profuturo.bolsa.util.encrypter.AESImpl;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.LoginErrorException;
import com.mx.profuturo.bolsa.util.formatter.FileUtil;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


public abstract class AuthServiceBase implements AuthService{

	@Autowired
	FileUtil fileUtil;
	
	@Value("classpath:/files/pwtkn")
	private Resource tknResource;
	
    private static  String SECRET_KEY = null;

    private static final String CIPHER_MODE = "AES/CBC/NoPadding";

    private static final byte[] initVector = new byte[16];
	
    @Override
    public UserCredentialsVO startUserSession(UserAuthDTO dto)throws LoginErrorException {
        UserCredentialsVO vo = this.authenticateUser(dto);
        vo.setSessionTime(System.currentTimeMillis());
        return vo;
    }

    @Override
    public UserDataVO getUserBasicData(UserCredentialsVO userCredentialsVO){
        UserDataVO userDataVO = new UserDataVO(userCredentialsVO);
        userDataVO.setRoleDescription("");
        userDataVO.setFullName(userCredentialsVO.getFirstName()+" "+userCredentialsVO.getLastName()+" "+userCredentialsVO.getSecondLastName());
        userDataVO.setBranch(userCredentialsVO.getBranch());
        userDataVO.setSystemSections(SystemSectionManager.getSystemSectionsByRole(userDataVO.getIdRole()));
        return userDataVO;
    }

    @Override
    public String registerSession(String id){
        SessionRegisterDTO dto = new SessionRegisterDTO();
        dto.setUserId(id);
        dto.setCreationTime(String.valueOf(new Date().getTime()));
        return __callRegisterSession(dto);
    }

    protected abstract String __callRegisterSession(SessionRegisterDTO dto);

    @Override
    public UserCredentialsVO authenticateUser(UserAuthDTO dto)throws LoginErrorException{
       /*
    	String pass = null;
		try {
			pass = decoder.desencriptar(dto.getPassword(), "CIPHERKEY");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	dto.setPassword(pass);
    	*/
    	RequestBean<UserAuthDTO> genericRequestBean = new RequestBean();
        genericRequestBean.setPayload(dto);

        UserCredentialsVO response =  this.__callAuthenticateUser(genericRequestBean);
        response = this.completeUserInfo(response);
        return response;

    }

    protected abstract UserCredentialsVO __callAuthenticateUser(RequestBean<UserAuthDTO> genericRequestBean)throws LoginErrorException;

    /*

        ALEX implementacion

     */

    @Override
    public UserDataBean authUser(String userDate) throws GenericStatusException {
        UserInfoResponseBean response  = getUserInfoResponse(userDate);
        UserDataBean userDataBean = response.getData();
        return userDataBean;
    }


    private UserInfoResponseBean getUserInfoResponse(String userDate) throws GenericStatusException {
        UserInfoRequestBean userInfoRequestBean = new UserInfoRequestBean();
        userInfoRequestBean.setIdClienteUnico(Integer.parseInt(userDate));
        RequestBean<UserInfoRequestBean> genericRequestBean = new RequestBean();
        genericRequestBean.setPayload(userInfoRequestBean);
        return callAuthService(genericRequestBean);
    }

    private UserCredentialsVO completeUserInfo(UserCredentialsVO userInfo){
        if(userInfo.getFirstName().indexOf("|")>-1)
        {
            String[] nameParts = userInfo.getFirstName().split("\\|");
            userInfo.setFirstName(nameParts[0]);
            userInfo.setLastName(nameParts[1]);
            userInfo.setSecondLastName(nameParts[2]);
        }
            String[] branchFromRole = userInfo.getRole().split("_");
        if(branchFromRole[1].equals("CRP") && branchFromRole[2].equals("ATN")){
            userInfo.setBranch(Branches.CUSTOMER_ATTENTION);
            userInfo.setIdBranch(Branches.CLIENTSID);
        }else
        if (branchFromRole[1].equals("CRP") || branchFromRole[1].equals("MKT")) {
            userInfo.setBranch(Branches.CORPORATE);
            userInfo.setIdBranch(Branches.CORPORATEID);
        }else if(branchFromRole[1].equals("COM")){
            userInfo.setBranch(Branches.COMMERCIAL);
            userInfo.setIdBranch(Branches.COMMERCIALID);
        }

        return userInfo;

    }

    @Override
    public String decryptPassword(String encryptedPwd){
        String password = null;
        try{
            SecretKeySpec secretKey = new SecretKeySpec(new BigInteger(getSecretKey(), 16).toByteArray(), "AES");
            IvParameterSpec iv = new IvParameterSpec(initVector);
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] plainText = cipher.doFinal(completeBlocks(Base64.decodeBase64(encryptedPwd)));
            password = new String(plainText, "UTF-8");
            password = password.replace("\u0000", "");
        }catch(Exception e){
            //e.printStackTrace();
        }
        return password;
    }
    
    
    private String getSecretKey() {
    	if(null == SECRET_KEY) {
    		SECRET_KEY = fileUtil.getFileAsString(tknResource);
    	}
    	return SECRET_KEY;
    }
    
 
    private byte[] completeBlocks(byte[] message) {
        try {
            int bytesLenght = message.length;
            if (bytesLenght % 16 != 0) {
                byte[] newArray = new byte[bytesLenght + (16 - (bytesLenght % 16))];
                System.arraycopy(message, 0, newArray, 0, bytesLenght);
                return newArray;
            }
            return message;
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return null;
    }


    protected abstract UserInfoResponseBean callAuthService(RequestBean<UserInfoRequestBean> genericRequestBean) throws GenericStatusException;
    public abstract boolean validateUserPatterns(UserAuthDTO dto) throws LoginErrorException;
}

