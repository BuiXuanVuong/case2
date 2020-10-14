package service;

import java.util.List;

public interface IProduct<T> {
     void  addProduct(T t);
     void editProduct(int id, T t);
     T deleteProduct(int id);
     T findById(int id);
     List<T> findByName(String s);
     List<T> findAll();
     void sortProduct(List<T> list);
}
