package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Urna;

public class TelaValidacao extends JFrame {
public TelaValidacao() throws FileNotFoundException {
            String cpf = JOptionPane.showInputDialog(null, "Insira o cpf do eleitor:");
                Urna urna = new Urna();
                if (urna.validarEleitor(cpf)) {
                    // Se o eleitor for validado com sucesso, exibe a tela de votação
                    TelaVotacao votacaoUI = new TelaVotacao(cpf);
                    votacaoUI.setVisible(true);
                    dispose();
                    
                } else {
                    // Caso contrário, exibe uma mensagem de erro
                    JOptionPane.showMessageDialog(null, "Eleitor não cadastrado ou já votou!");
                    new TelaPrincipal();                  
                }
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        


    public static void main(String[] args) throws FileNotFoundException {
        TelaValidacao validacaoUI = new TelaValidacao();
    }
}
