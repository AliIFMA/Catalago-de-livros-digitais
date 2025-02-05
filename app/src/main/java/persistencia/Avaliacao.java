package persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Avaliacao {
    private static Avaliacao avaliacao;

    private SQLiteOpenHelper bancoHelper;
    private SQLiteDatabase banco;

    private int id_livro;
    private double nota;
    private String comentario;

    public Avaliacao(Context context){
        bancoHelper = new BancoDeDados(context);
    }

    public void cadastrarAvaliacao(){
        banco = bancoHelper.getWritableDatabase();

        ContentValues conteudo = new ContentValues();
        conteudo.put("id_livro", this.id_livro);
        conteudo.put("nota", this.nota);
        conteudo.put("comentario", this.comentario);

        banco.insert("avaliacoes", null, conteudo);
        banco.close();
    }

    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
