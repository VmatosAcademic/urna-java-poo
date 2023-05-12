package controller;

import java.io.FileNotFoundException;

import view.TelaVotacao;

public class Controller {

    public void Controller() {
    }

    public void iniciar() throws Exception {
        Eleicao eleicao = new Eleicao();
        // DefinirCandidatos definirCandidatos = new DefinirCandidatos();
        // TelaVotacao telaVotacao = new TelaVotacao();
        //TODO: verificar hash da pagina de candidato
        //TODO: verificar hash da pagina de votos
        //new CriaHashDoArquivo().criaHashDoArquivo("candidatos.txt"); //Descomente para adicionar um hash ao arquivo
        eleicao = new Eleicao();
        System.out.println("Criando candidatos");
        // definirCandidatos.criarCandidatos(eleicao);
        new TelaVotacao().setVisible(true);
    

    }

}
