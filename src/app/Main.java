package app;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	
	
	// METODO DE AYUDA PARA LIMPIAR LA PANTALLA DE LA CONSOLA
	public static void SaltoLinea(int cantidadSaltos) {
		for (int i = 0; i < cantidadSaltos; i++) {
			System.out.println();
		}
	}

	public static void main(String[] args) {
		
		//SE INSTANCIA UN OBJETO DE CLASE SCANNER Y UNO DE CLASE GESTORCLIENTES
		Scanner scan = new Scanner(System.in);
		GestorClientes gestor = new GestorClientes();
				
		int opcionAcceso = -1;

		while (opcionAcceso != 0) {

			SaltoLinea(20);
			System.out.println("|	--- MENÚ PRINCIPAL ---		|");
			System.out.println("|	1 .- ACCEDER A SU BILLETERA	|");
			System.out.println("|	2 .- CREAR UN USUARIO		|");
			System.out.println("|	0 .- SALIR DE LA APLICACION	|");
			System.out.print("SELECCIONE UNA OPCION : ");
			opcionAcceso = scan.nextInt();

			if (opcionAcceso == 0) {
				System.out.print("Cerrando aplicación");
				break;
			}
			// --- INGRESO A LA APLICACIÓN SOLICITANDO CREDENCIALES ---
			if (opcionAcceso == 1) {
				System.out.print("INGRESE SU USUARIO	: ");
				String usuario = scan.next();

				System.out.print("INGRESE SU CONTRASEÑA : ");
				String contrasena = scan.next();
				
				
				// --- SE DECLARA LA VARIABLE loginValido QUE REGISTRA SI FUE POSIBLE EL INGRESO---
				boolean loginValido = gestor.validarLogin(usuario, contrasena);
				
				if (loginValido) {
					// --- SI TRUE CONTINUA CON EL CODIGO DE WALLET---
					SaltoLinea(20);
					
					System.out.println("Ha accedido a su cuenta");
					System.out.println("USUARIO ACTUAL : " + usuario);
					Cliente clienteActual = gestor.buscarCliente(usuario);

					while (loginValido) {
						// --- MIENTRAS LA VARIABLE loginValido sea TRUE ---
						SaltoLinea(20);
						
						
						// --- MENÚ PRINCIPAL USUARIO ---
						System.out.println("|	---- USUARIO ACTUAL :" + usuario + " ---	|");
						System.out.println("|	1 .- CREAR NUEVA BILLETERA	|");
						System.out.println("|	2 .- CONSULTA BILLETERAS	|");
						System.out.println("|	3 .- DEPOSITO A CUENTA		|");
						System.out.println("|	4 .- RETIRO A CUENTA		|");
						System.out.println("|	0 .- CERRAR SESIÓN		|");
						int opcionLogged = scan.nextInt();
						
						if (opcionLogged == 0) {
							
							SaltoLinea(20);
							
							System.out.println("Presione c para continuar...");
							scan.next();
							break;
						}				
						
						ArrayList<Cuenta> cuentas = clienteActual.getCuentas();
						
						// --- CREACION DE UNA CUENTA NUEVA --- 
						if (opcionLogged == 1) {
							
							SaltoLinea(20);
							
							System.out.println("| --- CREACION DE BILLETERA --- |");
							System.out.println("En que moneda desea su cuenta");
							System.out.println("1 .- PESO CHILENO (CLP)");
							System.out.println("2 .- DOLAR ESTADOUNIDENSE (USD)");
							System.out.println("0 .- VOLVER A MENÚ PRINCIPAL");
							int opcionCreacionCuenta = scan.nextInt();

							Cuenta cuenta = null;
							
							if (opcionCreacionCuenta == 1) {
								cuenta = new Cuenta(1);
							} else if (opcionCreacionCuenta == 2) {
								cuenta = new Cuenta(0);
							}

							if (cuenta != null) {
								
								SaltoLinea(20);
								
								clienteActual.agregarCuenta(cuenta);
								System.out.println("Cuenta creada con ID: " + cuenta.getId());
								System.out.println("Presione c para continuar...");
								scan.next();
							}
						}
						
						// --- OPCION CONSULTA CUENTAS EXISTENTES ---
						if (opcionLogged == 2) {
							
							SaltoLinea(20);
							
							if (cuentas.isEmpty()) {
								System.out.println("NO EXISTEN BILLETERAS REGISTRADAS");
							} else {
								System.out.println("|	---	BILLETERAS ACTUALES	---	|");
								System.out.println("|N° CUENTA	|SALDO	| MONEDA	|");
								for (Cuenta c : cuentas) {
									System.out.println("|" + c.getId() + " 		| $" + c.getSaldo() + "	|" +  c.getMoneda() + "	|");
								}
							}
							double NUMERO = 100 * Valores.valorUSD;
							System.out.print(NUMERO);
							System.out.println("Presione c para continuar...");
							scan.next();
						}
						
						// --- OPCION DEPOSITO A CUENTAS ---
						if (opcionLogged == 3) {
							
							SaltoLinea(20);
							
							System.out.print("INGRESE EL ID DE SU CUENTA");
							int solicitudCuentaId = scan.nextInt();
							
							Cuenta cuentaDestino = null;
							
							// --- REVISAR SI LA CUENTA EXISTE ---
							for (Cuenta c : cuentas) {
								if (c.getId() == solicitudCuentaId) {
									cuentaDestino = c;
									break;
								}
							}
								// --- SI TRUE PREGUNTAR CUANTO DESEA DEPOSITAR --- 
							if (cuentaDestino != null) {
								System.out.print("MONTO A DEPOSITAR : ");
								double monto = scan.nextDouble();
									
								if (monto > 0 ) {
									cuentaDestino.depositarMonto(monto);
									System.out.println("MONTO DEPOSITADO");
									System.out.println("NUEVO SALDO : $" + cuentaDestino.getSaldo());
									}
									else {
										System.out.println("EL MONTO DEBE SER MAYOR QUE 0");
									}
								}
								// --- SI FALSE DEVOLVER "CUENTA NO EXISTE" --- 
								else {
									System.out.println("NO SE ENCUENTRA UNA CUENTA CON ESE ID");
								}
							}
						
						// --- OPCION RETIRO DE CUENTAS ---
						if (opcionLogged == 4) {
							
							System.out.print("INGRESE EL ID DE LA CUENTA");
							int solicitudCuentaId = scan.nextInt();
							
							Cuenta cuentaDestino = null;
							
							// --- VER SI LA CUENTA EXISTE ---
							for (Cuenta c : cuentas) {
								if (c.getId() == solicitudCuentaId) {
									cuentaDestino = c;
									break;
								}
							}
							
							// SI TRUE PREGUNTAR CUANTO DESEA DEPOSITAR
							if (cuentaDestino != null) {
								
								// SI FALSE DEVOLVER "NO TIENE DINERO SUFICIENTE" 
								// SI TRUE LLAMAR A Cliente.retiroCuenta
							
								System.out.print("MONTO A RETIRAR : ");
								double monto = scan.nextDouble();
								
								// CONSULTAR AL OBJETO LA CANTIDAD DE DINERO
								double saldoActual = cuentaDestino.getSaldo();
								
								if (monto > 0 && saldoActual > monto ) {
									cuentaDestino.retirarMonto(monto);
									
								} else {
									System.out.print("LA CUENTA NO CUENTA CON SALDO SUFICIENTE");
									System.out.println("Presione c para continuar...");
									scan.next();
								
								}
							
							// --- SI NO FUE POSIBLE EL INGRESO VUELVE AL INICIO ---
							} else {
								
								System.out.println("CUENTA NO EXISTE");
								System.out.println("Presione c para continuar...");
								scan.next();
							}
							
							
						}
					}

				} else {
					System.out.println("Usuario o contraseña incorrectos");
					System.out.println("Presione c para continuar...");
					scan.next();
				}
				
			}

			if (opcionAcceso == 2) {
				// --- SISTEMA DE CREACION DE CUENTA EN LA APLICACION ---
				SaltoLinea(10);

				System.out.println("Sistema crear cuentas");
				System.out.println(" ------------------- ");

				System.out.println("|El usuario debe tener: |");
				System.out.println("|Minimo 6 caracteres    |");
				System.out.println("|Maximo 10 caracteres   |");
				System.out.print("INGRESE UN USUARIO : ");
				String nuevoUsuario = scan.next();

				SaltoLinea(20);

				System.out.println("Sistema crear cuentas");
				System.out.println("|Su clave debe tener:   |");
				System.out.println("|Minimo 4 caracteres    |");
				System.out.println("|Maximo 6 caracteres    |");
				System.out.print("INGRESE UNA CONTRASEÑA: ");
				String nuevaContrasena = scan.next();
				
				SaltoLinea(20);
				
				//REVISAR SI EXISTE ALGUN USUARIO CON EL USUARIO INGRESADO
				boolean existeUsuario;
				
				if (gestor.buscarCliente(nuevoUsuario) != null)
					existeUsuario = true;
				else {
					existeUsuario = false;
				}
				
				if (existeUsuario) {
					System.out.println("YA EXISTE UN USUARIO CON ESE NOMBRE");
					System.out.println("Para volver al menú principal presione c...");
					scan.next();
					continue;
					
				} else {
					gestor.agregarCliente(nuevoUsuario, nuevaContrasena);
					System.out.println("CUENTA CREADA EXITOSAMENTE");
					System.out.println("Para volver al menú principal presione c...");
					scan.next();
					continue;
	
				}
				
				
			}
		}

		scan.close();
	}

}
