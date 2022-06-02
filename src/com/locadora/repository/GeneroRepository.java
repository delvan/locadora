package com.locadora.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.locadora.connection.br.ConnetionDB;

import com.locadora.model.br.Genero;

public class GeneroRepository {

	ConnetionDB connection = new ConnetionDB();

	Connection conn = new ConnetionDB().conecta();

	PreparedStatement prep = null;

	public ArrayList<Genero> listarGenero() throws SQLException {

		String sql = "select * from genero";
		prep = conn.prepareStatement(sql);
		ResultSet rs = prep.executeQuery();

		ArrayList<Genero> generos = new ArrayList<Genero>();

		while (rs.next()) {

			Genero genero = new Genero();

			genero.setGenero(rs.getString("genero"));

			generos.add(genero);

		}

		return generos;
	}

}
