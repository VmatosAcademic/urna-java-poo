package controller;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

public class CriaHashDoArquivo {
    public String criaHashDoArquivo(String nomeArquivo) throws Exception {
        FileInputStream fis = new FileInputStream(nomeArquivo);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        Scanner scanner = new Scanner(new File("hash.txt"));
        boolean hashEncontrado = false;
        byte[] buffer = new byte[8192];
        int read;
        while ((read = fis.read(buffer)) > 0) {

            md.update(buffer, 0, read);
        }
        byte[] hash = md.digest();

        String hashBase64 = Base64.getEncoder().encodeToString(hash);
        
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if(linha.contains(nomeArquivo)){
                hashEncontrado = true;            
            }
        }
        if(hashEncontrado){
            fis.close();
            return hashBase64;
        }else{
        BufferedWriter writer = new BufferedWriter(new FileWriter("hash.txt", true));
        
        writer.newLine();
        writer.write(nomeArquivo+":"+hashBase64);

        fis.close();
        writer.close();
        }
        return hashBase64;
    }
}
