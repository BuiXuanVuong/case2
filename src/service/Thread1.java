package service;

import java.io.IOException;

public class Thread1 extends Thread{
    ProductService productService;

    public Thread1(){

    }

    public Thread1(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run() {
        synchronized (productService) {
            try {
                WriteReadFile.writeFile(ProductService.productList, ProductService.path);
                System.out.println("Write done!");
                productService.notifyAll();
                productService.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        synchronized (productService) {
            productService.notifyAll();
        }
    }

//    public static void main(String[] args) throws IOException {
//        service.ProductService productService = new service.ProductService();
//        T1 t1 = new T1(productService);
//        t1.start();
//    }
}

