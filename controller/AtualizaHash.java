package controller;

import java.io.*;
import java.util.*;

public class AtualizaHash {
    String novaHash="";

    public void atualizaHash(String arquivo) throws Exception{

        try {
            novaHash = new CriaHashDoArquivo().criaHashDoArquivo(arquivo);
            Scanner scanner = new Scanner(new File("hash.txt"));

            List<String> linhas = new ArrayList<>();
            
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if(!linha.contains(arquivo)){
                    linhas.add(linha);
                }
            }
            File arquivoHashAntigo = new File("hash.txt");
            arquivoHashAntigo.delete();

            File arquivoHashNovo = new File("hash.txt");
            // FileWriter writer = new FileWriter(arquivoHashNovo);
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoHashNovo, true));

            for (String novaLinha : linhas) {
                writer.write(novaLinha);
                writer.newLine();
            }

            writer.write(arquivo + ":" + novaHash);
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
