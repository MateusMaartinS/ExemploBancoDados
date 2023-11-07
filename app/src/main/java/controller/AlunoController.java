package controller;

import android.content.Context;

import com.example.exemplobancodados.dao.AlunoDao;
import com.example.exemplobancodados.model.Aluno;

import java.util.ArrayList;

public class AlunoController {

    private Context context;

    public AlunoController(Context context){
        this.context = context;
    }

    public String salvarAluno(String ra, String nome){
        try{
            if (ra.equals("") || ra.isEmpty()){
                return "informe o RA do aluno!";
            }
            if(nome.equals("") || ra.isEmpty()){
                return "informe o NOME do aluno!";
            }

            Aluno aluno = AlunoDao.getInstancia(context).getById(Integer.parseInt(ra));

            if (aluno != null){
                return "O RA ("+ra+") já está cadastrado!";
            }else{
                aluno = new Aluno();
                aluno.setRa(Integer.parseInt(ra));
                aluno.setNome(nome);

                AlunoDao.getInstancia(context).insert(aluno);
            }

        }catch (Exception ex){
            return "Erro ao Gravar Aluno";
        }
        return null;
    }

    public ArrayList<Aluno> retornarTodosAlunos(){
       return AlunoDao.getInstancia(context).getAll();

    }
}
