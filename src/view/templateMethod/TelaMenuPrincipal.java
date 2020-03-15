package view.templateMethod;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.facade.FacadeAdministracao;
import view.ouvintes.OuvinteCadastrarRoupa;

public class TelaMenuPrincipal extends TemplateMethodTela{

	public TelaMenuPrincipal() {
		repaint();
	}
	@Override
	public void adicionarBotoes() {
		
	}

	@Override
	public void adicionarJLabel() {
		String boasVindas = "";
		FacadeAdministracao facade = new FacadeAdministracao();
		if(facade.getAdministrador().getSexo().equalsIgnoreCase("masculino")){
			boasVindas = "Bem-Vindo Sr ";
		}else{
			boasVindas = "Bem-Vinda Sra ";
		}
		JLabel bemVindo = new JLabel(boasVindas+facade.getAdministrador().getNome());
		bemVindo.setBounds(150, 100, 200, 35);
		bemVindo.setFont(new Font("Times new roman", Font.BOLD, 20));
		add(bemVindo);
	}
	
	@Override
	public void adicionarMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(-5, 2, 500, 30);
		JMenu menu = new JMenu("Menu");
		menu.setIcon(new ImageIcon(getClass().getResource("/icons8-menu-vertical-20.png")));
		
		OuvinteCadastrarRoupa ouvinteCadastrarRoupa = new OuvinteCadastrarRoupa(this);
		JMenuItem cadastrarRoupa = new JMenuItem("Cadastrar Roupa");
		cadastrarRoupa.addActionListener(ouvinteCadastrarRoupa);
		
		//irei implementar mais coisas...
		menu.add(cadastrarRoupa);
		menuBar.add(menu);
		add(menuBar);
	}

}
