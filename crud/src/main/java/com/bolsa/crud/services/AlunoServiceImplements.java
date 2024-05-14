package com.bolsa.crud.services;

import com.bolsa.crud.AlunoProjection;
import com.bolsa.crud.model.Aluno;
import com.bolsa.crud.model.Pessoa;
import com.bolsa.crud.repository.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImplements implements AlunoService {

    @Autowired
    private final AlunoRepository alunoRepository;
    @Autowired
    private PessoaService pessoaService;

    public AlunoServiceImplements(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Override
    public List<AlunoProjection> listarAlunos() {
        return alunoRepository.listaDeAlunos();
    }

    @Override
    @Transactional
    public ResponseEntity<String> adicionarAluno(Aluno aluno) {
        if (alunoRepository.buscaPorMatricula(aluno.getMatricula())) {
            return ResponseEntity.badRequest().body("Não foi possível cadastrar (MATRICULA Existente)");
        }
        Pessoa pessoa = pessoaService.buscarPorCpf(aluno.getCpfPessoa());
        if(pessoa == null) {
            return ResponseEntity.badRequest().body("Não foi possível atualizar (CPF Inexistente)");
        }
        aluno.setId_pessoa(pessoa.getId());
        alunoRepository.inserirAluno(aluno.getMatricula(), aluno.getAno_entrada(), aluno.getId_pessoa());
        return ResponseEntity.ok("Aluno cadastrado");
    }

    @Override
    @Transactional
    public ResponseEntity<String> atualizarAluno(Aluno aluno) {
        if(!alunoRepository.informaMatricula(aluno.getId()).equals(aluno.getMatricula())){
            if(alunoRepository.buscaPorMatricula(aluno.getMatricula())){
                return ResponseEntity.badRequest().body("Não foi possível atualizar (MATRICULA Existente)");
            }
        }
        Pessoa pessoa = pessoaService.buscarPorCpf(aluno.getCpfPessoa());
        if(pessoa == null) {
            return ResponseEntity.badRequest().body("Não foi possível atualizar (CPF Inexistente)");
        }
        aluno.setId_pessoa(pessoa.getId());
        alunoRepository.atualizarAluno(aluno.getId(), aluno.getMatricula(), aluno.getAno_entrada(), aluno.getId_pessoa());
        return ResponseEntity.ok("Atualizado");
    }

    @Override
    @Transactional
    public ResponseEntity<String> deletarAluno(Integer id) {
        AlunoProjection aluno = alunoRepository.buscaPorId(id);
        alunoRepository.deletarPessoa(id);
        return ResponseEntity.ok("Aluno da matricula - " + aluno.getCpfPessoa() + " - EXCLUIDO");
    }

    @Override
    public AlunoProjection buscaPorId(Integer id) {
        return alunoRepository.buscaPorId(id);
    }

    @Override
    @Transactional
    public void deletarPessoaAluna(Integer id) {
        alunoRepository.deletarPessoaAluna(id);
    }

    @Override
    public Boolean buscarPessoaAluna(Integer id_pessoa) {
        return alunoRepository.buscarPessoaAluna(id_pessoa);
    }

    @Override
    public Boolean buscaPorMatricula(String matricula) {
        return alunoRepository.buscaPorMatricula(matricula);
    }
}
