package app;

import java.util.ArrayList;

public class Cliente {
	
	//  --- ATRIBUTOS CLIENTE ---
	
	private String usuario;
	private String contrasena;
	private ArrayList<Cuenta> cuentas;
	
	
	
	// --- CONSTRUCTOR CLIENTE ---
	public Cliente(String usuario, String contrasena) {
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.cuentas = new ArrayList<>();
	}
	
	// --- COMPORTAMIENTO GETTER/SETTER CREDENCIALES ---
	
	public boolean validarContrasena(String contrasenaIngresada) {
		return this.contrasena.equals(contrasenaIngresada);
	}
	public String getUsuario() {
		return usuario;
	}
	
	// PRUEBA PUSH
	
	// --- COMPORTAMIENTO GETTER SETTER CUENTAS ---
	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
	}
	
	public ArrayList<Cuenta> getCuentas(){
		return cuentas;
	}
	
	public Cuenta buscarCuenta(int idCuenta) {
		for (Cuenta c: cuentas) {
			if (c.getId() == idCuenta) {
				return c;
			}
		}
		return null;
	}
	
}
