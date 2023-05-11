package controller;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import model.Candidato;
import model.CandidatoGenerico;


public class Eleicao {

    private List<Candidato> candidatos;

    public Eleicao() {
        candidatos = new ArrayList<>();
    }

    public void adicionarCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }

    public Candidato getCandidato(String nome) {
        for (Candidato candidato : candidatos) {
            if (candidato.getNome().equals(nome)) {
                return candidato;
            }
        }
        return null;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void contabilizarVotos() {
        for (Candidato candidato : candidatos) {
            candidato.contabilizarVotos();
        }
    }

    public static void main(String[] args) {
        Eleicao eleicao = new Eleicao();

        // Adiciona alguns candidatos
        Candidato candidato1 = new CandidatoGenerico("João", "imagem.jpg");
        Candidato candidato2 = new CandidatoGenerico("Maria", "imagem.jpg");
        eleicao.adicionarCandidato(candidato1);
        eleicao.adicionarCandidato(candidato2);

        Candidato candidato = eleicao.getCandidato("Maria"); // Supondo que existe um método getCandidato que retorna um candidato pelo nome
        candidato.votar();
    

        // Contabiliza os votos
        eleicao.contabilizarVotos();

        // Exibe a quantidade de votos para cada candidato
        for (Candidato candidatoVotos : eleicao.getCandidatos()) {
            System.out.println(candidatoVotos.getNome() + ": " + candidatoVotos.getVotos());
        }
    }

}
