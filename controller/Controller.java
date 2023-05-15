package controller;

import view.Resultado;
import view.TelaLogin;
import view.TelaPrincipal;
import view.TelaValidacao;
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
        new CriaHashDoArquivo().criaHashDoArquivo("hashesEleitores.txt");
        new VerificarIntegridade().verificarIntegridade("hashesEleitores.txt");
        eleicao = new Eleicao();
        new TelaLogin();
        // new TelaPrincipal();
        // new TelaValidacao();
        // new TelaVotacao().setVisible(true);
    

    }

}
