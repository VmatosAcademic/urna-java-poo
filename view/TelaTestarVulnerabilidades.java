package view;

import javax.swing.*;

import model.Urna;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaTestarVulnerabilidades extends JFrame {
    private JTextField tfUsuario;
    private JPasswordField pfSenha;

    public TelaTestarVulnerabilidades() {
        super("Liberar permissão");

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        painel.add(new JLabel("Usuário:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        tfUsuario = new JTextField(20);
        painel.add(tfUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        painel.add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pfSenha = new JPasswordField(20);
        painel.add(pfSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btEntrar = new JButton("Autenticar");
        btEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = tfUsuario.getText();
                char[] senha = pfSenha.getPassword();

                if (usuario.equals("mesario") && String.valueOf(senha).equals("123456")) {
                    // Cria e exibe a nova interface
                
                    JOptionPane.showMessageDialog(null, "Autenticado");
                    Urna urna = new Urna();
                    if(urna.testarVulnerabilidades()== false){

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
                }
            }
            
        });
        

        painel.add(btEntrar, gbc);

        add(painel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaTestarVulnerabilidades();
    }
}
