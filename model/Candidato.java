package model;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Candidato {
    private String nome;
    private String imagem;
    private int votos;

    private static final String NOME_ARQUIVO = "votos.txt";

    public Candidato(String nome, String imagem) {
        this.nome = nome;
        this.imagem = imagem;
        this.votos = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

    public int getVotos() {
        return votos;
    }

    public void votar() {
        salvarVoto();
    }

    private void salvarVoto() {
        try {

            // Cria um objeto BufferedWriter para escrever os votos em um arquivo
            BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true));

            // Gera uma hash única para o voto usando o nome do candidato, a imagem e a quantidade de votos
            String hash = gerarHash(nome + imagem.toString());

            // Escreve a hash no arquivo
            writer.write(hash);
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

    public void contabilizarVotos() {
        try {
            // Cria um objeto BufferedReader para ler os votos do arquivo
            BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO));

            // Lê cada linha do arquivo e verifica se a hash corresponde ao candidato
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.equals(gerarHash(nome + imagem.toString()))) {
                    votos++;
                }
            }

            // Fecha o arquivo
            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler votos: " + e.getMessage());
        }
    }
}
