package br.com.projeto.conecta.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.domain.Usuarios;
import br.com.projeto.conecta.security.ConectaUserDetailsService;
import br.com.projeto.conecta.service.DisponivelService;
import br.com.projeto.conecta.service.PedidoService;

@Controller
@RequestMapping("/homeConsultor")
public class ConsultorController {

	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private ConectaUserDetailsService conecta;
	@Autowired
	private DisponivelService disponivelService;

	@GetMapping
	public String listarPedidos(ModelMap model) {
		model.addAttribute("pedido", pedidoService.buscarTodos());
		return "homeConsultor";
	}
	
	@PostMapping("/apontar")
	public String salvarApontamento(ModelMap model, Disponiveis disponiveis, HttpSession session) {
	
		Usuarios usuario = conecta.getCurrentUser(); 
		
		session.setAttribute("usuario", usuario);
		model.addAttribute("usuario", usuario);
		
		System.out.println(usuario.getIdUsuario());
		disponivelService.salvarApontamento(disponiveis);
		return "redirect:/homeConsultor";
	}
	
}