package app;

import java.util.ArrayList;

public class GestorClientes {
	
	// --- ATRIBUTO CLASE ---
	private ArrayList<Cliente> clientes;
	
	// --- CONSTRUCTOR CLASE ---
	public GestorClientes() {
		clientes = new ArrayList<>();
		
		// --- CLIENTES DEBUGG ---
		clientes.add(new Cliente("admin", "1234"));
		clientes.add(new Cliente("test", "0000"));
	}
	
	// --- AGREGAR CLIENTE ---
	public void agregarCliente(String usuario, String contrasena) {
		Cliente nuevo = new Cliente(usuario, contrasena);
		clientes.add(nuevo);
	}
	
	public Cliente buscarCliente(String usuario) {
		for (Cliente c : clientes) {
			if (c.getUsuario().equals(usuario)) {
				return c;
			}
		}
		return null;
	}
	
	// --- VALIDAR LOGIN ---
	public boolean validarLogin(String usuario, String contrasena) {
		Cliente c = buscarCliente(usuario);
		if ( c != null ) {
			return c.validarContrasena(contrasena);
		}
		return false;
	}
	
	
}
