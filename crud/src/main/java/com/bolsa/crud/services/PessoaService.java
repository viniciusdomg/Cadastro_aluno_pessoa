package com.bolsa.crud.services;

import com.bolsa.crud.model.Pessoa;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PessoaService {

    List<Pessoa> listarPessoas();

    ResponseEntity<String> adicionarPessoa(Pessoa pessoa);

    ResponseEntity<String> atualizarPessoa(Pessoa pessoa);

    ResponseEntity<String> deletarPessoa(Integer id);

    Pessoa buscarPorId(Integer id);

    Pessoa buscarPorCpf(String cpf);
}
