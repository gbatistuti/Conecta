package br.com.projeto.conecta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.conecta.repository.DemandaRepository;

@Controller
@RequestMapping("")
public class DemandaController {

	@Autowired
	private DemandaRepository demandaRepository;
	
	public String listar() {
		return ""; 
	}
	
}
