package model;

import java.io.Serializable;
import java.util.Scanner;

public class Product implements Serializable {
    private int id;
    private String name;
    private long price;
    private String store;
    private String statusExists;

    public Product() {

    }

    public Product(int id, String name, long price, String store, String statusExists) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.store = store;
        this.statusExists = statusExists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
    public String getStatusExists() {
        return statusExists;
    }

    public void setStatusExists(String statusExists) {
        this.statusExists = statusExists;
    }

    public Product inputProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input id product: ");
        id = sc.nextInt();
        sc.nextLine();
        System.out.println("Input name product: ");
        name = sc.nextLine();
        System.out.println("Input price product: ");
        price = sc.nextLong();
        sc.nextLine();
        System.out.println("Input store product: ");
        store = sc.nextLine();
        System.out.println("Input statusExists: ");
        statusExists = sc.nextLine();
        Product product = new Product(id,name,price,store,statusExists);
        return product;
    }

    @Override
    public String toString() {
        return  "Product[" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", store='" + store + '\'' +
                ", statusExists='" + statusExists + '\'' +
                ']';
    }
}
