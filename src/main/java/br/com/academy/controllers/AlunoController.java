package br.com.academy.controllers;

import br.com.academy.dao.AlunoDao;
import br.com.academy.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AlunoController {
    @Autowired
    private AlunoDao alunoRepositorio;

    @GetMapping("/inserirAlunos")
    public ModelAndView inserirAlunos(Aluno aluno){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/formAluno");
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    @PostMapping("cadastrarAlunos")
    public ModelAndView cadastrarAlunos(@Valid Aluno aluno, BindingResult br){
        ModelAndView mv = new ModelAndView();
        if (br.hasErrors()){
            mv.setViewName("aluno/formAluno");
            mv.addObject("aluno");
        }else{
            mv.setViewName("redirect:/listaAlunos");
            alunoRepositorio.save(aluno);
        }
        return mv;
    }

    @GetMapping("/listaAlunos")
    public ModelAndView listaDeAlunos(Aluno aluno){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/listaAlunos");
        mv.addObject("alunoLista", alunoRepositorio.findAll());
        return mv;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/formAlterar");
        Aluno aluno = alunoRepositorio.getOne(id);
        mv.addObject("aluno", aluno);
        return mv;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(@Valid Aluno aluno, BindingResult  br){
        ModelAndView mv = new ModelAndView();
        if (br.hasErrors()){
            mv.setViewName("aluno/formAlterar");
            mv.addObject("aluno");
        }else{
            alunoRepositorio.save(aluno);
            mv.setViewName("redirect:/listaAlunos");
        }
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String exluirAluno(@PathVariable("id") Integer id){
        alunoRepositorio.deleteById(id);
        return "redirect:/listaAlunos";

    }

    @GetMapping("/filtroAlunos")
    public ModelAndView filtroDeAlunos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/filtroAlunos");
        return mv;
    }

    @GetMapping("/statusAlunos/{status}")
    public ModelAndView listaDeAlunosAtivos(@PathVariable("status") String status, Aluno aluno){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/filtroStatusAlunos");
        mv.addObject("status", status);
        switch (status){
            case "ATIVO":
                mv.addObject("statusAlunos", alunoRepositorio.findByStatusAtivo());
                break;
            case "INATIVO":
                mv.addObject("statusAlunos", alunoRepositorio.findByStatusInativo());
                break;
            case "TRANCADO":
                mv.addObject("statusAlunos", alunoRepositorio.findByStatusTrancado());
                break;
            case "CANCELADO":
                mv.addObject("statusAlunos", alunoRepositorio.findByStatusCancelado());
                break;
        }
        return mv;
    }
}
