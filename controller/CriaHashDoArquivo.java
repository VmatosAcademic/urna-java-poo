package controller;

import java.security.MessageDigest;
import java.util.Base64;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class CriaHashDoArquivo {
    public void criaHashDoArquivo(String nomeArquivo) throws Exception {
        FileInputStream fis = new FileInputStream(nomeArquivo);
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] buffer = new byte[8192];
        int read;
        while ((read = fis.read(buffer)) > 0) {
            md.update(buffer, 0, read);
        }
        byte[] hash = md.digest();

        String hashBase64 = Base64.getEncoder().encodeToString(hash);

        BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true));
        
        writer.newLine();
        writer.write((hashBase64).getBytes().toString());
        

        fis.close();
        writer.close();
    }
}
