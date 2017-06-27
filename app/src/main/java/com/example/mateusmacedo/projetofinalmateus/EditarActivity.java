package com.example.mateusmacedo.projetofinalmateus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mateusmacedo.projetofinalmateus.Uteis.CpfMask;
import com.example.mateusmacedo.projetofinalmateus.Uteis.PhoneMask;
import com.example.mateusmacedo.projetofinalmateus.Uteis.Uteis;
import com.example.mateusmacedo.projetofinalmateus.controller.PessoaController;
import com.example.mateusmacedo.projetofinalmateus.model.PessoaModel;

/**
 * Created by Mateus Macedo on 23/06/17.
 */

public class EditarActivity extends AppCompatActivity {

    /*COMPONENTES DA TELA*/
    EditText editTextNome;
    EditText editTextCpf;
    EditText editTextIdade;
    EditText editTextTelefone;
    EditText editTextEmail;
    Button buttonAlterar;
    Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        //CHAMA O MÉTODO PARA CRIAR OS COMPONENTES DA TELA
        this.criarComponentes();

        //CHAMA O MÉTODO QUE CRIA EVENTOS PARA OS COMPONENTES
        this.criarEventos();


        //CARREGA OS VALORES NOS CAMPOS DA TELA.
        this.carregaValoresCampos();
    }

    //VINCULA OS COMPONENTES DA TELA(VIEW) AOS OBJETOS DECLARADOS.
    protected void criarComponentes() {

        editTextNome = (EditText) this.findViewById(R.id.editTextNomeEditar);
        editTextCpf = (EditText) this.findViewById(R.id.editTextCpfEditar);
        editTextCpf.addTextChangedListener(new CpfMask());
        editTextIdade = (EditText) this.findViewById(R.id.editTextIdadeEditar);
        editTextTelefone = (EditText) this.findViewById(R.id.editTextTelefoneEditar);
        editTextTelefone.addTextChangedListener(PhoneMask.insert(editTextTelefone));
        editTextEmail = (EditText) this.findViewById(R.id.editTextEmailEditar);
        buttonAlterar = (Button) this.findViewById(R.id.buttonAlterar);
        buttonVoltar = (Button) this.findViewById(R.id.buttonVoltar);

    }

    //MÉTODO CRIA OS EVENTOS PARA OS COMPONENTES
    protected  void criarEventos(){

        //CRIANDO EVENTO CLICK PARA O BOTÃO ALTERAR
        buttonAlterar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                alterar_onClick();
            }
        });

        //CRIANDO EVENTO CLICK PARA O BOTÃO VOLTAR
        buttonVoltar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    //ALTERA UM REGISTRO
    protected  void alterar_onClick(){

        //VALIDA SE OS CAMPOS ESTÃO VAZIOS ANTES DE ALTERAR O REGISTRO
        if(editTextNome.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.nome_obrigatorio));

            //FOCO NO CAMPO
            editTextNome.requestFocus();
        }
        else if(editTextCpf.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.cpf_obrigatorio));

            //FOCO NO CAMPO
            editTextCpf.requestFocus();
        }
        else if(editTextIdade.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.idade_obrigatorio));
            //FOCO NO CAMPO
            editTextIdade.requestFocus();
        }
        else if(editTextTelefone.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.telefone_obrigatorio));

            //FOCO NO CAMPO
            editTextTelefone.requestFocus();
        }
        else if(editTextEmail.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.email_obrigatorio));

            //FOCO NO CAMPO
            editTextEmail.requestFocus();
        }
        else{

            /*CRIANDO UM OBJETO PESSOA*/
            PessoaModel pessoaModel = new PessoaModel();

            Bundle extra =  this.getIntent().getExtras();
            int id_pessoa = extra.getInt("idPessoa");

            pessoaModel.setCodigo(id_pessoa);

            if (!pessoaModel.isValidEmail(editTextEmail.getText().toString())) {

                Uteis.Alert(this, this.getString(R.string.email_invalido));

                editTextEmail.requestFocus();

            } else if (!pessoaModel.isValidPhoneNumber(editTextTelefone.getText().toString())) {

                Uteis.Alert(this, this.getString(R.string.phone_invalido));

                editTextTelefone.requestFocus();

            } else if (!pessoaModel.isValidCpf(editTextCpf.getText().toString())){

                Uteis.Alert(this, this.getString(R.string.cpf_invalido));

                editTextCpf.requestFocus();

            } else {

            /*SETANDO O VALOR DO CAMPO NOME*/
                pessoaModel.setNome(editTextNome.getText().toString().trim());

                pessoaModel.setCpf(editTextCpf.getText().toString());

                pessoaModel.setTelefone(editTextTelefone.getText().toString());

                pessoaModel.setIdade(editTextIdade.getText().toString());

                pessoaModel.setEmail(editTextEmail.getText().toString().trim());


            /*ALTERANDO O REGISTRO*/
                new PessoaController(this).atualizar(pessoaModel);

            /*MENSAGEM DE SUCESSO!*/

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

                //ADICIONANDO UM TITULO A NOSSA MENSAGEM DE ALERTA
                alertDialog.setTitle(R.string.app_name);

                //MENSAGEM A SER EXIBIDA
                alertDialog.setMessage("Registro alterado com sucesso! ");

                //CRIA UM BOTÃO COM O TEXTO OK SEM AÇÃO
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        //RETORNA PARA A TELA DE CONSULTA
                        Intent intentRedirecionar = new Intent(getApplicationContext(), ListarActivity.class);

                        startActivity(intentRedirecionar);

                        finish();
                    }
                });

                //MOSTRA A MENSAGEM NA TELA
                alertDialog.show();
            }

        }

    }

    //CARREGA OS VALORES NOS CAMPOS APÓS RETORNAR DO SQLITE
    protected  void carregaValoresCampos(){

        PessoaController pessoaController = new PessoaController(this);

        //PEGA O ID PESSOA QUE FOI PASSADO COMO PARAMETRO ENTRE AS TELAS
        Bundle extra =  this.getIntent().getExtras();
        int id_pessoa = extra.getInt("idPessoa");

        //CONSULTA UMA PESSOA POR ID
        PessoaModel pessoaModel = pessoaController.getPessoa(id_pessoa);

        //SETA O NOME NA VIEW
        editTextNome.setText(pessoaModel.getNome());

        //SETA O CPF NA VIEW
        editTextCpf.setText(String.valueOf(pessoaModel.getCpf()));

        editTextIdade.setText(String.valueOf(pessoaModel.getIdade()));

        editTextTelefone.setText(String.valueOf(pessoaModel.getTelefone()));

        editTextEmail.setText(pessoaModel.getEmail());

    }

}
