package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WriteReadFile {  // Method test Write and Read File.

    public static void main(String[] args) throws IOException {

        writeFile(ProductService.productList,ProductService.path);

//        Main.display(readFile());
    }

    public static void writeFile(List list, String path) throws IOException {
        File file = new File(path);
        isFileExists(path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fos.close();
            oos.close();
        }
    }

    public static List<Object> readFile(String path)  {
        ArrayList<Object> readList = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            readList = (ArrayList<Object>) ois.readObject();
//            System.out.println("test 1: " + readList);
        }catch (FileNotFoundException e) {
            try {
                WriteReadFile.isFileExists(path);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (EOFException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return readList;

    }

    public static boolean isFileExists(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()) {
            file.createNewFile();
            return false;
        }
        return true;
    }

}
