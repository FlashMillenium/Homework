package ru.sberbank.jschool.homework.andreev.second;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EncryptedClassLoader extends URLClassLoader {
    String rootDirectory;
    CryptoLibrary cryptoLibrary;

    public EncryptedClassLoader(String rootDirectory, int offset) throws MalformedURLException {
        super(createUrlFromString(rootDirectory));
        this.rootDirectory = rootDirectory;
        this.cryptoLibrary = new CryptoLibrary(offset);
    }

    private static URL[] createUrlFromString(String rootDirectory) throws MalformedURLException {
        URL[] rootUrl = new URL[1];
        rootUrl[0] = new File(System.getProperty("user.dir") + File.separator + rootDirectory + File.separator).toURI().toURL();
        return rootUrl;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String pathToClass = System.getProperty("user.dir") + File.separator + rootDirectory + File.separator + name.replace('.', '/').concat(".class");
        byte[] encryptedClassByte = loadClassByte(pathToClass);
        byte[] decryptedClassByte = cryptoLibrary.decrypt(encryptedClassByte);
        Class<?> result = defineClass(name, decryptedClassByte, 0, decryptedClassByte.length);
        return result;
    }

    private byte[] loadClassByte(String pathToClass) {
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
