package com.bolsa.crud.services;

import com.bolsa.crud.model.Pessoa;
import com.bolsa.crud.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImplements implements PessoaService {

    @Autowired
    private final PessoaRepository pessoaRepository;
    @Autowired
    private AlunoService alunoService;

    public PessoaServiceImplements(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    @Override
    public ResponseEntity<String> adicionarPessoa(Pessoa pessoa) {
        if (pessoaRepository.buscaPorCpf(pessoa.getCpf()) != null) {
            return ResponseEntity.badRequest().body("Esse CPF já existe");
        }
        pessoaRepository.save(pessoa);
        return ResponseEntity.ok("Cadastrado");
    }

    @Override
    public ResponseEntity<String> atualizarPessoa(Pessoa pessoa) {
        if((!pessoaRepository.retornaCpf(pessoa.getId()).equals(pessoa.getCpf()))){
            if(pessoaRepository.buscaPorCpf(pessoa.getCpf()) != null){
                return ResponseEntity.badRequest().body("Este CPF já existe, atualize para outro");
            }
        }
        pessoaRepository.save(pessoa);
        return ResponseEntity.ok("Atualizado");
    }

    @Override
    public ResponseEntity<String> deletarPessoa(Integer id) {
        if(alunoService.buscarPessoaAluna(id)) {
            //Primeiro exclui a pessoa da tabela aluno, já que possui chave estrangeira de Pessoa
            alunoService.deletarPessoaAluna(id);
        }
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        pessoaRepository.deleteById(id);
        return ResponseEntity.ok("Pessoa do CPF - " + pessoa.get().getCpf() + " - EXCLUIDA");
    }

    @Override
    public Pessoa buscarPorId(Integer id) {
        return pessoaRepository.buscaPorId(id);
    }

    @Override
    public Pessoa buscarPorCpf(String cpf) {
        return pessoaRepository.buscaPorCpf(cpf);
    }
}
