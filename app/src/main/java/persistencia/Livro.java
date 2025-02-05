package persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private static Livro livro;

    private SQLiteOpenHelper bancoHelper;
    private SQLiteDatabase banco;

    private int id;
    private String titulo;
    private String autor;
    private String categoria;

    public Livro(Context context){
        bancoHelper = new BancoDeDados(context);
    }

    public void cadastarLivro(){
        banco = bancoHelper.getWritableDatabase();

        ContentValues conteudo = new ContentValues();
        conteudo.put("titulo", this.titulo);
        conteudo.put("autor", this.autor);
        conteudo.put("categoria", this.categoria);

        banco.insert("livros", null, conteudo);
        banco.close();
    }

    public List<Livro> buscarLivros(){
        banco = bancoHelper.getReadableDatabase();
        List<Livro> listarLivros = new ArrayList<>();

        Cursor cursor = banco.rawQuery("SELECT id, titulo FROM livros", null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()){
                Livro livro = new Livro(null);
                livro.setId(cursor.getInt(0));
                livro.setTitulo(cursor.getString(1));
                listarLivros.add(livro);
            }
        }
        cursor.close();
        banco.close();
        return listarLivros;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
