package com.Springmvc.Controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Springmvc.Model.Professor;
import com.Springmvc.Model.Telefone;
import com.Springmvc.Repositorio.ProfessorRepositorio;
import com.Springmvc.Repositorio.TelefoneRepositorio;

import jakarta.validation.Valid;





@Controller
public class ProfessorControle {
	@Autowired
	private ProfessorRepositorio repositorio;
	@Autowired
	private TelefoneRepositorio telRepositotio;
    
	@RequestMapping(method = RequestMethod.GET,value ="/cadastroprofessor" )
	public ModelAndView inicio() {		
		var mv = new ModelAndView("cadastro/cadastroprofessor");
		mv.addObject("professorobj",new Professor());
		Iterable<Professor>prof  = repositorio.findAll();
		mv.addObject("professores",prof);
		return mv;
		
	}
	@RequestMapping(method = RequestMethod.POST,value = "**/salvarprofessor")
	public ModelAndView salvar(@Valid Professor professor,BindingResult result) {
		
		if(result.hasErrors()) {
			var mv = new ModelAndView("cadastro/cadastroprofessor");
			Iterable<Professor>prof  = repositorio.findAll();
			mv.addObject("professores",prof);
			mv.addObject("professorobj",professor);
			List<String> msg = new ArrayList<String>();
			for(ObjectError obj : result.getAllErrors() ) {
				msg.add(obj.getDefaultMessage());  //vem das anotações do @NotEmpty
			}
			mv.addObject("msg",msg);
			return mv;
		}
		
		repositorio.save(professor);
		
		var mv = new ModelAndView("cadastro/cadastroprofessor");
		Iterable<Professor>prof  = repositorio.findAll();
		mv.addObject("professores",prof);
		mv.addObject("professorobj",new Professor());
		return mv;
	}
	@RequestMapping(method = RequestMethod.GET,value = "/listarprofessores")
	public ModelAndView listarProfessores() {
	var mv = new ModelAndView("cadastro/cadastroprofessor");
	Iterable<Professor>prof  = repositorio.findAll();
	mv.addObject("professores",prof);
	mv.addObject("professorobj",new Professor());
	return mv;
	
	}
	@GetMapping("/editarprofessor/{idprofessor}")
	public ModelAndView editar(@PathVariable("idprofessor") Long idprofessor) {
		Optional<Professor> professor = repositorio.findById(idprofessor);
		var mav = new ModelAndView("cadastro/cadastroprofessor");
		mav.addObject("professorobj",professor.get());
		return mav;
	}
	
	@GetMapping("/removerprofessor/{idprofessor}")
	public ModelAndView excluir(@PathVariable("idprofessor") Long idprofessor) {	
		repositorio.deleteById(idprofessor);
		var mv = new ModelAndView("cadastro/cadastroprofessor");
		mv.addObject("professores",repositorio.findAll());
		mv.addObject("professorobj",new Professor());
		return mv;
		
	}
	@PostMapping("**/pesquisarprofessor")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa")String nomepesquisa) {
		var mv = new ModelAndView("cadastro/cadastroprofessor");
		mv.addObject("professores",repositorio.findProfessorByName(nomepesquisa));
		mv.addObject("professorobj",new Professor());
		return mv;
	}
	
	@GetMapping("/telefones/{idprofessor}")
	public ModelAndView telefone(@PathVariable("idprofessor") Long idprofessor) {
		Optional<Professor> professor = repositorio.findById(idprofessor);
		var mav = new ModelAndView("cadastro/telefones");
		mav.addObject("professorobj",professor.get());
		mav.addObject("telefones",telRepositotio.getTelefones(idprofessor));
		return mav;
	}
	
	@PostMapping("**/addfoneProfessor/{idprofessor}")	
	public ModelAndView addFoneProfessor(Telefone telefone,@PathVariable("idprofessor")Long idprofessor) {
		var professor = repositorio.findById(idprofessor).get();		
		telefone.setProfessor(professor);
		telRepositotio.save(telefone);
		var mv = new ModelAndView("cadastro/telefones");
		mv.addObject("professorobj",professor);
		mv.addObject("telefones",telRepositotio.getTelefones(idprofessor));
		return mv;
	}
	
	@GetMapping("/removertelefone/{idtelefone}")
	public ModelAndView excluirTelefone(@PathVariable("idtelefone") Long idtelefone) {	
		var professor = telRepositotio.findById(idtelefone).get().getProfessor();
		telRepositotio.deleteById(idtelefone);
		var mv = new ModelAndView("cadastro/telefones");
		mv.addObject("professorobj",professor);
		mv.addObject("telefones",telRepositotio.getTelefones(professor.getId()));
		return mv;
		
	}
	
}
