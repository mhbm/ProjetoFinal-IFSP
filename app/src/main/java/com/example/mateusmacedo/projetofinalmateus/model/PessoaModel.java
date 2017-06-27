package com.example.mateusmacedo.projetofinalmateus.model;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by lsitec101.macedo on 22/06/17.
 */

public class PessoaModel {
    private Integer codigo;
    private String nome;
    private String cpf;
    private String idade;
    private String telefone;
    private String email;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValidEmail(CharSequence email) {
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }

    public boolean isValidPhoneNumber(CharSequence phoneNumber) {
        if (phoneNumber.length() == 9 || phoneNumber.length() == 10 || phoneNumber.length() == 13 || phoneNumber.length() == 14) {
            return true;
        }
        return false;
    }
}
