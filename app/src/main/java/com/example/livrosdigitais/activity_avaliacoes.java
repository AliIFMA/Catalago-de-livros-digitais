package com.example.livrosdigitais;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import persistencia.Avaliacao;
import persistencia.Livro;

public class activity_avaliacoes extends AppCompatActivity {

    private EditText comentario, nota;
    private Spinner select;
    private Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_avaliacoes);

        this.comentario = findViewById(R.id.comentario);
        this.nota = findViewById(R.id.nota);
        this.select = findViewById(R.id.id_livro);

        livro = new Livro(this);

        carregarLivros();
    }

    public void carregarLivros(){
        List<Livro> livros = livro.buscarLivros();
        List<String> titulos = new ArrayList<>();
        for (Livro livro : livros) {
            titulos.add(livro.getTitulo());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, titulos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        select.setAdapter(adapter);
    }

    public void salvarAvaliacao(View v) {
        String notaStr = this.nota.getText().toString();
        String comentario = this.comentario.getText().toString();

        if (notaStr.isEmpty() || comentario.isEmpty()) {
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Preencha todos os campos, por favor");
            msg.setTitle("Envio de avaliacções");
            msg.setPositiveButton("Ok", null);
            msg.create();
            msg.show();
        }else{
            double nota = Double.parseDouble(notaStr);

            int posicao = select.getSelectedItemPosition();

            List<Livro> livros = livro.buscarLivros();
            int idLivro = livros.get(posicao).getId();

            Avaliacao avaliacao = new Avaliacao(this);
            avaliacao.setId_livro(idLivro);
            avaliacao.setNota(nota);
            avaliacao.setComentario(comentario);
            avaliacao.cadastrarAvaliacao();

            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Avaliação cadastrada com sucesso!");
            msg.setTitle("Envio de avaliações");
            msg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            msg.create();
            msg.show();
        }
    }
}