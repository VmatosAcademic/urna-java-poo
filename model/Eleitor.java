package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;



public class Eleitor {
    private String cpf;
    private String hashEleitor;
    

    public Eleitor() {
        
    }

    public boolean cadastrarEleitor(String cpf){
        try{
            if (cpfJaCadastrado(cpf)) {
                System.out.println("CFP já cadastrado");
                return false;
            }else{
            this.cpf = cpf;
            this.hashEleitor = gerarHashAleatoria();
            salvarHashEmArquivo();
            
            return true;
            }
        }catch(Exception e){
            System.out.println("CPF já cadastrado");
            return false;
        }
    }

    public static boolean hashJavotada(String hash) {
        Map<String, String> hashEleitores = carregarHashesDeHashes();
        return hashEleitores.containsKey(hash);
    }

    public static Map<String, String> carregarHashesDeHashes() {
        Map<String, String> hashEleitores = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File("hashesVotados.txt"));
            while (scanner.hasNextLine()) {
                String[] linha = scanner.nextLine().split(":");
                hashEleitores.put(linha[0], linha[1]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo hashesEleitores.txt não encontrado.");
        }
        return hashEleitores;
    }

    public boolean jaVotou(String hashCpf){
        try{
            if (hashJavotada(hashCpf)) {
                System.out.println("ja votou");
                return false;
            }else{
            
            this.hashEleitor = gerarHashAleatoria();
            salvarHashEmArquivo();
            
            return true;
            }
        }catch(Exception e){
            System.out.println("CPF já cadastrado");
            return false;
        } 
    }
  
    

    public String getCpf() {
        return cpf;
    }

    public String getHashEleitor() {
        return hashEleitor;
    }

    public String gerarHashAleatoria() {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(cpf.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02X", b));
            }
            hash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Erro ao gerar hash: " + e.getMessage());
        }
        return hash;
    }

    public String gerarHashSenha(String senha) {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(senha.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02X", b));
            }
            hash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Erro ao gerar hash: " + e.getMessage());
        }
        return hash;
    }

    private void salvarHashEmArquivo() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("hashesEleitores.txt", true));
            writer.write(cpf + ":" + hashEleitor);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar hash em arquivo: " + e.getMessage());
        }
    }

    public static Map<String, String> carregarHashesDeArquivo() {
        Map<String, String> hashEleitores = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File("hashesEleitores.txt"));
            while (scanner.hasNextLine()) {
                String[] linha = scanner.nextLine().split(":");
                hashEleitores.put(linha[0], linha[1]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo hashesEleitores.txt não encontrado.");
        }
        return hashEleitores;
    }

    public static boolean cpfJaCadastrado(String cpf) {
        Map<String, String> hashEleitores = carregarHashesDeArquivo();
        return hashEleitores.containsKey(cpf);

    }
    public static boolean validarVotos(String hash) {
        try {
            File votosFile = new File("votos.txt");
            if (!votosFile.exists()) {
                votosFile.createNewFile();
            }
    
            BufferedReader br = new BufferedReader(new FileReader(votosFile));
            String hashVoto;
            while ((hashVoto = br.readLine()) != null) {
                if (hashVoto==hash) {
                    System.out.println("Eleitor já votou.");
                    br.close();
                    return false;
                }
            }
            br.close();
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao validar votos: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean validarHash(String hash) {
        File file = new File("hashesVotados.txt");
        boolean hashExiste = false;

        try {
            // Verifica se o arquivo existe
            if (!file.exists()) {
                file.createNewFile();
            } else {
                // Procura pelo hash no arquivo
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.equals(hash)) {
                        hashExiste = true;
                        break;
                    }
                }
                scanner.close();
            }

            // Se o hash não existe no arquivo, adiciona o hash e retorna true
            if (!hashExiste) {
                FileWriter writer = new FileWriter(file, true);
                writer.write(hash + "\n");
                writer.close();
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Se o hash já existe no arquivo, retorna false
        return false;
    }

}