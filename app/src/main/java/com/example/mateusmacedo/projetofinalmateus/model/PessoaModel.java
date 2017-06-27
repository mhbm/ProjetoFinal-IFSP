package com.example.mateusmacedo.projetofinalmateus.model;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by Mateus Macedo on 22/06/17.
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

    // CPF
    private static final int[] weightSsn = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    private int calculate(String str, final int[] weight) {
        int sum = 0;
        for (int i = str.length() - 1, digit; i >= 0; i--) {
            digit = Integer.parseInt(str.substring(i, i + 1));
            sum += digit * weight[weight.length - str.length() + i];
        }
        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }

    public boolean isValidCpf(String ssn) {

        ssn = ssn.replaceAll("\\.", "");
        ssn = ssn.replaceAll("\\-", "");

        if ((ssn == null) || (ssn.length() != 11) || ssn.matches(ssn.charAt(0) + "{11}")) return false;

        final Integer digit1 = calculate(ssn.substring(0, 9), weightSsn);
        final Integer digit2 = calculate(ssn.substring(0, 9) + digit1, weightSsn);
        return ssn.equals(ssn.substring(0, 9) + digit1.toString() + digit2.toString());
    }




}
