package com.bolsa.crud.services;

import com.bolsa.crud.AlunoProjection;
import com.bolsa.crud.model.Aluno;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AlunoService {

    List<AlunoProjection> listarAlunos();

    ResponseEntity<String> adicionarAluno(Aluno aluno);

    ResponseEntity<String> atualizarAluno(Aluno aluno);

    ResponseEntity<String> deletarAluno(Integer id);

    AlunoProjection buscaPorId(Integer id);

    Boolean buscaPorMatricula(String matricula);

    void deletarPessoaAluna(Integer id);

    Boolean buscarPessoaAluna(Integer id_pessoa);

}
