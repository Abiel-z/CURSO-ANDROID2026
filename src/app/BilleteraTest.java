package app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BilleteraTest {
	
	private Cuenta c;
	private Cliente user;
	@BeforeEach
	void configuracion()
	{
		c = new Cuenta(1);
		user = new Cliente("admin" , "1234");
//		c.depositarMonto(500.0);
	}
	
	@Test //DEPOSITO SIMPLE
    void testDepositoSimple() {
        c.depositarMonto(500.0);
        assertEquals(500.0, c.getSaldo());
    }

    @Test //MULTIPLES DEPOSITOS
    void testDepositoMultiple() {
        c.depositarMonto(500.0);
        c.depositarMonto(250.0);
        assertEquals(750.0, c.getSaldo());
    }
   
    @Test //MULTIPLES OPERACIONES
    void testRetiroValido() {
        c.depositarMonto(500.0);
        c.retirarMonto(200.0);
        assertEquals(300.0, c.getSaldo());
    }

    @Test //RETIRO DEL SALDO COMPLETO
    void testRetiroTotal() {
        c.depositarMonto(400.0);
        c.retirarMonto(400.0);
       
        assertEquals(0.0, c.getSaldo());
    }
	 
    @Test // NO SE PUEDE REALIZAR POR LO QUE EL SALDO NO DEBERIA CAMBIAR
    void testRetiroMayorAlSaldo() {
        c.depositarMonto(200.0);
        c.retirarMonto(500.0);

        
        assertEquals(200.0, c.getSaldo());
    }

    @Test // NO SE PUEDE REALIZAR POR LO QUE EL SALDO NO DEBERIA CAMBIAR
    void testDepositoNegativo() {
        c.depositarMonto(-100.0);
        assertEquals(0.0, c.getSaldo());
    }
    
	@Test // REVISAR CAMBIO CORRECTO DE MONEDAS EN CUENTAS
	
	void testMonedaCLPtoUSD() {
		c.setMoneda(1);
		c.cambiarMoneda();
		String resultado = c.getMoneda();
		System.out.println("Moneda testMonedaCLPtoUSD : " + resultado);
		assertEquals("USD", resultado);
	}
	
	@Test
	void testMonedaUSDtoCLP() {
		c.setMoneda(0);
		c.cambiarMoneda();
		String resultado = c.getMoneda();
		System.out.println("Moneda testMoneda : " + resultado);
		assertEquals("CLP", resultado);
	}
	
	
	
	
	
	// TEST USUARIO
		
	@Test // REVISAR ACCESO VALIDANDO CONTRASEÑA
	void testAccesoUsuarioInvalido() {
		boolean resultado = user.validarContrasena("12345");
		assertEquals(false, resultado);
	}
	void testAccesoUsuarioValido() {
		boolean resultado = user.validarContrasena("1234");
		assertNotEquals(false, resultado);
	}	
}
