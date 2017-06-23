package com.example.mateusmacedo.projetofinalmateus.Uteis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mateusmacedo.projetofinalmateus.ListarActivity;
import com.example.mateusmacedo.projetofinalmateus.R;
import com.example.mateusmacedo.projetofinalmateus.controller.PessoaController;
import com.example.mateusmacedo.projetofinalmateus.model.PessoaModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsitec101.macedo on 23/06/17.
 */

public class LinhaConsultarAdapter extends BaseAdapter {

    //CRIANDO UM OBJETO LayoutInflater PARA FAZER LINK A NOSSA VIEW(activity_linha_consultar.xml)
    private static LayoutInflater layoutInflater = null;

    //CRIANDO UMA LISTA DE PESSOAS
    List<PessoaModel> pessoaModels = new ArrayList<PessoaModel>();

    //CIRANDO UM OBJETO DA NOSSA CLASSE QUE FAZ ACESSO AO BANCO DE DADOS
    PessoaController pessoaController;

    //CRIANDO UM OBJETO DA NOSSA ATIVIDADE QUE CONTEM A LISTA
    private ListarActivity consultarActivity;

    //CONSTRUTOR QUE VAI RECEBER A NOSSA ATIVIDADE COMO PARAMETRO E A LISTA DE PESSOAS QUE VAI RETORNAR
    //DA NOSSA BASE DE DADOS
    public LinhaConsultarAdapter(ListarActivity consultarActivity, List<PessoaModel> pessoaModels) {

        this.pessoaModels = pessoaModels;
        this.consultarActivity = consultarActivity;
        this.layoutInflater = (LayoutInflater) this.consultarActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.pessoaController = new PessoaController(consultarActivity);
    }

    //RETORNA A QUANTIDADE DE REGISTROS DA LISTA
    @Override
    public int getCount() {

        return pessoaModels.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //ESSE MÉTODO SETA OS VALORES DE UM ITEM DA NOSSA LISTA DE PESSOAS PARA UMA LINHA DO NOSSO LISVIEW
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        //CRIANDO UM OBJETO DO TIPO View PARA ACESSAR O NOSSO ARQUIVO DE LAYOUT activity_row_consultar.xml
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_row_listar, null);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_row_consultar.xml) AOS OBJETOS DECLARADOS.

        //CAMPO QUE VAI MOSTRAR O NOME DA PESSOA
        TextView textViewNome = (TextView) viewLinhaLista.findViewById(R.id.textViewNome);

        //CAMPO QUE VAI MOSTRAR O CPF DA PESSOA
        TextView textViewCpf = (TextView) viewLinhaLista.findViewById(R.id.textViewCpf);

        //CAMPOS QUE VAI MOSTRAR A IDADE DA PESSOA
        TextView textViewIdade = (TextView) viewLinhaLista.findViewById(R.id.textViewIdade);

        //CAMPO QUE VAI MOSTRAR O TELEFONE
        TextView textViewTelefone = (TextView) viewLinhaLista.findViewById(R.id.textViewTelefone);

        //CAMPO QUE VAI MOSTRAR Email
        TextView textViewEmail = (TextView) viewLinhaLista.findViewById(R.id.textViewEmail);

        //CRIANDO O BOTÃO  EXCLUIR PARA DELETARMOS UM REGISTRO DO BANCO DE DADOS
        Button buttonExcluir = (Button) viewLinhaLista.findViewById(R.id.buttonExcluirRegistro);

        //CRIANDO O BOTÃO PARA EDITAR UM REGISTRO CADASTRADO
        Button buttonEditar = (Button) viewLinhaLista.findViewById(R.id.buttonEditar);

        //SETANDO O NOME NO CAMPO DA NOSSA VIEW
        textViewNome.setText(pessoaModels.get(position).getNome());

        //SETANDO O CPF NO CAMPO DA NOSSA VIEW
        textViewCpf.setText(String.valueOf(pessoaModels.get(position).getCpf()));

        //SETANDO A telefone
        textViewIdade.setText(String.valueOf(pessoaModels.get(position).getIdade()));

        //SETANDO A telefone
        textViewTelefone.setText(String.valueOf(pessoaModels.get(position).getTelefone()));

        //SETANDO A email
        textViewEmail.setText(pessoaModels.get(position).getEmail());


        //CRIANDO EVENTO CLICK PARA O BOTÃO DE EXCLUIR REGISTRO
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //EXCLUINDO UM REGISTRO
                pessoaController.excluir(pessoaModels.get(position).getCodigo());

                //MOSTRA A MENSAGEM APÓS EXCLUIR UM REGISTRO
                Toast.makeText(consultarActivity, "Registro excluido com sucesso!", Toast.LENGTH_LONG).show();

                //CHAMA O MÉTODO QUE ATUALIZA A LISTA COM OS REGISTROS QUE AINDA ESTÃO NA BASE
                atualizarLista();

            }
        });

        //CRIANDO EVENTO CLICK PARA O BOTÃO QUE VAI REDIRECIONAR PARA A TELA DE EDIÇÃO
        // DO REGISTRO.
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        return viewLinhaLista;
    }

    //ATUALIZA A LISTTA DEPOIS DE EXCLUIR UM REGISTRO
    public void atualizarLista(){

        this.pessoaModels.clear();
        this.pessoaModels = pessoaController.selecionarTodos();
        this.notifyDataSetChanged();
    }

}
