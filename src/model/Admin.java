package model;

import java.util.List;

public class Admin {
    List userList;
    public Admin(){};
    public List getUserList (){
        return userList;
    }

    public void setUserList(List userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "service.Admin[" +
                "userList=" + userList +
                ']';
    }
}

