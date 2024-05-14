package com.bolsa.crud.controller;

import com.bolsa.crud.AlunoProjection;
import com.bolsa.crud.model.Aluno;
import com.bolsa.crud.model.Pessoa;
import com.bolsa.crud.services.AlunoService;
import com.bolsa.crud.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/Graduacao")
public class AlunoController {

    @Autowired
    private AlunoService alunoServ;

    @GetMapping("/Listar")
    @ResponseBody
    public List<AlunoProjection> mostrarAlunos() {
        return alunoServ.listarAlunos();
    }

    @GetMapping("/Buscar/{id}")
    @ResponseBody
    public AlunoProjection buscarAluno(@PathVariable Integer id){
        return alunoServ.buscaPorId(id);
    }

    @DeleteMapping("/Excluir/{id}")
    public ResponseEntity<String> excluirAluno(@PathVariable Integer id) {
        ResponseEntity<String> responseEntity = alunoServ.deletarAluno(id);
        return responseEntity;
    }

    @PostMapping("/Cadastrando")
    public ResponseEntity<String> formularioAluno(HttpServletRequest request){
        Aluno aluno = new Aluno();
        aluno.setMatricula(request.getParameter("matricula"));
        aluno.setAno_entrada(Integer.parseInt(request.getParameter("anoIngresso")));
        aluno.setCpfPessoa(request.getParameter("cpfPessoa"));
        ResponseEntity<String> responseEntity = alunoServ.adicionarAluno(aluno);
        return responseEntity;
    }

    @PostMapping("/Atualizando")
    public ResponseEntity<String> formularioAtualizaAluno(HttpServletRequest request){
        Aluno aluno = new Aluno();
        aluno.setId(Integer.parseInt(request.getParameter("id")));
        aluno.setMatricula(request.getParameter("matricula"));
        aluno.setAno_entrada(Integer.parseInt(request.getParameter("anoIngresso")));
        aluno.setCpfPessoa(request.getParameter("cpfPessoa"));
        ResponseEntity<String> responseEntity = alunoServ.atualizarAluno(aluno);
        return responseEntity;
    }
}
