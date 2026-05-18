package com.example.locadora.filme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
  
@Controller
@RequestMapping("/filme")
public class FilmeController {
	
	@Autowired
	private FilmeService filmeService;
	@GetMapping                                           
	public String carregaPaginaListagem (Model model, HttpSession session){  
		model.addAttribute("lista", filmeService.getAllFilme());
		return "filme/listagem";                         
	}
 
	@GetMapping("/formulario")
	public String novoFilme(Model model,  HttpSession session) {
		model.addAttribute("filme", new Filme());
		return "filme/formulario";
	}
 
	@PostMapping("/salvar")
	public String salvarFilme( @ModelAttribute("filme") Filme filme,
			BindingResult result, HttpSession session,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "filme/formulario";
		}
		try {
			String mensagem = filmeService.save(filme);
			redirectAttributes.addFlashAttribute("message", mensagem);
			return "redirect:/filme";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Erro ao salvar filme: " + e.getMessage());
			return "redirect:/filme/formulario" + (filme.getId() != null ? "/" + filme.getId() : "");
		}
	}
	
	@GetMapping("/delete/{id}")
	@Transactional
	public String deleteTutorial(@PathVariable("id") Long id, Model model, HttpSession session,  RedirectAttributes redirectAttributes) {
		try {
			String mensagem = filmeService.deleteFilme(id);
			redirectAttributes.addFlashAttribute("message", mensagem);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/filme";
	}
	
	@GetMapping ("/formulario/{id}")                  
	public String carregaPaginaFormulario (@PathVariable("id") Long id,
			HttpSession session,
			RedirectAttributes redirectAttributes,
			Model model){

		try {
			Filme filme = filmeService.getFilmeById(id);
			model.addAttribute("filme", filme);
			return "filme/formulario";

		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/filme";
		}
	}	
}