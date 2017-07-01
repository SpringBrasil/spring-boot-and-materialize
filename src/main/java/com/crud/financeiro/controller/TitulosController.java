package com.crud.financeiro.controller;

import com.crud.financeiro.controller.page.PageWrapper;
import com.crud.financeiro.model.Situacao;
import com.crud.financeiro.model.Tipo;
import com.crud.financeiro.model.TipoDePagamento;
import com.crud.financeiro.model.Titulo;
import com.crud.financeiro.repository.Entidades;
import com.crud.financeiro.repository.TiposDePagamento;
import com.crud.financeiro.repository.Titulos;
import com.crud.financeiro.service.TitulosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping("/titulos")
public class TitulosController {

    private final String INDEX = "titulo/CadastroTitulo";

    @Autowired
    private Entidades entidades;

    @Autowired
    private TitulosService titulosService;

    @Autowired
    private TiposDePagamento tiposDePagamento;

    @Autowired
    private Titulos titulos;

    @RequestMapping(value = "/novo")
    public ModelAndView novo(Titulo titulo) {
        ModelAndView mv = new ModelAndView(INDEX);
        mv.addObject("todosOsTipos", Tipo.values());
        mv.addObject("todasAsSituacoes", Situacao.values());
        mv.addObject("tiposDePagamento", tiposDePagamento.findAll());
        return mv;
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public ModelAndView salvar(@Valid Titulo titulo, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
            return novo(titulo);
        }

        titulosService.salvar(titulo);
        attributes.addFlashAttribute("mensagem", "Título salvo com sucesso");
        return new ModelAndView("redirect:/titulos/novo");
    }

    @GetMapping
    public ModelAndView pesquisar(Titulo titulo, @PageableDefault(size = 3) Pageable pageable,
                                  HttpServletRequest httpServletRequest){

        ModelAndView mv = new ModelAndView("titulo/PesquisarTitulos");
        mv.addObject("listaDeEntidades", titulos.entidadesDosTitulos());
        String descricao = titulo.getDescricao() == null ? "%" : titulo.getDescricao();

        PageWrapper<Titulo> paginaWrapper =
                new PageWrapper<>(titulos.filtrados(descricao, titulo.getEntidade(), pageable), httpServletRequest);
        mv.addObject("pagina", paginaWrapper);
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<?> novoTipoDePagamento(@RequestBody @Valid TipoDePagamento tipoDePagamento,
                                                               BindingResult result){

        if (result.hasErrors()){
          return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
        }

        tipoDePagamento = tiposDePagamento.save(tipoDePagamento);
        return ResponseEntity.ok(tipoDePagamento);
    }

    @GetMapping("/{codigo}")
    public ModelAndView editar(@PathVariable("codigo") Titulo titulo){
        ModelAndView mv = new ModelAndView(INDEX);
        mv.addObject("listaDeEntidades", entidades.findAll());
        mv.addObject("todosOsTipos", Tipo.values());
        mv.addObject("todasAsSituacoes", Situacao.values());
        mv.addObject("tiposDePagamento", tiposDePagamento.findAll());
        mv.addObject(titulo);
        return mv;
    }

    @RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
    public String excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attributes){
        titulosService.excluir(codigo);
        attributes.addFlashAttribute("mensagem", "Título excluído com sucesso");
        return "redirect:/titulos";
    }


}
