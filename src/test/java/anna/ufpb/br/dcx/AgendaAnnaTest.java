package anna.ufpb.br.dcx;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgendaAnnaTest {


    @Test
 public void testAgendaAnna()  throws ClassNotFoundException {
        //cadastrar contato
        Contato contato1 = new Contato();
        contato1.cadastraContato("Anna",12,2);
        //pesquisar Aniversariante
        Contato contato = contato1.pesquisaAniversariantes(12,2);
        assertEquals(12,contato.getDiaAniversario());
        assertEquals(2,contato.getMesAniversario());
        //remover Contato
        contato1.removerContato("anna");
        assertEquals("anna",contato1.getNome());
    }
}
