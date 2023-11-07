package com.example.exemplobancodados.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exemplobancodados.R;
import com.example.exemplobancodados.adapter.AlunoListAdapter;
import com.example.exemplobancodados.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import controller.AlunoController;

public class AlunoActivity extends AppCompatActivity {
    private FloatingActionButton btCadastroAluno;
    private AlertDialog dialog;
    private AlunoController controller;

    private EditText edRA;

    private EditText edNome;

    private View viewAlert;
    private RecyclerView rvAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        controller = new AlunoController(this);

        rvAlunos = findViewById(R.id.rvAlunos);

        btCadastroAluno = findViewById(R.id.btCadastroAluno);

        btCadastroAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastro();
            }
        });

        atualizarListaAluno();
    }

    private void abrirCadastro() {
        LayoutInflater inflater = dialog.getLayoutInflater();
        View view = getLayoutInflater().inflate(R.layout.dialog_cadastro_aluno, null);

        edRA = view.findViewById(R.id.edRA);
        edNome = view.findViewById(R.id.edNome);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CADASTRO DE ALUNO");
        builder.setView(view);
        builder.setCancelable(false);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                salvarDados();

            }
        });

        dialog = builder.create();
        dialog = builder.show();
    }

    public void salvarDados() {
        String retorno = controller.salvarAluno(edRA.getText().toString(), edNome.getText().toString());


        if (retorno != null) {
            if (retorno.contains("RA")) {
                edRA.setError(retorno);
            }
            if (retorno.contains("NOME")) {
                edNome.setError(retorno);
            }
        } else {
            Toast.makeText(this, "Aluno salvo com sucesso!", Toast.LENGTH_LONG).show();

            dialog.dismiss();

            atualizarListaAluno();
        }

    }

    private void atualizarListaAluno(){
        ArrayList<Aluno> listaAlunos = controller.retornarTodosAlunos();
        AlunoListAdapter adapter = new AlunoListAdapter(listaAlunos, this);
        rvAlunos.setLayoutManager(new LinearLayoutManager(this));
        rvAlunos.setAdapter(adapter);

    }
}