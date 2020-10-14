import model.Product;
import service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static void display(List<Product> products){
        Iterator<Product> iterator = products.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ProductService productService = new ProductService();
        Login login = new Login();
        Product product = new Product();
        Scanner sc = new Scanner(System.in);
        String account;
        String password;


        do {
            System.out.println("---------Choose create account or login account--------");
            System.out.println("1.Create account");
            System.out.println("2.Login");
            System.out.println("0.Exit");
            System.out.println("-------------------------------------------------------\n");

            System.out.println("--------------------Your choice(0-1)-------------------");
            int chooseBegin = sc.nextInt();
            sc.nextLine();
            System.out.println("-------------------------------------------------------");

            switch (chooseBegin) {
                case 1:
                    System.out.println("---------------------Create account--------------------");
                    login.createAccount();
                    System.out.println("-------------------------------------------------------");
                    break;
                case 0:
                    System.out.println("---------------------Exit program----------------------");
                    System.exit(0);
                case 2:
                    if(Login.userList.size() == 0) {
                        System.out.println("No has account in list. Create account!");
                        login.createAccount();
                    }
                    do {
                        System.out.println("\n-------------------------Login-------------------------");
                        System.out.println("Input account ");
                        account = sc.nextLine();
                        System.out.println("Input password ");
                        password = sc.nextLine();

                        if (login.checkLogin(account,password)==false) {
                            System.out.println("You input wrong account or password. Input again!");
                        }
                        System.out.println("-------------------------------------------------------\n");
                    } while (login.checkLogin(account,password)==false);

                    System.out.println("----------------------Login success--------------------");
                    String regexLogin = "^admin\\w*";
                    Pattern pattern = Pattern.compile(regexLogin);
                    Matcher matcher = pattern.matcher(account);

                    if (matcher.find()) {
                        List userList = new ArrayList();
                        userList = (ArrayList) WriteReadFile.readFile(Login.path);
                        System.out.println("1.Display list all admin and user:");
                        System.out.printf("0.Exit\n");
                        System.out.println("-------------------------------------------------------");

                        do {
                            System.out.println("\n--------------------Your choice(0-1)-------------------");
                            int chooseAdmin = sc.nextInt();
                            sc.nextLine();
                            System.out.println("-------------------------------------------------------\n");
                            switch (chooseAdmin) {
                                case 1:
                                    System.out.println("-------------------List all in company-----------------");
                                    display(userList);
                                    System.out.println("-------------------------------------------------------");
                                    break;
                                case 0:
                                    System.out.println("---------------------Exit program----------------------");
                                    System.exit(0);
                            }

                        } while (true);


                    } else {
                        System.out.println("1. Add new product");
                        System.out.println("2. Edit product");
                        System.out.println("3. Delete product");
                        System.out.println("4. Find product by id");
                        System.out.println("5. Find product by name");
                        System.out.println("6. Find all product");
                        System.out.println("7. Sort by price");
                        System.out.println("8. Update file");
                        System.out.println("0. Exit");
                        System.out.println("-------------------------------------------------------");

                        do {
                            System.out.println("\n--------------------Your choice(0-8)-------------------");
                            int choose = sc.nextInt();
                            sc.nextLine();
                            System.out.println("-------------------------------------------------------\n");
                            switch (choose) {
                                case 1:
                                    System.out.println("---------------------Add new product-------------------");
                                    Product product1 = product.inputProduct();
                                    System.out.println(product1.getId());
                                    if (productService.checkId(product1.getId())) {

                                        System.out.println("Id product is exists! ");
                                        System.out.println("-------------------------------------------------------");
                                        break;
                                    }
                                    productService.addProduct(product1);
                                    WriteReadFile.writeFile(ProductService.productList, ProductService.path);
                                    System.out.println("-------------------------------------------------------");
                                    break;
                                case 2:
                                    System.out.println("----------------------Edit product---------------------");
                                    if (ProductService.productList.size() == 0) {
                                        System.out.println("No product in list!");
                                        System.out.println("-------------------------------------------------------");
                                        break;
                                    }

                                    System.out.println("Input id for update");
                                    int id = sc.nextInt();
                                    Product product2 = product.inputProduct();
                                    productService.editProduct(id, product2);
                                    if (!productService.checkId(id)) {
                                        System.out.println("No found product");
                                        System.out.println("-------------------------------------------------------");
                                        break;
                                    }
                                    WriteReadFile.writeFile(ProductService.productList, ProductService.path);
                                    System.out.println("-------------------------------------------------------");
                                    break;

                                case 3:
                                    System.out.println("---------------------Delete product--------------------");
                                    if (ProductService.productList.size() == 0) {
                                        System.out.println("No product in list!");
                                        System.out.println("-------------------------------------------------------");
                                        break;
                                    }
                                    System.out.println("Input id for delete");
                                    id = sc.nextInt();
                                    if (!productService.checkId(id)) {
                                        System.out.println("No found product");
                                        System.out.println("-------------------------------------------------------");
                                        break;
                                    }
                                    System.out.println(productService.deleteProduct(id));
                                    WriteReadFile.writeFile(ProductService.productList, ProductService.path);
                                    System.out.println("-------------------------------------------------------");
                                    break;
                                case 4:
                                    System.out.println("-------------------Find product by id------------------");
                                    if (ProductService.productList.size() == 0) {
                                        System.out.println("No product in list!");
                                        System.out.println("-------------------------------------------------------");
                                        break;
                                    }
                                    System.out.println("Input id for look");
                                    id = sc.nextInt();
                                    sc.nextLine();
                                    if (productService.findById(id) != null) {
                                        System.out.println("Information product has id " + id);
                                        System.out.println(productService.findById(id));
                                    } else {
                                        System.out.println("No found product");
                                        System.out.println("-------------------------------------------------------");
                                        break;
                                    }
                                    break;
                                case 5:
                                    System.out.println("------------------Find product by name-----------------");
                                    if (ProductService.productList.size() == 0) {
                                        System.out.println("No product in list!");
                                        System.out.println("-------------------------------------------------------");
                                        break;
                                    }
                                    System.out.println("Input name for look");
                                    String name = sc.nextLine();
                                    if (productService.findByName(name) != null) {
                                        display(productService.findByName(name));
                                    } else {
                                        System.out.println("No found name product");
                                        System.out.println("-------------------------------------------------------");
                                        break;
                                    }
                                    System.out.println("-------------------------------------------------------");
                                    break;
                                case 6:
                                    System.out.println("--------------------Find all product-------------------");
                                    if (ProductService.productList.size() == 0) {
                                        System.out.println("No product in list");
                                        System.out.println("-------------------------------------------------------");
                                        break;
                                    }
                                    display(productService.findAll());
                                    System.out.println("-------------------------------------------------------");
                                    break;
                                case 7:
                                    System.out.println("------------------Sort price product-------------------");
                                    if (ProductService.productList.size() == 0) {
                                        System.out.println("No product in list");
                                        System.out.println("-------------------------------------------------------");
                                        break;
                                    }
                                    List<Product> productListTemp = new ArrayList<>(ProductService.productList);
                                    productService.sortProduct(productListTemp);
                                    display(productListTemp);
                                    System.out.println("-------------------------------------------------------");
                                    break;

                                case 8:
                                    System.out.println("------------------------Update-------------------------");
                                    Thread1 t1 = new Thread1(productService);
                                    Thread2 t2 = new Thread2(productService);
                                    t2.start();
                                    t1.start();
                                    t2.join();
                                    t1.join();
                                    System.out.println("Complete update file!");
                                    System.out.println("-------------------------------------------------------");
                                    break;
                                case 0:
                                    System.out.println("---------------------Exit program----------------------");
                                    System.exit(0);
                                    System.out.println("-------------------------------------------------------");
                                    break;
                            }
                        } while (true);
                    }
            }
        } while(true);
    }
}

