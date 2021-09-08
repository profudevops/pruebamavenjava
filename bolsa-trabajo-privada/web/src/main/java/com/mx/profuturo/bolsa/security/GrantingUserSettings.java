package com.mx.profuturo.bolsa.security;

import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.util.exception.custom.AuthenticationException;
import org.apache.commons.collections.bag.HashBag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public final class GrantingUserSettings {
    private static final Logger LOGGER = LoggerFactory.getLogger(GrantingUserSettings.class);

    private String idUsuario;
    private String password;
    private String role;
    private String branch;

    public GrantingUserSettings(){
        /**default constructor*/
    }


    public void setUser(UserCredentialsVO userCredentialsVO) {
        this.idUsuario = userCredentialsVO.getId();
        this.password = userCredentialsVO.getPassword();
        this.role = userCredentialsVO.getRole();
        this.branch = userCredentialsVO.getBranch();
        Collection<GrantedAuthority> grantedAuthorities = new HashBag();
        grantedAuthorities.add(new SimpleGrantedAuthority(this.role));
        UsernamePasswordAuthenticationToken authentication = new
                UsernamePasswordAuthenticationToken(idUsuario,
                this.password, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LOGGER.info("Permisos asignados.");
    }




    public void setUser(String idUsuario) {
        this.setUser(new UserCredentialsVO(idUsuario, "Rol", "password"));
    }



    public String getUser(){
        return this.idUsuario;
    }

    public String getPassword(){
        return this.password;
    }

    public String getRole() {
        return role;
    }


	public String getBranch() {
		return branch;
	}


	public void setBranch(String branch) {
		this.branch = branch;
	}

   
    
}
