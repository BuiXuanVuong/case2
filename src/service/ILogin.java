package service;

public interface ILogin {
    void createAccount();
    boolean checkAccount(String ac);
    boolean checkLogin(String ac, String ps);
}
