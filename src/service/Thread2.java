package service;

import model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Thread2 extends Thread {
    ProductService productService;
    public Thread2() {

    }

    public Thread2(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run() {

        synchronized (productService) {
            List<Product> listTemp = null;
            productService.notifyAll();
            try {
                productService.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            listTemp = (ArrayList) WriteReadFile.readFile(ProductService.path);
            Iterator<Product> iterator = listTemp.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
        System.out.println("Read done!");
        synchronized (productService) {
            productService.notifyAll();
        }
    }

    public static void main(String[] args) throws IOException {
        ProductService productService = new ProductService();
        Thread2 t2 = new Thread2(productService);
        t2.start();
    }
}

