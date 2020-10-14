package service;

import model.Product;

import java.io.IOException;
import java.util.*;

public class ProductService implements IProduct<Product> {
    public static List<Product> productList = new ArrayList<>();
    int positionId;
    public static String path = "product.txt";
    public ProductService() throws IOException {
        productList.addAll((Collection) WriteReadFile.readFile(path));
    }


@Override
    public void addProduct(Product product) {
        if(!checkId(product.getId())) {
            productList.add(product);
        }
    }

@Override
    public void editProduct(int id, Product newProduct) {
        if(checkId(id)) {
            productList.set(positionId,newProduct);
        }
    }

@Override
    public Product deleteProduct(int id) {
        if(checkId(id)) {
            Product product;
            product = productList.get(positionId);
            productList.remove(positionId);
            return product;
        }
        return null;
    }

@Override
    public Product findById(int id) {
        if(checkId(id)) {
            return productList.get(positionId);
        }
        return null;
    }

@Override
    public List<Product> findByName(String name) {
        List<Product> nameList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getName().equalsIgnoreCase(name)) {
                nameList.add(productList.get(i));
            }
        }
        return nameList;
    }

@Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

@Override
    public void sortProduct(List<Product> list) {

        Collections.sort(list, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return (p1.getPrice() > p2.getPrice()) ? 1: -1;
            }
        });

    }

    public boolean checkId(int id) {
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            idList.add(productList.get(i).getId());
        }
        if(idList.contains(id)) {
            positionId = idList.indexOf(id);
            return true;
        } else {
            return false;
        }
    }

}










//static {
//        productList.add(new Product(1,"Sony ",1800,"Store2","False"));
//        productList.add(new Product(2,"Asus ",1500,"Store3","True "));
//        productList.add(new Product(3,"LG   ",1600,"Store4","False"));
//        productList.add(new Product(5,"Apple",1800,"Store5","True "));
//        productList.add(new Product(6,"Apple",1900,"Store6","True "));
//        }
