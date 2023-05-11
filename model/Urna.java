package model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.swing.JOptionPane;



public class Urna {

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

    public boolean validarEleitor(String cpf) {
        Eleitor eleitor = new Eleitor();
        
        Map<String, String> hashEleitores = Eleitor.carregarHashesDeArquivo();
        String hashEleitor = hashEleitores.get(cpf);
        if (hashEleitor == null) {
            System.out.println("CPF não cadastrado.");
            return false;
        }
        String senha = JOptionPane.showInputDialog(null, "Insira o cpf do eleitor:");
        String hashSenha = eleitor.gerarHashSenha(senha);
        if (hashEleitor.equals(hashSenha)) {
            System.out.println("Eleitor validado.");
            return true;
        } else {
            System.out.println("Eleitor não cadastrado.");
            return false;
        }
    }
}

