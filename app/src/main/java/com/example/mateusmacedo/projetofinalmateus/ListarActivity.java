package com.example.mateusmacedo.projetofinalmateus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.mateusmacedo.projetofinalmateus.Uteis.LinhaConsultarAdapter;
import com.example.mateusmacedo.projetofinalmateus.controller.PessoaController;
import com.example.mateusmacedo.projetofinalmateus.model.PessoaModel;

import java.util.List;

public class ListarActivity extends AppCompatActivity {

    //CRIANDO UM OBJETO DO TIPO ListView PARA RECEBER OS REGISTROS DE UM ADAPTER
    ListView listViewPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        //VINCULANDO O LISTVIEW DA TELA AO OBJETO CRIADO
        listViewPessoas = (ListView)this.findViewById(R.id.listViewPessoas);

        //CHAMA O MÉTODO QUE CARREGA AS PESSOAS CADASTRADAS NA BASE DE DADOS
        this.carregarPessoasCadastradas();

    }

    //MÉTODO QUE CONSULTA AS PESSOAS CADASTRADAS
    protected  void carregarPessoasCadastradas(){

        PessoaController pessoaRepository =  new PessoaController(this);

        //BUSCA AS PESSOAS CADASTRADAS
        List<PessoaModel> pessoas = pessoaRepository.selecionarTodos();

        //SETA O ADAPTER DA LISTA COM OS REGISTROS RETORNADOS DA BASE
        listViewPessoas.setAdapter(new LinhaConsultarAdapter(this, pessoas));

    }



}
