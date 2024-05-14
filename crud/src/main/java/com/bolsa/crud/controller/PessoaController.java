package com.bolsa.crud.controller;

import com.bolsa.crud.model.Pessoa;
import com.bolsa.crud.services.AlunoService;
import com.bolsa.crud.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/Pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaServ;
    @Autowired
    private AlunoService alunoService;

    @GetMapping("/Listar")
    @ResponseBody
    public List<Pessoa> mostrarPessoa() {
        return pessoaServ.listarPessoas();
    }

    @GetMapping("/Buscar/{id}")
    @ResponseBody
    public Pessoa buscaPessoa(@PathVariable Integer id){
        System.out.println(id);
        return pessoaServ.buscarPorId(id);
    }

    @DeleteMapping("/Excluir/{id}")
    public ResponseEntity<String> excluirAluno(@PathVariable Integer id) {
        ResponseEntity<String> responseEntity = pessoaServ.deletarPessoa(id);
        return responseEntity;
    }


    @PostMapping("/Cadastrando")
    public ResponseEntity<String> criaPessoa(HttpServletRequest request) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(request.getParameter("nome"));
        pessoa.setCpf(request.getParameter("cpf"));
        ResponseEntity<String> responseEntity = pessoaServ.adicionarPessoa(pessoa);
        return responseEntity;
    }

    @PostMapping("/Atualizando")
    public ResponseEntity<String> atualizaPessoa(HttpServletRequest request) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(Integer.parseInt(request.getParameter("id")));
        pessoa.setNome(request.getParameter("nome"));
        pessoa.setCpf(request.getParameter("cpf"));
        ResponseEntity<String> responseEntity =  pessoaServ.atualizarPessoa(pessoa);
        return responseEntity;
    }
}

