package com.locadora.br;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.locadora.model.br.Filme;
import com.locadora.model.br.Genero;
import com.locadora.repository.FilmesRepository;
import com.locadora.repository.GeneroRepository;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Label;

public class FormFilmes {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtDuracao;
	private JTextField txtData;
	private JTable table;
	private ImageIcon imageIcon;
	private JComboBox cbGenero;
	String sinopse;

	GeneroRepository generos = new GeneroRepository();

	FilmesRepository filmesRepository = new FilmesRepository();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormFilmes window = new FormFilmes();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormFilmes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 255, 255));
		frame.setBackground(new Color(102, 204, 255));
		frame.setBounds(100, 100, 993, 713);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome Filme:");
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 12));
		lblNewLabel.setBounds(47, 64, 99, 26);
		frame.getContentPane().add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.setBounds(156, 67, 235, 20);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Dura\u00E7\u00E3o Filme");
		lblNewLabel_1.setFont(new Font("Georgia", Font.BOLD, 12));
		lblNewLabel_1.setBounds(47, 112, 99, 14);
		frame.getContentPane().add(lblNewLabel_1);

		try {
			MaskFormatter dur = new MaskFormatter("##:##:##");

			txtDuracao = new JFormattedTextField();
			txtDuracao.setBounds(156, 109, 123, 20);
			frame.getContentPane().add(txtDuracao);
			txtDuracao.setColumns(10);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		JLabel lblNewLabel_2 = new JLabel("Genero Filme");
		lblNewLabel_2.setFont(new Font("Georgia", Font.BOLD, 12));
		lblNewLabel_2.setBounds(47, 154, 97, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Data Filme");
		lblNewLabel_3.setFont(new Font("Georgia", Font.BOLD, 12));
		lblNewLabel_3.setBounds(47, 199, 78, 14);
		frame.getContentPane().add(lblNewLabel_3);

		try {
			MaskFormatter dataFilmeMasc = new MaskFormatter("##/##/####");
			txtData = new JFormattedTextField();
			txtData.setBounds(156, 196, 166, 20);
			frame.getContentPane().add(txtData);
			txtData.setColumns(10);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Object[] genero = { "Drama", "Ação", "Ficção", "Suspense" };

		try {
			ArrayList<Genero> listar = generos.listarGenero();

			cbGenero = new JComboBox(listar.toArray());
			cbGenero.setBounds(156, 154, 166, 22);
			frame.getContentPane().add(cbGenero);
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel lblNewLabel_4 = new JLabel("Sinopse");
		lblNewLabel_4.setFont(new Font("Georgia", Font.BOLD, 12));
		lblNewLabel_4.setBounds(47, 249, 63, 14);
		frame.getContentPane().add(lblNewLabel_4);

		JTextArea txtSinopse = new JTextArea();
		txtSinopse.setBounds(156, 244, 256, 113);
		frame.getContentPane().add(txtSinopse);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(547, 43, 389, 206);
		frame.getContentPane().add(scrollPane);

		JLabel lbImagem = new JLabel("Selecione uma imagem clicando aqui!!");
		lbImagem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lbImagem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JFileChooser chooser = new JFileChooser();
				File f;

				try {

					chooser.showOpenDialog(null);
					f = chooser.getSelectedFile();

					String foto = f.getAbsolutePath();
					lbImagem.setText(foto);

					/*Código para definir o tamanho da imagem na tela */
					imageIcon = new ImageIcon(f.getPath());
					System.out.println(imageIcon);
					Image image = imageIcon.getImage(); // transform it
					Image newimg = image.getScaledInstance(400, 200, java.awt.Image.SCALE_SMOOTH); 
				

					ImageIcon icon = new ImageIcon(newimg);

					lbImagem.setIcon(icon);

				} catch (Exception e2) {
					System.err.println("Imagem não selecionada: " + e2);
				}

			}
		});
		lbImagem.setBackground(Color.WHITE);
		lbImagem.setBounds(47, 412, 389, 183);
		frame.getContentPane().add(lbImagem);

		table = new JTable();

		Object[] columns = { "Nome", "Duração" };
		DefaultTableModel model = new DefaultTableModel();
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		model.setColumnIdentifiers(columns);
		// table.getColumnModel().getColumn(0).setWidth(80);
		table.setModel(model);

		table.getColumnModel().getColumn(0).setWidth(80);

		scrollPane.setViewportView(table);

		String[] filmes = { "acao", "drama", "ficcao", "suspense" };

		// HashMap<String, String> listasFiles = new HashMap<String, String>();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = table.getSelectedRow();

				String filme = filmes[index];

				ImageIcon imageIcon = new ImageIcon(getClass().getResource(filme + ".png"));

				lbImagem.setIcon(imageIcon);
				// lbSinopse.setText(sinopse);
			}

		});

		JLabel lblNewLabel_5 = new JLabel("Selecione Imagem do Encarte");
		lblNewLabel_5.setFont(new Font("Georgia", Font.BOLD, 12));
		lblNewLabel_5.setBounds(140, 381, 215, 20);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Sinopse");
		lblNewLabel_6.setFont(new Font("Georgia", Font.BOLD, 12));
		lblNewLabel_6.setBounds(726, 506, 78, 14);
		frame.getContentPane().add(lblNewLabel_6);

		JButton btnInserir = new JButton("");
		btnInserir.setBackground(SystemColor.inactiveCaption);
		btnInserir.setIcon(new ImageIcon(FormFilmes.class.getResource("/icones/inserir_novo.png")));
		btnInserir.setToolTipText("");

		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Filme filme = new Filme();

				String nomeFilme = txtNome.getText();
				String duracaoFilme = txtDuracao.getText();

				// duracao filme combobx
				String dataFilme = txtData.getText();
				String sinopseFilme = txtSinopse.getText();
				String imageFilme = lbImagem.getText();

				if (nomeFilme.equals("") && nomeFilme.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo nome está vazio!");

				}

				if (duracaoFilme.trim().length() < 0 && duracaoFilme.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo duração está vazio!");

				}

				if (dataFilme.equals("") && dataFilme.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo data filme está vazio!");

				}

				if (sinopseFilme.equals("") && sinopseFilme.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo sinopse filme está vazio!");

				} else {

					filme.setNome_filme(txtNome.getText());
					filme.setDuracao_filme(txtDuracao.getText());
					// combobox genero
					String item = cbGenero.getSelectedItem().toString();
					filme.setGenero_filme(item);
					
					System.out.println(item);
					
					filme.setData_filme(txtData.getText());
					filme.setSinopse_filme(txtSinopse.getText());
					filme.setImagem_filme(imageIcon.toString());
					
					//Fomatação de data
					//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
					LocalDate localDate = LocalDate.now();
					filme.setData_modificacao_filme(localDate);

				}

				// filme.setGenero_filme();

				// Object[] row = new Object[3];

				// row[0] = txtNome.getText();
				// row[1] = txtDuracao.getText();

				// model.addRow(row);

			}
		});
		btnInserir.setBounds(10, 623, 99, 40);
		frame.getContentPane().add(btnInserir);

		JButton btnDeletar = new JButton("");
		btnDeletar.setBackground(SystemColor.inactiveCaption);
		btnDeletar.setIcon(new ImageIcon(FormFilmes.class.getResource("/icones/menos.png")));
		btnDeletar.setBounds(119, 622, 104, 41);
		frame.getContentPane().add(btnDeletar);

		JButton btnLimpar = new JButton("");
		btnLimpar.setForeground(Color.GREEN);
		btnLimpar.setBackground(SystemColor.inactiveCaption);
		btnLimpar.setIcon(new ImageIcon(FormFilmes.class.getResource("/icones/limpar.png")));
		btnLimpar.setBounds(343, 622, 99, 41);
		frame.getContentPane().add(btnLimpar);

		JLabel lblNewLabel_8 = new JLabel("Sess\u00E3o de Filmes");
		lblNewLabel_8.setFont(new Font("Georgia", Font.BOLD, 12));
		lblNewLabel_8.setBounds(681, 18, 134, 14);
		frame.getContentPane().add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Sistema de Loca\u00E7\u00E3o de Filmes");
		lblNewLabel_9.setFont(new Font("Georgia", Font.BOLD, 24));
		lblNewLabel_9.setBounds(23, 0, 389, 42);
		frame.getContentPane().add(lblNewLabel_9);

		JButton btnEditar = new JButton("");
		btnEditar.setBackground(SystemColor.inactiveCaption);
		btnEditar.setIcon(new ImageIcon(FormFilmes.class.getResource("/icones/editar.png")));
		btnEditar.setBounds(234, 623, 99, 40);
		frame.getContentPane().add(btnEditar);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(501, 64, 8, 592);
		frame.getContentPane().add(separator);

		JList listSinopse = new JList();
		listSinopse.setEnabled(false);
		listSinopse.setBounds(592, 531, 316, 125);
		frame.getContentPane().add(listSinopse);

		Label label = new Label("Exibindo Imagem do Encarte");
		label.setFont(new Font("Georgia", Font.BOLD, 12));
		label.setBounds(669, 267, 175, 22);
		frame.getContentPane().add(label);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(572, 295, 352, 183);
		frame.getContentPane().add(lblNewLabel_7);
	}
}
