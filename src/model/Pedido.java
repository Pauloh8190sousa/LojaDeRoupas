package model;

import java.util.ArrayList;

public class Pedido {
    private ArrayList<Roupa> roupas;
    private Cliente cliente;
    
    Pedido(String email,String nome,String telefone,ArrayList<Roupa> roupas){
    	cliente = new Cliente(email, nome, telefone);
    	this.roupas = roupas;
    }
    
    public float precoTotal(){
    	float total = 0;
    	for(Roupa r: roupas){
    		total+= r.getPreco();
    	}
    	return total;
    }
	public ArrayList<Roupa> getRoupas() {
		return roupas;
	}

	public void setRoupas(ArrayList<Roupa> roupas) {
		this.roupas = roupas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
    
}