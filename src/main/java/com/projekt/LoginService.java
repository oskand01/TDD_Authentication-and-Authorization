package com.projekt;

public class LoginService {

    public LoginService() {
    }

    public Boolean login(String username, String password) {

        switch (username+password) {
            case "annalosen":
            case "berit123456":
            case "kallepassword":
                return true;
        }
        return false;
    }
}
