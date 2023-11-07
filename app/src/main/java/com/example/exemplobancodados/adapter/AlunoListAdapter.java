package com.example.exemplobancodados.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.exemplobancodados.R;
import com.example.exemplobancodados.model.Aluno;

public class AlunoListAdapter extends RecyclerView.Adapter<AlunoListAdapter.ViewHolder> {

    private ArrayList<Aluno> listaAlunos;
    private Context context;

    public AlunoListAdapter(ArrayList<Aluno> listaAlunos, Context context) {
        this.listaAlunos = listaAlunos;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_aluno, parent, false);


        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Aluno alunoselecionado = listaAlunos.get(position);

        holder.tvRa.setText(String.valueOf(alunoselecionado.getRa()));
        holder.tvNome.setText(alunoselecionado.getNome());

    }

    @Override
    public int getItemCount() {
        return this.listaAlunos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvRa;
        public TextView tvNome;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvRa = itemView.findViewById(R.id.tvRA);
            this.tvNome = itemView.findViewById(R.id.tvNome);
        }
    }
}
