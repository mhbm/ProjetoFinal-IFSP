package com.example.mateusmacedo.projetofinalmateus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mateusmacedo.projetofinalmateus.Uteis.MaskWatcher;
import com.example.mateusmacedo.projetofinalmateus.Uteis.Uteis;
import com.example.mateusmacedo.projetofinalmateus.controller.PessoaController;
import com.example.mateusmacedo.projetofinalmateus.model.PessoaModel;

public class InserirActivity extends AppCompatActivity {

    Button buttonVoltarInicio;
    Button buttonSalvarCadastro;
    Button buttonListar;

    EditText editTextNome;
    EditText editTextCpf;
    EditText editTextIdade;
    EditText editTextTelefone;
    EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        this.setarVariaveis();

        this.criarEventosBotoes();

    }

    protected void setarVariaveis() {
        this.editTextNome = (EditText) this.findViewById(R.id.nomePessoa);
        this.editTextCpf = (EditText) this.findViewById(R.id.cpf);
        this.editTextCpf.addTextChangedListener(MaskWatcher.buildCpf());
        this.editTextIdade = (EditText) this.findViewById(R.id.idade);
        this.editTextTelefone = (EditText) this.findViewById(R.id.telefone);
        this.editTextTelefone.addTextChangedListener(MaskWatcher.buildPhone());
        this.editTextEmail = (EditText) this.findViewById(R.id.email);
        buttonVoltarInicio = (Button) findViewById(R.id.btnvoltarinicio);
        buttonSalvarCadastro = (Button) findViewById(R.id.btninserir);
        buttonListar = (Button) findViewById(R.id.btnlistar);
    }

    protected void criarEventosBotoes() {

        buttonVoltarInicio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        buttonSalvarCadastro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Salvar_onClick();
            }
        });

        buttonListar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //REDIRECIONA PARA A TELA PRINCIPAL
                Intent intentMainActivity = new Intent(getApplicationContext(), ListarActivity.class);
                startActivity(intentMainActivity);
            }
        });


    }

    //VALIDA OS CAMPOS E SALVA AS INFORMAÇÕES NO BANCO DE DADOS
    protected void Salvar_onClick() {

        if (editTextNome.getText().toString().trim().equals("")) {

            Uteis.Alert(this, this.getString(R.string.nome_obrigatorio));

            editTextNome.requestFocus();
        } else if (editTextCpf.getText().toString().trim().equals("")) {

            Uteis.Alert(this, this.getString(R.string.cpf_obrigatorio));

            editTextCpf.requestFocus();

        } else if (editTextIdade.getText().toString().trim().equals("")) {

            Uteis.Alert(this, this.getString(R.string.idade_obrigatorio));

            editTextIdade.requestFocus();
        } else if (editTextTelefone.getText().toString().trim().equals("")) {

            Uteis.Alert(this, this.getString(R.string.telefone_obrigatorio));

            editTextTelefone.requestFocus();

        } else if (editTextEmail.getText().toString().trim().equals("")) {

            Uteis.Alert(this, this.getString(R.string.email_obrigatorio));

            editTextEmail.requestFocus();

        } else {

            /*CRIANDO UM OBJETO PESSOA*/
            PessoaModel pessoaModel = new PessoaModel();

            if (!pessoaModel.isValidEmail(editTextEmail.getText().toString())) {

                Uteis.Alert(this, this.getString(R.string.email_invalido));

                editTextEmail.requestFocus();

            } else if (!pessoaModel.isValidPhoneNumber(editTextTelefone.getText().toString())) {

                Uteis.Alert(this, this.getString(R.string.phone_invalido));

                editTextEmail.requestFocus();

            } else {

                /*SETANDO O VALOR DO CAMPO NOME*/
                pessoaModel.setNome(editTextNome.getText().toString().trim());

            /*SETANDO O CPF*/
                pessoaModel.setCpf(editTextCpf.getText().toString().trim());

            /*SETANDO O Idade*/
                pessoaModel.setIdade(editTextIdade.getText().toString().trim());

            /*SETANDO O Telefone*/
                pessoaModel.setTelefone(editTextTelefone.getText().toString().trim());

            /*SETANDO O Email*/
                pessoaModel.setEmail(editTextEmail.getText().toString().trim());



            /*SALVANDO UM NOVO REGISTRO*/
                new PessoaController(this).inserirCadastro(pessoaModel);

            /*MENSAGEM DE SUCESSO!*/
                Uteis.Alert(this, this.getString(R.string.registro_salvo_sucesso));

                LimparCampos();
            }


        }

    }

    //LIMPA OS CAMPOS APÓS SALVAR AS INFORMAÇÕES
    protected void LimparCampos() {

        editTextNome.setText(null);
        editTextCpf.setText(null);
        editTextIdade.setText(null);
        editTextTelefone.setText(null);
        editTextEmail.setText(null);
    }
}
