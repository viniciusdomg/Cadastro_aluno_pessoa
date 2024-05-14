package com.bolsa.crud.repository;

import com.bolsa.crud.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

    @Query("SELECT p FROM Pessoa p WHERE p.cpf = ?1")
    Pessoa buscaPorCpf(String cpf);

    @Query("SELECT cpf FROM Pessoa WHERE id = ?1")
    String retornaCpf(Integer id);

    @Query ("SELECT p FROM Pessoa p WHERE p.id = ?1")
    Pessoa buscaPorId(Integer id);
}
