/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.auth;

import static com.anmpout.fcddashboard.utils.Utils.addDetailMessage;
import com.github.adminfaces.template.config.AdminConfig;
import com.github.adminfaces.template.session.AdminSession;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Faces;

/**
 *
 * @author anmpout
 */
@Named
@SessionScoped
@Specializes
public class LogonMB extends AdminSession implements Serializable {

    private String currentUser;
    private String email;
    private String password;
    private boolean remember;
    @Inject
    private AdminConfig adminConfig;


    public void login() throws IOException {
        currentUser = email;
        //addDetailMessage("Logged in successfully as <b>" + email + "</b>");
        Faces.getExternalContext().getFlash().setKeepMessages(true);
        Faces.redirect(adminConfig.getIndexPage());
    }

    @Override
    public boolean isLoggedIn() {

        return currentUser != null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
