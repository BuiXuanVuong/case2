package service;

import model.User;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login implements ILogin {
    public static String path = "user.txt";
    String regexAcount = "^\\w+(\\.\\w+)?@[a-zA-Z]+\\w+\\.com$|^admin\\w*";
    String regexPassword = "\\w+";
    Scanner sc = new Scanner(System.in);
    public static List<User> userList = new ArrayList<>();

    public Login () {
        userList.addAll((Collection) WriteReadFile.readFile(path));
    }

@Override
    public void createAccount() {
        String account = "";
        String password = "";

        Matcher matcher1;
        Matcher matcher2;
        do {
            System.out.println("Input new account of you!");
            account = sc.nextLine();
            Pattern pattern1 = Pattern.compile(regexAcount);
            matcher1 = pattern1.matcher(account);
            if(((matcher1.matches()==false) || (checkAccount(account)==true))) {
                System.out.println("Account you input is exists or wrong format account");
            }
        } while (((matcher1.matches() == false) || (checkAccount(account)==true)));
        do {
            System.out.println("Input password for account of you!");
            password = sc.nextLine();
            Pattern pattern2 = Pattern.compile(regexPassword);
            matcher2 = pattern2.matcher(password);
        } while (!matcher2.matches());
        User user = new User(account,password);
        userList.add(user);
    try {
        WriteReadFile.writeFile(userList,Login.path);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@Override
    public boolean checkAccount(String acount) {
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getAccount().equalsIgnoreCase(acount)) {
                return true;
            }
        }
        return false;
    }

@Override
    public boolean checkLogin(String account, String password) {
        for (User user : userList) {
            if(user.getAccount().equalsIgnoreCase(account) && user.getPassword().equalsIgnoreCase(password))
                return true;
        }
        return false;
    }
}

























