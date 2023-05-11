package controller;

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
        new CriaHashDoArquivo().criaHashDoArquivo("candidatos.txt");
        new VerificarIntegridade().verificarIntegridade("candidatos.txt");
        new CriaHashDoArquivo().criaHashDoArquivo("votos.txt");
        new VerificarIntegridade().verificarIntegridade("votos.txt");
        eleicao = new Eleicao();
        new TelaVotacao().setVisible(true);
    

    }

}
