package anna.ufpb.br.dcx;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;

public class AgendaKarol extends JFrame {

    private Agenda agenda = new AgendaAnna();  // usamos AgendaAnna como lógica

    private JTextField nomeField = new JTextField(15);
    private JTextField diaField = new JTextField(2);
    private JTextField mesField = new JTextField(2);
    private JTextArea resultadoArea = new JTextArea(10, 30);

    public AgendaKarol() {
        super("Agenda de Contatos");

        JPanel painelCadastro = new JPanel();
        painelCadastro.setLayout(new GridLayout(6, 2));

        painelCadastro.add(new JLabel("Nome:"));
        painelCadastro.add(nomeField);
        painelCadastro.add(new JLabel("Dia:"));
        painelCadastro.add(diaField);
        painelCadastro.add(new JLabel("Mês:"));
        painelCadastro.add(mesField);

        JButton cadastrarBtn = new JButton("Cadastrar Contato");
        JButton pesquisarBtn = new JButton("Pesquisar Aniversariantes");
        JButton removerBtn = new JButton("Remover Contato");
        JButton salvarBtn = new JButton("Salvar Dados");
        JButton recuperarBtn = new JButton("Recuperar Dados");

        painelCadastro.add(cadastrarBtn);
        painelCadastro.add(pesquisarBtn);
        painelCadastro.add(removerBtn);
        painelCadastro.add(salvarBtn);
        painelCadastro.add(recuperarBtn);

        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        JPanel painelResultado = new JPanel();
        painelResultado.add(scrollPane);

        add(painelCadastro, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Ações dos botões
        cadastrarBtn.addActionListener(e -> {
            String nome = nomeField.getText();
            try {
                int dia = Integer.parseInt(diaField.getText());
                int mes = Integer.parseInt(mesField.getText());
                boolean sucesso = agenda.cadastraContato(nome, dia, mes);
                if (sucesso) {
                    resultadoArea.setText("Contato cadastrado com sucesso!");
                } else {
                    resultadoArea.setText("Contato já existe.");
                }
            } catch (NumberFormatException ex) {
                resultadoArea.setText("Dia e mês devem ser números.");
            }
        });

        pesquisarBtn.addActionListener(e -> {
            try {
                int dia = Integer.parseInt(diaField.getText());
                int mes = Integer.parseInt(mesField.getText());
                Collection<Contato> aniversariantes = agenda.pesquisaAniversariantes(dia, mes);
                if (aniversariantes.isEmpty()) {
                    resultadoArea.setText("Nenhum aniversariante encontrado.");
                } else {
                    StringBuilder sb = new StringBuilder("Aniversariantes:\n");
                    for (Contato c : aniversariantes) {
                        sb.append(c).append("\n");
                    }
                    resultadoArea.setText(sb.toString());
                }
            } catch (NumberFormatException ex) {
                resultadoArea.setText("Digite dia e mês válidos.");
            }
        });

        removerBtn.addActionListener(e -> {
            String nome = nomeField.getText();
            try {
                boolean removido = agenda.removeContato(nome);
                if (removido) {
                    resultadoArea.setText("Contato removido com sucesso!");
                }
            } catch (ContatoInexistenteException ex) {
                resultadoArea.setText("Erro: " + ex.getMessage());
            }
        });

        salvarBtn.addActionListener(e -> {
            try {
                agenda.salvarDados();
                resultadoArea.setText("Dados salvos com sucesso.");
            } catch (IOException ex) {
                resultadoArea.setText("Erro ao salvar dados: " + ex.getMessage());
            }
        });

        recuperarBtn.addActionListener(e -> {
            try {
                agenda.recuperarDados();
                resultadoArea.setText("Dados recuperados com sucesso.");
            } catch (IOException ex) {
                resultadoArea.setText("Erro ao recuperar dados: " + ex.getMessage());
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AgendaKarol();
    }
}


