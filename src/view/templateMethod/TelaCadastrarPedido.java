package view.templateMethod;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.ControllerPedido;
import controller.facade.FacadeAdministracao;
import model.Carrinho;

import model.Roupa;

public class TelaCadastrarPedido extends TemplateMethodTela{
	//Fiz a l�gica de cadastrar pedido, est� no ouvinte dessa classe
	public TelaCadastrarPedido() {
		setTitle("Cadastrar Pedido");
		setSize(600,350);
		repaint();
	}


	private JTextField campoEmail;
	private JTextField campoNome;
	private JTextField campoTelefone;
	private JButton buttonFazerPedido;

	@Override
	public void adicionarBotoes() {

		JButton buttonVoltar = new JButton("Voltar");
		buttonVoltar.setBounds(470, 20, 90, 30);
		add(buttonVoltar);

		buttonFazerPedido = new JButton("Fazer Pedido");
		buttonFazerPedido.setBounds(230, 240, 140, 30);
		add(buttonFazerPedido);

		OuvinteCadastrarPedido ouvinteCadastrarPedido = new OuvinteCadastrarPedido(this);
		buttonFazerPedido.addActionListener(ouvinteCadastrarPedido);

		OuvinteVoltarTelaCarrinho ouvinteVoltarTelaCarrinho = new OuvinteVoltarTelaCarrinho();
		buttonVoltar.addActionListener(ouvinteVoltarTelaCarrinho);

	}

	@Override
	public void adicionarJLabel() {

		JLabel labelTitulo = new JLabel("Dados Do Cliente");
		labelTitulo.setBounds(225, 80, 150, 30);
		labelTitulo.setFont(new Font("Times new Roman", Font.BOLD, 20));
		add(labelTitulo);

		JLabel labelEmail = new JLabel("Email");
		labelEmail.setBounds(100, 110, 50, 30);
		labelEmail.setFont(new Font("Arial",Font.BOLD,12));
		add(labelEmail);

		JLabel labelNome = new JLabel("Nome");
		labelNome.setBounds(100, 150, 50, 30);
		labelNome.setFont(new Font("Arial",Font.BOLD,12));
		add(labelNome);

		JLabel labelTelefone = new JLabel("Telefone");
		labelTelefone.setBounds(290,150, 70, 30);
		labelTelefone.setFont(new Font("Arial",Font.BOLD,12));
		add(labelTelefone);

	}


	public void adicionarTextField() {

		campoEmail = new JTextField();
		campoEmail.setBounds(170, 110, 290, 30);
		add(campoEmail);

		campoNome = new JTextField();
		campoNome.setBounds(140, 150, 130, 30);
		add(campoNome);

		campoTelefone = new JTextField();
		campoTelefone.setBounds(365, 150, 95, 30);
		add(campoTelefone);

	}
	public class OuvinteVoltarTelaCarrinho implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new TelaDeCarrinho();
			dispose();
		}
	}

	public class OuvinteCadastrarPedido implements ActionListener{

		private TelaCadastrarPedido tela;

		public OuvinteCadastrarPedido(TelaCadastrarPedido tela) {
			this.tela = tela;
		}

		public void actionPerformed(ActionEvent e) {

			if(campoEmail.getText().equals("") || campoNome.getText().equals("") || campoTelefone.getText().equals("")) {
				JOptionPane.showMessageDialog(tela, "Campo Vazio!");
			}else {

				ControllerPedido pedidoCTL = new ControllerPedido();
				//Pega as roupas escolhidas pelo o cliente, que est� no singleton
				//que � o carrinho
				Carrinho carrinho = Carrinho.getInstance();
				SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
				Date d = new Date();
				String a = s.format(d);
				String[] datas = a.split("/");
				datas[2]+=pedidoCTL.quantidadeDePedidos()+1;
				long id = Long.parseLong(datas[2]);
				//aviso adicionei o envio de email
				
				FacadeAdministracao facade = new FacadeAdministracao();
				facade.enviarEmail(campoEmail.getText(), "Pedido feito", 
						"Nome: "+campoNome.getText()+"\n"+"Telefone:"+campoTelefone);
				
				//gera um id com a data do ano junto o tamanho de pedido, assim
				//o id � unica a cada pedido
				try {
					pedidoCTL.addPedido(id,campoEmail.getText(), campoNome.getText(),
							campoTelefone.getText(),carrinho.getRoupa());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				campoEmail.setText("");
				campoNome.setText("");
				campoTelefone.setText("");
				JOptionPane.showMessageDialog(tela, "Pedido Feito!");
				//deleta as roupas salvas no singleton carrinho
				//e depois volta para tela carrinho
				carrinho.deleteRoupas();
				new TelaDeCarrinho();
				dispose();


				// Ainda vou fazer essa parte da l�gica, onde vou cadastrar o cliente e fazer a persist�ncia dele.

				//Paulo - na hora que cria o pedido j� � cadastrado o cliente, quando � passado os parametros do pedido,
				//Como � visto na classe pedido, assim a persistencia de pedido j� salva o pedido com o cliente dentro como atributo
				//a l�gica de cadastrar pedido est� pegando e funcionando.

			}


		}
	}

}
