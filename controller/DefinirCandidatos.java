package controller;


import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import model.Candidato;
import model.CandidatoGenerico;

public class DefinirCandidatos {

    private List<Candidato> candidatos;

    public void DefinirCandidatos() {
        candidatos = new ArrayList<>();
    }
    
    public void carregarCandidatos(String arquivo) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(arquivo));
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] partes = linha.split(":");
            String nome = partes[0].trim();
            Icon imagem = new ImageIcon(partes[1].trim());
            Candidato candidato = new CandidatoGenerico(nome, imagem);
            candidatos.add(candidato);
        }
        scanner.close();
    }

    public void criarCandidatos(Eleicao eleicao) {
        // Cria e adiciona os candidatos à eleição
        
        Candidato c1 = new CandidatoGenerico("Kaguya", new ImageIcon("candidato1.jpg"));
        Candidato c2 = new CandidatoGenerico("Vladilena", new ImageIcon("candidato2.jpg"));
        Candidato c3 = new CandidatoGenerico("Komi", new ImageIcon("candidato3.jpg"));
        


        eleicao.adicionarCandidato(c1);
        eleicao.adicionarCandidato(c2);
        eleicao.adicionarCandidato(c3);
        System.out.println("Candidatos criados");
    }
}
