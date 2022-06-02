package com.locadora.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.locadora.connection.br.ConnetionDB;
import com.locadora.model.br.Filme;

public class FilmesRepository {

	ConnetionDB connection = new ConnetionDB();

	Connection conn = new ConnetionDB().conecta();

	PreparedStatement prep = null;

	public void addContato(Filme filme) {

		try {

			// Prepare a statement to insert a record
			String sql = "INSERT INTO filme VALUES (?, ?, ?, ?, ?, ?)";

			prep = conn.prepareStatement(sql);

			prep.setString(1, filme.getNome_filme());
			prep.setString(2, filme.getDuracao_filme());
			prep.setString(3, filme.getGenero_filme());
			prep.setString(4, filme.getData_filme());
			prep.setString(5, filme.getSinopse_filme());
			prep.setString(6, filme.getImagem_filme());

			prep.execute();

		} catch (SQLException e) {
		}

	}

	public ArrayList<Filme> listarFilme() throws SQLException {

		String sql = "select * from contato";
		prep = conn.prepareStatement(sql);
		ResultSet rs = prep.executeQuery();

		ArrayList<Filme> filmes = new ArrayList<Filme>();

		if (rs.next()) {
			Filme contato = new Filme();
			contato.setNome_filme(rs.getString("nome_filme"));
			contato.setDuracao_filme(rs.getString("duracao_filme"));
			// contato.setGenero_filme(rs.getString("genero_filme"));
			// contato.setData_filme(rs.getString("data_filme"));
			// contato.setSinopse_filme(rs.getString("sinopse_filme"));
			// contato.setImagem_filme(rs.getString("image_filme"));
			// contato.setData_modificacao_filme(LocalDate.parse(rs.getString("data_modificacao_filme")));

			filmes.add(contato);

		}
		return filmes;
	}

	public void updateFilme() {

		Filme filme = new Filme();

		try {

			String query = "update filme set nome_filme=?, " + "duracao_filme=?, genero_filme=?, "
					+ "data_filme=?, sinopse_filme=?, " + "image_filme=? where Id=? ";

			prep = conn.prepareStatement(query);
			prep.setString(1, filme.getNome_filme());
			prep.setString(2, filme.getDuracao_filme());
			prep.setString(3, filme.getGenero_filme());
			prep.setString(4, filme.getData_filme());
			prep.setString(5, filme.getSinopse_filme());
			prep.setString(6, filme.getImagem_filme());
			prep.setInt(7, filme.getId_filme());

			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
