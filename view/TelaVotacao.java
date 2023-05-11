package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Eleicao;
import model.Candidato;
import model.CandidatoGenerico;

public class TelaVotacao extends JFrame {

    private static final long serialVersionUID = 1L;

    private Eleicao eleicao;

    private List<JLabel> labelsCandidatos;

    public TelaVotacao() {
        eleicao = new Eleicao();
        criarCandidatos();
        criarInterface();
    }

    private void criarCandidatos() {
        // Cria e adiciona os candidatos à eleição
        Candidato c1 = new CandidatoGenerico("João", "imagem.jpg");
        Candidato c2 = new CandidatoGenerico("Pedro", "imagem.jpg");
        Candidato c3 = new CandidatoGenerico("José", "imagem.jpg");
        eleicao.adicionarCandidato(c1);
        eleicao.adicionarCandidato(c2);
        eleicao.adicionarCandidato(c3);
    }

    private void criarInterface() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Eleição");

        // Cria um painel para os candidatos
        JPanel painelCandidatos = new JPanel(new GridLayout(3, 1, 10, 10));
        labelsCandidatos = new ArrayList<>();
        for (Candidato candidato : eleicao.getCandidatos()) {
            JLabel label = new JLabel(candidato.getNome(), SwingConstants.CENTER);
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    contabilizarVoto(candidato);
                }
            });
            painelCandidatos.add(label);
            labelsCandidatos.add(label);
        }

        // Cria um botão para exibir o resultado
        JButton botaoResultado = new JButton("Resultado");
        botaoResultado.addActionListener(e -> exibirResultado());

        // Cria um painel para o botão de resultado
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.add(botaoResultado);

        // Adiciona os painéis à janela
        add(painelCandidatos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Ajusta o tamanho da janela
        pack();
    }

    private void contabilizarVoto(Candidato candidato) {
        
        candidato.votar();
        atualizarLabelsCandidatos();
    }

    // private void salvarVoto(String nomeCandidato, int hashVoto) {
    //     try (PrintWriter pw = new PrintWriter(new FileWriter(Eleicao.NOME_ARQUIVO, true))) {
    //         pw.println(nomeCandidato + ":" + hashVoto);
    //     } catch (IOException e) {
    //         System.err.println("Erro ao salvar voto: " + e.getMessage());
    //     }
    // }

    private void atualizarLabelsCandidatos() {
        // for (JLabel label : labelsCandidatos) {
        //     label.setText(label.getText().split(":")[0] + ": " + eleicao.getVotos(label.getText().split(":")[0]));
        // }
    }

    private void exibirResultado() {
        eleicao.contabilizarVotos();
        eleicao.exibirResultado();
    }

    public static void main(String[] args) {
        new TelaVotacao().setVisible(true);
    }
}