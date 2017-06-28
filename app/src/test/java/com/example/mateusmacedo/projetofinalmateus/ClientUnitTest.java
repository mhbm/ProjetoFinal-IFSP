package com.example.mateusmacedo.projetofinalmateus;

import com.example.mateusmacedo.projetofinalmateus.model.PessoaModel;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lsitec101.macedo on 27/06/17.
 */

public class ClientUnitTest {

    @Test
    public void cpfValido() {
        //set up the expectation result
        String cpfValido = "15263558333";
        assertEquals(PessoaModel.isValidCpf(cpfValido), true);
    }

    @Test
    public void cpfInvalido() {
        //set up the expectation result
        String cpfInvalido = "12345678901";
        assertEquals(PessoaModel.isValidCpf(cpfInvalido), false);
    }

    @Test
    public void telefoneInvalido() {
        //set up the expectation result
        String telefone = "1234-123";
        assertEquals(PessoaModel.isValidPhoneNumber(telefone), false);
    }

    @Test
    public void telefoneValido() {
        //set up the expectation result
        String telefone = "1234-1234";
        assertEquals(PessoaModel.isValidPhoneNumber(telefone), true);
    }

    @Test
    public void CelularInvalido() {
        //set up the expectation result
        String telefone = "(11)9124-123";
        assertEquals(PessoaModel.isValidPhoneNumber(telefone), false);
    }

    @Test
    public void CelularValido() {
        //set up the expectation result
        String telefone = "(11)91124-1233";
        assertEquals(PessoaModel.isValidPhoneNumber(telefone), true);
    }

}
