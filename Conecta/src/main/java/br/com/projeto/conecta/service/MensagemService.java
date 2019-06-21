package br.com.projeto.conecta.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Alocacoes;
import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.domain.Lider;
import br.com.projeto.conecta.domain.Mensagem;
import br.com.projeto.conecta.domain.Pedido;
import br.com.projeto.conecta.domain.Recusado;
import br.com.projeto.conecta.domain.Usuarios;

@Service
public class MensagemService {
	
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private AgendamentoService agendamentoService;
	@Autowired
	private AlocacaoService alocacaoService;
	
	private Queue<Mensagem> filaMensagemService = new LinkedList<Mensagem>();
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String hoje = LocalDate.now().format(formatter);
	
	
	@Async
	public void emailApontamento(Disponiveis disponivel) {
		Mensagem mensagem = new Mensagem(disponivel.getConsultor().getEmail(),
				"Apontamento realizado | " + hoje,
				"Olá, " + disponivel.getConsultor().getNome() 
						+ "\nID do apontamento: "	+ disponivel.getIdDisponivel() 
						+ "\nInício às: " + disponivel.getHoraInicio()
						+ "\nFim às: " + disponivel.getHoraFim() 
						+ "\nRealizado com sucesso!"
						+ "\n\nAtenciosamente,"
						+ "\nEquipe TOTVS Conecta");
		
		filaMensagemService.add(mensagem);
	}
	
	@Async
	public void emailCandidatura(Disponiveis disponivel, Integer idPedido) {
		Pedido pedido = pedidoService.getInformacoesEmailCandidatura(idPedido);
		Mensagem mensagem = new Mensagem(disponivel.getConsultor().getEmail(),
				"Candidatura realizada | " + hoje,
				"Olá, " + disponivel.getConsultor().getNome() 
						+ "\nID do pedido: "	+ pedido.getIdPedido()
						+ "\nCliente: " + pedido.getProjeto().getCliente().getNome() 
						+ "\nProjeto: " + pedido.getProjeto().getNome()
						+ "\nTítulo do Pedido: " + pedido.getTitulo()
						+ "\nDescrição do Pedido: " + pedido.getDescricao()
						+ "\nHoras contratadas: " + pedido.getSugestaoDeHoras()
						+ "\n\nAtenciosamente,"
						+ "\n\nEquipe TOTVS Conecta");
		
		filaMensagemService.add(mensagem);
	}
	
	@Async
	public void emailCriacaoPedido(Pedido pedido, Usuarios usuario) {
		Mensagem mensagem = new Mensagem(usuario.getEmail(),
				"Pedido Criado | " + hoje,
				"Olá, " + usuario.getNome() 
						+ "\nID do Pedido: "	+ pedido.getIdPedido()
						+ "\nTítulo do Pedido: " + pedido.getTitulo()
						+ "\nDescrição do Pedido: " + pedido.getDescricao()
						+ "\nProjeto: " + pedido.getProjeto().getNome()
						+ "\nHoras contratadas: " + pedido.getSugestaoDeHoras()
						+ "\n\nAtenciosamente,"
						+ "\n\nEquipe TOTVS Conecta");
		
		filaMensagemService.add(mensagem);
	}

	@Async
	public void emailCriacaoAgendamento(Usuarios usuario, Agendamento agendamento) {
		Mensagem mensagem = new Mensagem(usuario.getEmail(),
				"Agendamento Criado | " + hoje,
				"Olá, " + usuario.getNome() 
						+ "\nID do Agendamento: "	+ agendamento.getIdAgendamento()
						+ "\nID do Consultor Disponível: " + agendamento.getDisponivel().getIdDisponivel()
						+ "\nTítulo do Agendamento: " + agendamento.getPedido().getTitulo()
						+ "\nDescrição do Pedido: " + agendamento.getPedido().getDescricao()
						+ "\nProjeto: " + agendamento.getPedido().getProjeto().getNome()
						+ "\nHoras contratadas: " + agendamento.getPedido().getSugestaoDeHoras()
						+ "\n\nAtenciosamente,"
						+ "\n\nEquipe TOTVS Conecta");
		
		filaMensagemService.add(mensagem);
	}

	public void emailAprovacaoConsultor(Integer idAlocacao, Integer idPedido) {
		Alocacoes alocacao = alocacaoService.getAlocacao(idAlocacao);
		Pedido pedido = pedidoService.getInformacoesEmailCandidatura(idPedido);
		Mensagem mensagem = new Mensagem(alocacao.getAgendamento().getDisponivel().getConsultor().getEmail(),
				"Alocação Criada | " + hoje,
				"Olá, " + alocacao.getAgendamento().getDisponivel().getConsultor().getNome() 
						+ "\n\nVocê foi alocado, pelo líder " + alocacao.getCriadoPor().getNome() + ", para atender o projeto " + pedido.getProjeto().getNome()
						+ " das "	+ alocacao.getHoraInicio()
						+ " às " + alocacao.getHoraFim() + "." 
						+ "\nPor favor, entre em contato no telefone: " + pedido.getProjeto().getCliente().getTelefone() + "."
						+ "\n\nID do Agendamento: " + alocacao.getAgendamento().getIdAgendamento()
						+ "\nID do Pedido: " + pedido.getIdPedido()
						+ "\nTítulo do Pedido: " + pedido.getTitulo()
						+ "\nDescrição do Pedido: " + pedido.getDescricao()
						+ "\n\nAtenciosamente,"
						+ "\n\nEquipe TOTVS Conecta");
		
		filaMensagemService.add(mensagem);
	}

	public void emailAprovacaoLider(Integer idAlocacao, Integer idPedido) {
		Alocacoes alocacao = alocacaoService.getAlocacao(idAlocacao);
		Pedido pedido = pedidoService.getInformacoesEmailCandidatura(idPedido);
		Mensagem mensagem = new Mensagem(alocacao.getCriadoPor().getEmail(),
				"Alocação Criada | " + hoje,
				"Olá, " + alocacao.getCriadoPor().getNome() 
						+ "\nVocê alocou o " + alocacao.getAgendamento().getDisponivel().getConsultor().getNome()
						+ " no projeto " + pedido.getProjeto().getNome()
						+ "\nHora início: " + alocacao.getHoraInicio()
						+ "\nHora fim: " + alocacao.getHoraFim()
						+ "\n\nID do Agendamento: " + alocacao.getAgendamento().getIdAgendamento()
						+ "\nID do Pedido: " + pedido.getIdPedido()
						+ "\nTítulo do Pedido: " + pedido.getTitulo()
						+ "\nDescrição do Pedido: " + pedido.getDescricao()
						+ "\n\nAtenciosamente,"
						+ "\n\nEquipe TOTVS Conecta");
		
		filaMensagemService.add(mensagem);
	}

	public void emailReprovacaoLider(Integer idAgendamento, Lider lider, Recusado recusado) {
		Agendamento agendamento	= agendamentoService.getAgendamento(idAgendamento);
		Mensagem mensagem = new Mensagem(lider.getEmail(),
				"Agendamento Recusado | " + hoje,
				"Olá, " + lider.getNome()
						+ "\nVocê recusou o pedido " + agendamento.getPedido().getTitulo() + "."
						+ "\n Motivo: " + recusado.getMotivo()
						+ "\nCliente: " + agendamento.getPedido().getProjeto().getCliente().getNome()
						+ "\nProjeto: " +agendamento.getPedido().getProjeto().getNome()
						+ "\nID do Pedido: " + agendamento.getPedido().getIdPedido()
						+ "\nID do Agendamento: " + agendamento.getPedido().getIdPedido()
						+ "\nDescrição do Pedido: " + agendamento.getPedido().getDescricao()
						+ "\n\nAtenciosamente,"
						+ "\n\nEquipe TOTVS Conecta");
		
		filaMensagemService.add(mensagem);
	}

	public void emailReprovacaoCliente(Recusado recusado) {
		Mensagem mensagem = new Mensagem(recusado.getAgendamento().getPedido().getProjeto().getCliente().getEmail(),
				"Agendamento Recusado | " + hoje,
						"Seu pedido foi recusado."
						+ "\n Motivo: " + recusado.getMotivo()
						+ "\nProjeto: " + recusado.getAgendamento().getPedido().getProjeto().getNome()
						+ "\nID do Pedido: " + recusado.getAgendamento().getPedido().getIdPedido()
						+ "\nID do Agendamento: " + recusado.getAgendamento().getPedido().getIdPedido()
						+ "\nDescrição do Pedido: " + recusado.getAgendamento().getPedido().getDescricao()
						+ "\n\nAtenciosamente,"
						+ "\n\nEquipe TOTVS Conecta");
		
		filaMensagemService.add(mensagem);
	}
	
	public Mensagem getMensagem() {
		if(filaMensagemService.isEmpty()) {
			return null;
		}
		return filaMensagemService.poll();
	}
}
