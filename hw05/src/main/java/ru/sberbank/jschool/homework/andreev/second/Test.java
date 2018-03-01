package ru.sberbank.jschool.homework.andreev.second;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

    public static final String PATH_TO_CLASS_FOR_ENCRYPTION = "C:\\Users\\Mal\\IdeaProjects\\Homework\\hw05\\MyPlugins\\ForEncrypt.class";

    public static void main(String[] args) {
//        EncryptionClass();
        try {
            EncryptedClassLoader myClassLoader = new EncryptedClassLoader("MyPlugins", 3);
            Runnable forEncrypt = (Runnable) myClassLoader.findClass("ForEncrypt").newInstance();
            forEncrypt.run();
        } catch (MalformedURLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // hardcode method for encrypt *.class file once
    public static void EncryptionClass() {
        byte[] bytes = loadClassByte(PATH_TO_CLASS_FOR_ENCRYPTION);
        CryptoLibrary cL = new CryptoLibrary(3);
        byte[] encrypt = cL.encrypt(bytes);
        try (FileOutputStream fos = new FileOutputStream(PATH_TO_CLASS_FOR_ENCRYPTION)) {
            fos.write(encrypt);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] loadClassByte(String pathToClass) {
        byte[] result = null;
        Path path = Paths.get(pathToClass);
        try {
            result = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
