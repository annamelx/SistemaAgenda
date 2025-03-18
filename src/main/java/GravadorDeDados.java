import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class GravadorDeDados {
    private String arquivoDados;

    public GravadorDeDados(){
        this.arquivoDados = "conttos.dat";
    }

    public Collection<Contato> recuperarContatos() throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoDados))) {
            Collection<Contato>contatoesAchados = (ArrayList<Contato>)in.readObject();
            return contatoesAchados;
        }catch (ClassNotFoundException  | ClassCastException e ) {
            throw new IOException(e);
        }

        }
    }

    public void gravaContato(Collection<Contato> contatos) throws IOException{
          ArrayList<Contato> contatoArrayList = new ArrayList<>();
          contatoArrayList.addAll(contatos);
          try (ObjectInputStream out = new ObjectOutputStream(new FileOutputStream(arquivoDados))){
              out.writeObject(contatoArrayList);

          }


}
