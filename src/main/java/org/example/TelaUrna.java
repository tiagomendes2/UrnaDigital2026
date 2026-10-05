package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaUrna extends JFrame {

    private String cpf;
    private String candidatoEscolhido;

    public TelaUrna() {

        setTitle("Urna Eletrônica");
        setSize(500, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();

        JPanel painelCPF = new JPanel();
        JPanel painelCandidatos = new JPanel();
        JPanel painelResultado = new JPanel();

        painelCPF.setLayout(new GridBagLayout());
        painelCandidatos.setLayout(new GridLayout(3, 2, 10, 10));
        painelResultado.setLayout(new BorderLayout());

        JLabel lblCPF = new JLabel("Digite seu CPF:");

        JTextField txtCPF = new JTextField();
        txtCPF.setPreferredSize(new Dimension(180, 30));

        JButton btnCPF = new JButton("Continuar");

        GridBagConstraints grid = new GridBagConstraints();

        grid.insets = new Insets(10, 10, 10, 10);

        grid.gridx = 0;
        grid.gridy = 0;
        painelCPF.add(lblCPF, grid);

        grid.gridx = 0;
        grid.gridy = 1;
        painelCPF.add(txtCPF, grid);

        grid.gridx = 0;
        grid.gridy = 2;
        painelCPF.add(btnCPF, grid);

        JButton btnGoku = new JButton("Goku");
        JButton btnVegeta = new JButton("Vegeta");
        JButton btnPiccolo = new JButton("Piccolo");
        JButton btnFreeza = new JButton("Freeza");
        JButton btnKuririn = new JButton("Kuririn");
        JButton btnBroly = new JButton("Broly");

        painelCandidatos.add(btnGoku);
        painelCandidatos.add(btnVegeta);
        painelCandidatos.add(btnPiccolo);
        painelCandidatos.add(btnFreeza);
        painelCandidatos.add(btnKuririn);
        painelCandidatos.add(btnBroly);

        JTextArea txtResultado = new JTextArea();
        txtResultado.setEditable(false);

        painelResultado.add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        abas.addTab("CPF", painelCPF);
        abas.addTab("Candidatos", painelCandidatos);
        abas.addTab("Resultado", painelResultado);

        abas.setEnabledAt(1, false);
        abas.setEnabledAt(2, false);

        add(abas);

        btnCPF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cpfDigitado = txtCPF.getText();

                if (cpfValido(cpfDigitado)) {

                    cpf = cpfDigitado;

                    abas.setEnabledAt(1, true);
                    abas.setSelectedIndex(1);

                } else {

                    JOptionPane.showMessageDialog(
                            TelaUrna.this,
                            "CPF inválido!"
                    );
                }
            }
        });

        btnGoku.addActionListener(e -> escolherCandidato("Goku", txtResultado, abas));
        btnVegeta.addActionListener(e -> escolherCandidato("Vegeta", txtResultado, abas));
        btnPiccolo.addActionListener(e -> escolherCandidato("Piccolo", txtResultado, abas));
        btnFreeza.addActionListener(e -> escolherCandidato("Freeza", txtResultado, abas));
        btnKuririn.addActionListener(e -> escolherCandidato("Kuririn", txtResultado, abas));
        btnBroly.addActionListener(e -> escolherCandidato("Broly", txtResultado, abas));
    }

    private void escolherCandidato(String candidato, JTextArea txtResultado, JTabbedPane abas) {

        candidatoEscolhido = candidato;

        boolean salvou = Votacao.salvar(cpf, candidato);

        if (salvou) {

            txtResultado.setText(
                    "VOTAÇÃO REGISTRADA\n\n" +
                            "CPF: " + cpf + "\n" +
                            "Candidato escolhido: " + candidato + "\n\n" +
                            "Candidatos:\n" +
                            "Goku\n" +
                            "Vegeta\n" +
                            "Piccolo\n" +
                            "Freeza\n" +
                            "Kuririn\n" +
                            "Broly"
            );

            abas.setEnabledAt(2, true);
            abas.setSelectedIndex(2);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível salvar a votação."
            );
        }
    }

    private boolean cpfValido(String cpf) {

        cpf = cpf.replace(".", "").replace("-", "");

        if (cpf.length() != 11) {
            return false;
        }

        for (int i = 0; i < cpf.length(); i++) {
            if (!Character.isDigit(cpf.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}