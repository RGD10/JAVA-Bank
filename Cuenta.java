package banco;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.TreeMap;

import daw.com.Teclado;

public class Cuenta {
private String cuenta;
private float saldo;
private TreeMap<String,Cliente> titulares;

public Cuenta(){
	cuenta="";
	saldo=0;
	titulares= new TreeMap<String,Cliente>();	
}
public Cuenta(Cuenta c){
	this.cuenta=c.cuenta;
	this.saldo=c.saldo;
	this.titulares=(TreeMap<String,Cliente>)titulares.clone();
}
public String getCuenta() {
	return cuenta;
}
public void setCuenta(String cuenta) {
	this.cuenta = cuenta;
}
public float getSaldo() {
	return saldo;
}
public void setSaldo(float saldo) {
	this.saldo = saldo;
}
public TreeMap<String, Cliente> getTitulares() {
	return titulares;
}
public void setTitulares(TreeMap<String, Cliente> titulares) {
	this.titulares = titulares;
}
public void leerDatos(){
	Cliente c;
	String seguir;
	do{
	c=new Cliente();
	c.leerDatos();
	if(titulares.containsKey(c.getNif())){
	System.out.println("el Nif que has introducido ya existe");
	}else{
	titulares.put(c.getNif(), c);
	System.out.println("Cliente añadido exitosamente");
	}
	seguir=Teclado.leerString();
	}while(seguir.equals("s"));
	setCuenta(Teclado.leerString("dime el numero de cuenta: "));
	setSaldo(Teclado.leerFloat("dime el saldo de la cuenta: "));
}
public void mostrarDatos(){
	for(Cliente cliente:titulares.values())
	{
		cliente.mostrarDatos();
	}
	System.out.println("el numero de la cuenta es:"+cuenta);
	System.out.println("el saldo de la cuenta es:"+saldo);
	}

public void guardarFichero(DataOutputStream filtro)throws IOException{
	for(Cliente c:titulares.values()){
		c.guardarFichero(filtro);
	}
	filtro.writeBytes(cuenta+"\n");
	filtro.writeFloat(saldo);
	
}
public void leerFichero(DataInputStream filtro)throws IOException{
	Cliente c;
	c=new Cliente();
	c.leerFichero(filtro);
	this.cuenta=filtro.readLine();
	this.saldo=filtro.readFloat();
	
	titulares.put(c.getNif(), c);
	
}
public void addDinero(float saldo){
	this.saldo+=saldo;
}
public void reDinero(Float saldo){
	this.saldo-=saldo;
}
public void calcularAval(float opc){
	float resultado;
	float aval;
	aval=0;
	for(Cliente c:titulares.values()){
		aval=aval+c.getAval();
	}
	resultado=opc+aval/2;
}
}
