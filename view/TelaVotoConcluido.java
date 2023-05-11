package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaVotoConcluido extends JFrame {
    private static final long serialVersionUID = 1L;

    public TelaVotoConcluido() {
        super("Voto computado");
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Obrigado por votar! Seu voto foi computado.");
        label.setPreferredSize(new Dimension(400, 200));
        panel.setPreferredSize(new Dimension(600, 600));
        label.setHorizontalAlignment(JLabel.CENTER);
        
        panel.add(label);
        getContentPane().add(panel);
        
        JButton botaoNovoVoto = new JButton("Novo voto");
        botaoNovoVoto.addActionListener(e -> iniciarNovoVoto());

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.add(botaoNovoVoto);
        
        add(painelBotoes, BorderLayout.SOUTH);

        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void iniciarNovoVoto() {
        System.out.println("Novo voto");

        //TODO implementar a lógica para iniciar um novo voto
    }
}
