package br.com.projeto.conecta.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.HabilidadesPrincipais;
import br.com.projeto.conecta.repository.GeradorDeRelatorios;
import br.com.projeto.conecta.repository.HabilidadesPrincipaisRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class HabilidadesPrincipaisService {

	@Autowired
	private HabilidadesPrincipaisRepository habilidadesPrincipaisRepository;
	@Autowired
	private GeradorDeRelatorios geradorDeRelatorios;

	@Transactional
	public List<HabilidadesPrincipais> buscarTodos() {
		return habilidadesPrincipaisRepository.findAll();
	}
	
	public JasperPrint exportPdfFile(String numero) throws SQLException, JRException, IOException {
		return geradorDeRelatorios.exportHabilidadesPrincipaisPorId(numero);
	}
}