package app;

public class Cuenta implements Valores{
	
	// --- ATRIBUTOS CUENTA  ---
	private static int contadorId = 1;
	private double saldo;
	private int moneda;
	private int id;
	
	// --- CONSTRUCTOR CUENTA ---
	public Cuenta(int moneda) {
		this.id = contadorId++;
		this.moneda = moneda;
		this.saldo = 0;
	}
	
	// --- COMPORTAMIENTO CONSULTAS ---
	
	public int getId() {
		return id;
	}
	public double getSaldo() {
		return saldo;
	}
	
	public void setMoneda(int var) {
		if (var == 1)
			this.moneda = 1;
			else this.moneda = 0;
	}
	
	public String getMoneda() {
		if (moneda == 1)
			return "CLP";
			else return "USD";
	}
	
	
	// --- COMPORTAMIENTO DEPÓSITO ---
	public boolean depositarMonto(double monto) {
		if (monto <= 0) {
			return false;
		}
		saldo += monto;
		return true;
	}
	
	// --- COMPORTAMIENTO RETIRO ---
	public boolean retirarMonto(double monto) {
		if (monto <= 0) {
			return false;
		}
		if (monto > saldo) {
			return false;
		}
		saldo -= monto;
		return true;
	}
	
	
	
	
}
