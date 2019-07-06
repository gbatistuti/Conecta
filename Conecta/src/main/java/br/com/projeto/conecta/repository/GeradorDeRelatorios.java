package br.com.projeto.conecta.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Transactional
@Repository
public class GeradorDeRelatorios {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public JasperPrint exportJrxml(String caminho, Map<String, Object> parametros) throws SQLException, JRException, IOException {
		//pega o paremetro de conexao, creio que aqui pode ser nulo
		Connection conn = jdbcTemplate.getDataSource().getConnection();
		
		//passa o caminho para o relatorio XML a ser convertido para jasper
		//String path = resourceLoader.getResource(caminho).getURI().getPath();

		//converte o relatorio para jasper
		JasperReport jasperReport = JasperCompileManager.compileReport(caminho);
		
		//cria relatorio
		JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, conn);

		return print;
	}
}