package app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BilleteraTest {
	
	private Cuenta c;
	@BeforeEach
	void configuracion()
	{
		c = new Cuenta(1);		 
//		c.depositarMonto(500.0);
	}
	
	@Test
	void testRetiro() {
		c.depositarMonto(500.0);
		c.retirarMonto(250.0);
		double saldo = c.getSaldo();
		System.out.println("Saldo testRetiro : " + saldo);
		
	}
	
	@Test
	void testDeposito() {
		c.depositarMonto(500.0);
		c.depositarMonto(250.0);
		double saldo = c.getSaldo();
		System.out.println("Saldo testDeposito : " + saldo);
		
	}
	
	@Test
	void testMoneda() {
		c.setMoneda(0);
		String resultado = c.getMoneda();
		System.out.println("Moneda testMoneda : " + resultado);
		assertEquals("USD", resultado);
	}
}
