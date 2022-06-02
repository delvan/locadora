package com.locadora.test.br;

import java.sql.SQLException;
import java.util.ArrayList;

import com.locadora.model.br.Genero;
import com.locadora.repository.GeneroRepository;

public class ListarGenero {

	public static void main(String[] args) {

		// Genero genero = new Genero();

		GeneroRepository generos = new GeneroRepository();

		try {
			ArrayList<Genero> listar = generos.listarGenero();

			for (Genero genero : listar) {
				
				System.out.println(genero);
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
