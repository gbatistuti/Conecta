package br.com.projeto.conecta.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Projeto;
import br.com.projeto.conecta.repository.GeradorDeRelatorios;
import br.com.projeto.conecta.repository.ProjetoRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class ProjetoService {
	
	@Autowired
	private ProjetoRepository projetoRepository;
	@Autowired
	private GeradorDeRelatorios geradorDeRelatorios;
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Cacheable(value = "projetosTodosCache")
	public Object buscarTodos() {
		return projetoRepository.findAll();
	}
	
	@Cacheable(value = "projetosCache")
	@CacheEvict(value = "email")
	public List<Projeto> buscarPor(String email) {
		return projetoRepository.getByEmail(email);
	}
	
	@CacheEvict(value = {"projetosTodosCache", "projetosCache"}, allEntries = true)
	public void salvar(Projeto projeto){
		projetoRepository.save(projeto);
	}
	
	public Projeto getProjeto(Integer id) {
		return projetoRepository.getOne(id);
	}

	@CacheEvict(value = "projetosTodosCache", allEntries = true)
	public void descontarCreditos(float creditosParaDescontar, Integer idProjeto) {
		projetoRepository.debitarCreditos(creditosParaDescontar, idProjeto);
	}
	
	@CacheEvict(value = "projetosTodosCache", allEntries = true)
	public void atualizarCreditos(Float qtdCreditos, Integer idProjeto) {
		projetoRepository.atualizarCreditos(qtdCreditos, idProjeto);
	}

	public JasperPrint exportaAlocacoesPorProjetoPdf(String projeto) throws IOException, SQLException, JRException {
		String caminho = resourceLoader.getResource("classpath:/relatorios/alocacoesPorProjeto.jrxml").getURI().getPath();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("projeto", projeto);
		return geradorDeRelatorios.exportJrxml(caminho, parametros);
	}
}
