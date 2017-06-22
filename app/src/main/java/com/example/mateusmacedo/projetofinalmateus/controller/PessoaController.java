package com.example.mateusmacedo.projetofinalmateus.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.mateusmacedo.projetofinalmateus.Uteis.DatabaseUtil;
import com.example.mateusmacedo.projetofinalmateus.model.PessoaModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsitec101.macedo on 22/06/17.
 */

public class PessoaController {
    DatabaseUtil databaseUtil;


    public PessoaController(Context context) {
        databaseUtil = new DatabaseUtil(context);
    }

    public void inserirCadastro (PessoaModel pessoaModel) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("nome", pessoaModel.getNome());
        contentValues.put("cpf", pessoaModel.getCpf());
        contentValues.put("idade", pessoaModel.getIdade());
        contentValues.put("telefone", pessoaModel.getTelefone());
        contentValues.put("email", pessoaModel.getEmail());

        databaseUtil.getConexaoDataBase().insert("pessoas",null,contentValues);
    }

    public List<PessoaModel> SelecionarTodos(){
        List<PessoaModel> pessoas = new ArrayList<PessoaModel>();

        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT      ");
        stringBuilderQuery.append("        nome,");
        stringBuilderQuery.append("        cpf,");
        stringBuilderQuery.append("        idade,");
        stringBuilderQuery.append("        telefone,");
        stringBuilderQuery.append("        email");
        stringBuilderQuery.append("FROM pessoas ");
        stringBuilderQuery.append("order by nome ");

        //CONSULTANDO OS REGISTROS CADASTRADOS
        Cursor cursor = databaseUtil.getConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        /*POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
        cursor.moveToFirst();

        PessoaModel pessoaModel;

        //REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR
        while (!cursor.isAfterLast()){

            /* CRIANDO UMA NOVA PESSOAS */
            pessoaModel =  new PessoaModel();

            //ADICIONANDO OS DADOS DA PESSOA
            pessoaModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            pessoaModel.setCpf(cursor.getInt(cursor.getColumnIndex("cpf")));
            pessoaModel.setIdade(cursor.getInt(cursor.getColumnIndex("idade")));
            pessoaModel.setTelefone(cursor.getInt(cursor.getColumnIndex("telefone")));
            pessoaModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));

            //ADICIONANDO UMA PESSOA NA LISTA
            pessoas.add(pessoaModel);

            //VAI PARA O PRÓXIMO REGISTRO
            cursor.moveToNext();
        }


        return pessoas;
    }


}