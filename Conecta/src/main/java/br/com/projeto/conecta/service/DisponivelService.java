package br.com.projeto.conecta.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Disponivel;
import br.com.projeto.conecta.repository.DisponivelRepository;
import br.com.projeto.conecta.repository.GeradorDeRelatorios;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class DisponivelService {

	@Autowired
	private DisponivelRepository disponivelRepository;
	@Autowired
	private GeradorDeRelatorios geradorDeRelatorios;
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Transactional
	@Cacheable(value = "disponiveisCache")
	public List<Disponivel> buscarTodos(){
		Date data = new Date();
		return disponivelRepository.findByDate(data);
	}

	@Transactional
	@CacheEvict(value = {"disponiveisCache", "validaApontamentoCache"}, allEntries = true)
	public void salvarApontamento(Disponivel disponiveis) {
		disponivelRepository.save(disponiveis);
	}
	
	@Transactional
	@Cacheable(value = "validaApontamentoCache")
	public Disponivel validaApontamento(String email) {
		Date data = new Date();
		return disponivelRepository.findByUserAndDate(data, email);
	}
	
	public Disponivel getDisponivel(int id) {
		return disponivelRepository.getOne(id);
	}

	public JasperPrint exportaDisponiveisPorPeriodoPdf(String dataInicio, String dataFim) throws SQLException, JRException, IOException {
		String caminho = resourceLoader.getResource("classpath:/relatorios/disponiveisPorPeriodo.jrxml").getURI().getPath();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("dataInicio", dataInicio);
		parametros.put("dataFim", dataFim);
		return geradorDeRelatorios.exportJrxml(caminho, parametros);		
	}

	public JasperPrint exportaDisponiveisPorConsultorPdf(String consultor) throws IOException, SQLException, JRException {
		String caminho = resourceLoader.getResource("classpath:/relatorios/disponiveisPorConsultor.jrxml").getURI().getPath();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("consultor", consultor);
		return geradorDeRelatorios.exportJrxml(caminho, parametros);
	}

	public JasperPrint exportaDisponiveisPorConsultorEPeriodoPdf(String consultor, String dataInicio, String dataFim) throws IOException, SQLException, JRException {
		String caminho = resourceLoader.getResource("classpath:/relatorios/disponiveisPorPeriodoEConsultor.jrxml").getURI().getPath();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("consultor", consultor);
		parametros.put("dataInicio", dataInicio);
		parametros.put("dataFim", dataFim);
		return geradorDeRelatorios.exportJrxml(caminho, parametros);
	}
}