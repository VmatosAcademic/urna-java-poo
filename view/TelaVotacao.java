package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Eleicao;
import model.Candidato;
import model.CandidatoGenerico;

public class TelaVotacao extends JFrame {
    int linhas = 0;
    private static final long serialVersionUID = 1L;

    private Eleicao eleicao;

    private List<JLabel> labelsCandidatos;

    public TelaVotacao() throws FileNotFoundException {
        eleicao = new Eleicao();
        contarLinhas();
        criarCandidatos();
        criarInterface(eleicao);
    }

    private void criarCandidatos() throws FileNotFoundException{
        // Cria e adiciona os candidatos à eleição
            Scanner scanner = new Scanner(new File("candidatos.txt"));
            for(int i = 0; i < linhas-1; i++) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(":");
                String nome = partes[0].trim();
                Icon imagem = new ImageIcon(partes[1].trim());
                Candidato candidato = new CandidatoGenerico(nome, imagem);
                eleicao.adicionarCandidato(candidato);
            }
            scanner.close();

        // Candidato c1 = new CandidatoGenerico("Kaguya", new ImageIcon("candidato1.jpg"));
        // Candidato c2 = new CandidatoGenerico("Vladilena", new ImageIcon("candidato2.jpg"));
        // Candidato c3 = new CandidatoGenerico("Komi", new ImageIcon("candidato3.jpg"));
        // eleicao.adicionarCandidato(c1);
        // eleicao.adicionarCandidato(c2);
        // eleicao.adicionarCandidato(c3);
    }

    public void contarLinhas() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("candidatos.txt"));
        
        while (scanner.hasNextLine()) {
            linhas++;
            scanner.nextLine();
        }
        scanner.close();
        System.out.println("Linhas: " + linhas);
    }

    public void criarInterface(Eleicao eleicao) {
        
        System.out.println("Criando interface");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Eleição");

        System.out.println(eleicao.getCandidatos().size());

        // Cria um painel para os candidatos
        JPanel painelCandidatos = new JPanel(new GridLayout(3, 1, 10, 10));
        labelsCandidatos = new ArrayList<>();
        for (Candidato candidato : eleicao.getCandidatos()) {
            System.out.println("Criando candidato " + candidato.getNome()+" "+candidato.getImagem());
            JLabel label = new JLabel(candidato.getNome(), candidato.getImagem(), SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(400, 400));
            // label.setHorizontalAlignment(JLabel.CENTER);
            // label.setVerticalAlignment(JLabel.LEFT);
            // label.setIcon(new ImageIcon(imagem.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT)));
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
        System.out.println("Interface criada");
    }

    private void contabilizarVoto(Candidato candidato) {
        
        eleicao.votar(candidato);
        atualizarLabelsCandidatos();
    }


    private void atualizarLabelsCandidatos() {
        TelaVotacao.this.setVisible(false);
        new TelaVotoConcluido();
    }

    private void exibirResultado() {
        eleicao.contabilizarVotos();
        eleicao.exibirResultado();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new TelaVotacao().setVisible(true);
    }
}