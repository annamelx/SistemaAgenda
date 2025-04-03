package anna.ufpb.br.dcx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class AgendaKarol extends JFrame {

    private AgendaAnna agenda = new AgendaAnna();

    private JTextField nomeField = new JTextField(15);
    private JTextField diaField = new JTextField(2);
    private JTextField mesField = new JTextField(2);
    private JTextArea resultadoArea = new JTextArea(10, 30);

    public AgendaKarol() {
        super("Agenda de Contatos");

        JPanel painelCadastro = new JPanel();
        painelCadastro.setLayout(new GridLayout(4, 2));
        painelCadastro.add(new JLabel("Nome:"));
        painelCadastro.add(nomeField);
        painelCadastro.add(new JLabel("Dia:"));
        painelCadastro.add(diaField);
        painelCadastro.add(new JLabel("Mês:"));
        painelCadastro.add(mesField);

        JButton cadastrarBtn = new JButton("Cadastrar Contato");
        JButton pesquisarBtn = new JButton("Pesquisar Aniversariantes");
        JButton removerBtn = new JButton("Remover Contato");

        painelCadastro.add(cadastrarBtn);
        painelCadastro.add(pesquisarBtn);
        painelCadastro.add(removerBtn);

        JPanel painelResultado = new JPanel();
        resultadoArea.setEditable(false);
        painelResultado.add(new JScrollPane(resultadoArea));

        add(painelCadastro, BorderLayout.NORTH);
        add(painelResultado, BorderLayout.CENTER);

        cadastrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                int dia = Integer.parseInt(diaField.getText());
                int mes = Integer.parseInt(mesField.getText());

                if (agenda.cadastraContato(nome, dia, mes)) {
                    resultadoArea.setText("Contato cadastrado com sucesso!");
                } else {
                    resultadoArea.setText("Contato já existe.");
                }
            }
        });

        pesquisarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dia = Integer.parseInt(diaField.getText());
                int mes = Integer.parseInt(mesField.getText());

                Collection<Contato> aniversariantes = agenda.pesquisaAniversariantes(dia, mes);
                if (aniversariantes.isEmpty()) {
                    resultadoArea.setText("Nenhum aniversariante encontrado.");
                } else {
                    StringBuilder resultado = new StringBuilder("Aniversariantes:\n");
                    for (Contato c : aniversariantes) {
                        resultado.append(c).append("\n");
                    }
                    resultadoArea.setText(resultado.toString());
                }
            }
        });

        removerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                try {
                    if (agenda.removeContato(nome)) {
                        resultadoArea.setText("Contato removido com sucesso!");
                    }
                } catch (ContatoInexistenteException ex) {
                    resultadoArea.setText("Erro: " + ex.getMessage());
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new AgendaKarol();
    }
}

