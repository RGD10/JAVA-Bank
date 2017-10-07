package banco;
/**
 * author Ruben Gonzalez Diez
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeMap;

import daw.com.Teclado;

public class AplicacionBanco {

	TreeMap<String,Cuenta>cuentas;
	TreeMap<String,Cliente>clientes;
	
public AplicacionBanco(){
	cuentas=new TreeMap<String,Cuenta>();
	clientes= new TreeMap<String,Cliente>();
	
}
public static void main(String[] args) {
			// TODO Auto-generated method stub
	AplicacionBanco app=new  AplicacionBanco();
	Reloj contar=new Reloj(app);
	contar.start();
	app.run();
	contar.stop();
	System.out.println(contar.toString());
		}

	public void run(){
		leerFichero();
		int opc;
		do{
		opc=mostrarMenu();
		evaluarOpcion(opc);
		}while(opc!=7);
		}
	public int mostrarMenu(){
	int opc;
	do{
	System.out.println("1 crear cuenta bancaria");
	System.out.println("2 ingreso de dinero");
	System.out.println("3 retirar dinero");
	System.out.println("4 realizar transferencia");
	System.out.println("5 consultar saldo");
	System.out.println("6 consultar saldo de banco");
	System.out.println("7 salir y guardar");
	opc=Teclado.leerInt("elige una opcion....");
	}while(opc<1 && opc>7);
	return opc;
	}
	public void evaluarOpcion(int opc){
		
		switch (opc){
		case 1:
			addCuenta();
			break;
		case 2:
			ingresoDinero();
			break;
		case 3:
			retirarDinero();
			break;
		case 4:
			realizarTransferencia();
			break;
		case 5:
			consultarSaldo();
			break;
		case 6:
			saldoTodas();
			break;
		case 7:
			salirGuardar();
			System.out.println("gracias por utilizar el programa");
			break;
		}
		
	}
	public void addCuenta(){
		Cuenta c;
		String seguir;
		int opc;
		do{
			do{
			opc=Teclado.leerInt("1 para particular , 2 para empresa");
			}while(opc<1 || opc>2);
			if(opc==1){
				c=new Particulares();
				
			}else{
				c=new Empresa();
			}
		c.leerDatos();
		cuentas.put(c.getCuenta(), c);
		seguir=Teclado.leerString("desea continuar añadiendo cuentas? s/n");
		}while(seguir.equals("s"));
	}
	public void ingresoDinero(){
		String cuenta=Teclado.leerString("dime el numero de cuenta");
		float saldo;
		if(cuentas.containsKey(cuenta)){
			saldo=Teclado.leerFloat("dime cuanto dinero a ingresar");
			cuentas.get(cuenta).addDinero(saldo);
		}
	}
	public void consultarSaldo(){
		String cuenta= Teclado.leerString("dime el numero de cuenta");
		if(cuentas.containsKey(cuenta)){
			System.out.println(cuentas.get(cuenta).getSaldo());
			
		}
	}
	public void retirarDinero(){
		String cuenta=Teclado.leerString("dime el numero de cuenta");
		float saldo =0;
		Cuenta c=new Cuenta();
		c.calcularAval(saldo);
		saldo=Teclado.leerFloat("dime cuanto dinero a retirar");
		if(cuentas.containsKey(cuenta)){
			cuentas.get(cuenta).reDinero(saldo);
		}
	}
	public void realizarTransferencia(){
		String cuenta=Teclado.leerString("dime la cuenta a realizar la transferencia");
		float saldo=Teclado.leerFloat("dime el saldo para transferir");
		String cuenta1=Teclado.leerString("desde que cuenta quiere hacer la transferencia?");
		if(cuentas.containsKey(cuenta)){
			cuentas.get(cuenta).addDinero(saldo);
			cuentas.get(cuenta1).reDinero(saldo);
		}
	}
	public void salirGuardar(){
		try{
		FileOutputStream bruto= new FileOutputStream("datosBancarios.txt");
		DataOutputStream filtro= new DataOutputStream (bruto);
		
		
		for(Cuenta c:cuentas.values()){
			filtro.writeBytes(c.getClass().getSimpleName()+"\n");
			c.guardarFichero(filtro);
		}
		filtro.close();
		bruto.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		}
	public void saldoTodas(){
		float sum=0;
		for(Cuenta c:cuentas.values()){
			c.getSaldo();
			sum=sum+c.getSaldo();
		}
	}
	public void leerFichero(){
		FileInputStream bruto;
		DataInputStream filtro;
		Cuenta c;
		String cual;
		try{
			bruto = new FileInputStream("datosBancarios.txt");
			filtro= new DataInputStream(bruto);
			while(filtro.available()>0){
				cual=filtro.readLine();
				if(filtro.equals("Particulares")){
				c=new Particulares();		
				}else{
				c=new Empresa();
				}
				c.leerFichero(filtro);
				cuentas.put(c.getCuenta(), c);
			}
			filtro.close();
			bruto.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	}
