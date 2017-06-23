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

    public void inserirCadastro(PessoaModel pessoaModel) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("nome", pessoaModel.getNome());
        contentValues.put("cpf", pessoaModel.getCpf());
        contentValues.put("idade", pessoaModel.getIdade());
        contentValues.put("telefone", pessoaModel.getTelefone());
        contentValues.put("email", pessoaModel.getEmail());

        databaseUtil.getConexaoDataBase().insert("pessoas", null, contentValues);

    }

    public void resetarBanco(){
        databaseUtil.onUpgrade(databaseUtil.getConexaoDataBase(),1,1);
    }

    public void atualizar(PessoaModel pessoaModel){

        ContentValues contentValues =  new ContentValues();

        /*MONTA OS PARAMENTROS PARA REALIZAR UPDATE NOS CAMPOS*/
        contentValues.put("nome", pessoaModel.getNome());
        contentValues.put("cpf", pessoaModel.getCpf());
        contentValues.put("idade", pessoaModel.getIdade());
        contentValues.put("telefone", pessoaModel.getTelefone());
        contentValues.put("email", pessoaModel.getEmail());

        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/

        databaseUtil.getConexaoDataBase().update("pessoas", contentValues, "idPessoa = ?", new String[]{Integer.toString(pessoaModel.getCodigo())});
    }

    public Integer excluir(int codigo) {
        return databaseUtil.getConexaoDataBase().delete("pessoas","idPessoa = ?", new String[]{Integer.toString(codigo)});
    }

    public PessoaModel getPessoa(int codigo) {


        Cursor cursor = databaseUtil.getConexaoDataBase().rawQuery("SELECT * FROM pessoas WHERE idPessoa= " + codigo, null);

        cursor.moveToFirst();

        ///CRIANDO UMA NOVA PESSOAS
        PessoaModel pessoaModel = new PessoaModel();

        //ADICIONANDO OS DADOS DA PESSOA
        pessoaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("idPessoa")));
        pessoaModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        pessoaModel.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
        pessoaModel.setIdade(cursor.getString(cursor.getColumnIndex("idade")));
        pessoaModel.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
        pessoaModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));

        //RETORNANDO A PESSOA
        return pessoaModel;

    }

    public List<PessoaModel> selecionarTodos() {
        List<PessoaModel> pessoas = new ArrayList<PessoaModel>();

        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT      ");
        stringBuilderQuery.append("        idPessoa,");
        stringBuilderQuery.append("        nome,");
        stringBuilderQuery.append("        cpf,");
        stringBuilderQuery.append("        idade,");
        stringBuilderQuery.append("        telefone,");
        stringBuilderQuery.append("        email  ");
        stringBuilderQuery.append("FROM pessoas  ");
        stringBuilderQuery.append("order by nome ");

        //CONSULTANDO OS REGISTROS CADASTRADOS
        Cursor cursor = databaseUtil.getConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        /*POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
        cursor.moveToFirst();

        PessoaModel pessoaModel;

        //REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR
        while (!cursor.isAfterLast()) {

            /* CRIANDO UMA NOVA PESSOAS */
            pessoaModel = new PessoaModel();

            //ADICIONANDO OS DADOS DA PESSOA
            pessoaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("idPessoa")));
            pessoaModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            pessoaModel.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
            pessoaModel.setIdade(cursor.getString(cursor.getColumnIndex("idade")));
            pessoaModel.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            pessoaModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));

            //ADICIONANDO UMA PESSOA NA LISTA
            pessoas.add(pessoaModel);

            //VAI PARA O PRÓXIMO REGISTRO
            cursor.moveToNext();
        }

        return pessoas;
    }


}
