package com.example.livrosdigitais;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import persistencia.Livro;

public class MainActivity extends AppCompatActivity {

    private EditText titulo;
    private EditText autor;
    private EditText categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.titulo = findViewById(R.id.titulo);
        this.autor = findViewById(R.id.autor);
        this.categoria = findViewById(R.id.categoria);
    }

    public void cadastrar(View v){
        Livro livro = new Livro(this);
        String titulo = this.titulo.getText().toString();
        String autor = this.autor.getText().toString();
        String categoria = this.categoria.getText().toString();

        if (titulo.isEmpty() || autor.isEmpty() || categoria.isEmpty()) {
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Preencha todos os campos, por favor");
            msg.setTitle("Cadastro de livros");
            msg.setPositiveButton("Ok", null);
            msg.create();
            msg.show();
        }else{
            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setCategoria(categoria);

            livro.cadastarLivro();

            this.titulo.setText("");
            this.autor.setText("");
            this.categoria.setText("");

            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Livro cadastrado com sucesso!");
            msg.setTitle("Cadastro de livros");
            msg.setPositiveButton("Ok", null);
            msg.create();
            msg.show();

        }
    }

    public void telaAvaliacao(View v){
        Intent telaAvaliacao = new Intent(this, activity_avaliacoes.class );
        startActivity(telaAvaliacao);
    }
}