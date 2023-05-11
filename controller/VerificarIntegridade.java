package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class VerificarIntegridade {
    String hashArquivo = "";
    String hashValidador = "";




    public void verificarIntegridade(String arquivo) throws Exception {
        pegaHash(arquivo);
        hashArquivo = new CriaHashDoArquivo().criaHashDoArquivo(arquivo);
        if (hashArquivo.equals(hashValidador)) {
            System.out.println("Arquivo íntegro");
        } else {
            System.out.println("Arquivo corrompido"+arquivo);
        }
    }

    public void pegaHash(String arquivo) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("hash.txt"));
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (linha.contains(arquivo)) {
                String[] partes = linha.split(":");
                hashValidador = partes[1].trim();
            }
            
        }
    }


}
