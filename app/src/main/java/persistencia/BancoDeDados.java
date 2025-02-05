package persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados extends SQLiteOpenHelper {

    public BancoDeDados(Context context){
        super(context, "gerenciador", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        String sqlLivro = "CREATE TABLE livros (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT," +
                "autor TEXT," +
                "categoria TEXT);";
        bd.execSQL(sqlLivro);

        String sqlAvaliacao = "CREATE TABLE avaliacoes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_livro INTEGER," +
                "nota DOUBLE," +
                "comentario TEXT," +
                "FOREIGN KEY(id_livro) REFERENCES livros(id) ON DELETE CASCADE ON UPDATE CASCADE);";
        bd.execSQL(sqlAvaliacao);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("DROP TABLE IF EXISTS livros");
        bd.execSQL("DROP TABLE IF EXISTS avaliacoes");
    }
}
