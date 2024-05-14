package com.bolsa.crud.repository;

import com.bolsa.crud.AlunoProjection;
import com.bolsa.crud.model.Aluno;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{

    @Query("SELECT a.id as id, a.matricula as matricula, a.ano_entrada as anoEntrada, p.cpf as cpfPessoa FROM Aluno a INNER JOIN Pessoa p ON a.id_pessoa = p.id")
    List<AlunoProjection> listaDeAlunos();
    @Query("SELECT COUNT(a.id) > 0 FROM Aluno a WHERE a.matricula = ?1")
    boolean buscaPorMatricula(String matricula);

    @Query("SELECT COUNT(a) > 0 FROM Aluno a WHERE a.id_pessoa = ?1")
    Boolean buscarPessoaAluna(Integer id_pessoa);

    @Query("SELECT matricula FROM Aluno WHERE id = ?1")
    String informaMatricula(Integer id);

    @Query("SELECT a.id as id, a.matricula as matricula, a.ano_entrada as anoEntrada, " +
            "p.cpf as cpfPessoa FROM Aluno a INNER JOIN Pessoa p ON a.id_pessoa = p.id " +
            "WHERE a.id = ?1")
    AlunoProjection buscaPorId(Integer id);

    @Modifying
    @Query(value = "INSERT INTO graduacao.aluno (matricula, ano_entrada, id_pessoa) VALUES (:matricula, :anoEntrada, :idPessoa)", nativeQuery = true)
    void inserirAluno(@Param("matricula") String matricula, @Param("anoEntrada") int anoEntrada, @Param("idPessoa") int idPessoa);

    @Modifying
    @Query("UPDATE Aluno a SET a.matricula = :matricula, a.ano_entrada = :anoEntrada, a.id_pessoa = :idPessoa WHERE a.id = :id")
    void atualizarAluno(@Param("id") Integer id, @Param("matricula") String matricula, @Param("anoEntrada") int anoEntrada, @Param("idPessoa") Integer idPessoa);

    @Modifying
    @Query("DELETE FROM Aluno a WHERE a.id_pessoa = ?1")
    void deletarPessoaAluna(Integer id_pessoa);

    @Modifying
    @Query("DELETE FROM Aluno a WHERE a.id = ?1")
    void deletarPessoa(Integer id);

}
