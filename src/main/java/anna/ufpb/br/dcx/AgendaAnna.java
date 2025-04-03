package anna.ufpb.br.dcx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class AgendaAnna implements Agenda {
    private GravadorDeDados gravadorDeDados = new GravadorDeDados();
    private HashMap<String, Contato> contatos = new HashMap<>();

    @Override
    public boolean cadastraContato(String nome, int dia, int mes) {
        if (this.contatos.containsKey(nome)) {
            return false;
        } else {
            Contato contato = new Contato(dia, mes, nome);
            this.contatos.put(nome, contato);
            return true;
        }
    }

    @Override
    public Collection<Contato> pesquisaAniversariantes(int dia, int mes) {
        List<Contato> aniversariantes = new ArrayList<>();
        for (Contato contato : this.contatos.values()) {
            if (contato.getDiaAniversario() == dia && contato.getMesAniversario() == mes) {
                aniversariantes.add(contato);
            }
        }
        return aniversariantes;
    }

    @Override
    public boolean removeContato(String nome) throws ContatoInexistenteException {
        if (this.contatos.containsKey(nome)) {
            this.contatos.remove(nome);
            return true;
        } else {
            throw new ContatoInexistenteException("Contato não existe!");
        }
    }

    @Override
    public void salvarDados() throws IOException {
        gravadorDeDados.salvarContato(this.contatos.values());
    }

    @Override
    public void recuperarDados() throws IOException {
        Collection<Contato> contatosRecuperados = gravadorDeDados.recuperarContatos();
        for (Contato c : contatosRecuperados) {
            this.contatos.put(c.getNome(), c);
        }
    }
}
