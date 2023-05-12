package model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.swing.JOptionPane;



public class Urna {

    public boolean validarEleitor(String cpf) {
        Eleitor eleitor = new Eleitor();
        
        Map<String, String> hashEleitores = Eleitor.carregarHashesDeArquivo();
        String hashEleitor = hashEleitores.get(cpf);
        if (hashEleitor == null) {
            System.out.println("CPF não cadastrado.");
            return false;
        }
        String senha = cpf;
        //String senha = JOptionPane.showInputDialog(null, "Insira o cpf do eleitor:");
        String hashCpf = eleitor.gerarHashSenha(senha);
        String hashSenha = eleitor.gerarHashSenha(hashCpf);
        if (hashEleitor.equals(hashCpf)) {            
            if(Eleitor.validarHash(hashSenha)){  
                System.out.println("Eleitor validado.");
                return true;
            }else {               
            System.out.println("Eleitor já votou");
            return false;
        }
    }else{
        System.out.println("Eleitor não cadastrado");
        return false;
    }
    }

    public boolean testarVulnerabilidades(){

        return false;
    }
}

