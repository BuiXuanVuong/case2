package model;

import java.io.Serializable;

public class User implements Serializable {
    String account;
    String password;

    public User() {

    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return  password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        if((account.length()>=5) && (account.substring(0,5)).equalsIgnoreCase("admin") ) {
            return "Admin[" +
                    "account='" + account + '\'' +
                    ", password='" + password + '\'' +
                    ']';
        } else {
            return "User[" +
                    "account='" + account + '\'' +
                    ", password='" + password + '\'' +
                    ']';
        }
    }
}
