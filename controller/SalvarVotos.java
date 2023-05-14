package controller;

import java.io.*;
import java.security.*;

import model.Candidato;

public class SalvarVotos {
    


    public void votar(String nome, Candidato candidato) {
        salvarVoto(nome, candidato);
    }

    public void salvarVoto(String nome, Candidato candidato) {
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("votos.txt", true));

            String hash = gerarHash(nome + candidato.getNome());

            // Escreve a hash no arquivo
            writer.write(nome + "," + candidato.getNome() + "," + hash);
            writer.newLine();

            // Fecha o arquivo
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar voto: " + e.getMessage());
        }
    }

    private String gerarHash(String texto) {
        try {
            // Cria um objeto MessageDigest para gerar a hash
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Converte o texto para bytes e calcula a hash
            md.update(texto.getBytes());
            byte[] hash = md.digest();

            // Converte a hash para uma string hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Erro ao gerar hash: " + e.getMessage());
            return null;
        }
    }
}
