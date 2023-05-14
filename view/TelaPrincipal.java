package view;

import model.Eleitor;

import java.io.*;
import java.awt.*;
import javax.swing.*;



public class TelaPrincipal extends JFrame {
    public TelaPrincipal() {
        super("Tela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu eleitoresMenu = new JMenu("Eleitores");
        menuBar.add(eleitoresMenu);

        JMenuItem cadastrarEleitorMenuItem = new JMenuItem("Cadastrar Eleitor");
        eleitoresMenu.add(cadastrarEleitorMenuItem);
        cadastrarEleitorMenuItem.addActionListener(e -> {
            String cpf = JOptionPane.showInputDialog(null, "Digite o CPF do eleitor a ser cadastrado:");
            if(cpf != null && !cpf.trim().isEmpty()) {
                Eleitor eleitor = new Eleitor();
                boolean cadastrado = eleitor.cadastrarEleitor(cpf);
                if(cadastrado) {
                    JOptionPane.showMessageDialog(null, "Eleitor cadastrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "CPF já cadastrado.");
                }                
        }});

        JButton botaoNovoVoto = new JButton("Novo voto");
        botaoNovoVoto.addActionListener(e -> {
            try {
                dispose();
                TelaValidacao validacaoUI = new TelaValidacao();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.add(botaoNovoVoto);
        add(painelBotoes, BorderLayout.SOUTH);
        //JMenuItem listarEleitoresMenuItem = new JMenuItem("Listar Eleitores");
        //eleitoresMenu.add(listarEleitoresMenuItem);
        //listarEleitoresMenuItem.addActionListener(e -> listarEleitores());

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    

    private void listarEleitores() {
        // aqui você pode implementar a lógica para listar os eleitores cadastrados
        JOptionPane.showMessageDialog(this, "Listar eleitores");
    }

    public static void main(String[] args) {
        new TelaPrincipal();
    }
}


