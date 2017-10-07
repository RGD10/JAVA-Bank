package banco;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import daw.com.Teclado;

public class Cliente {
private String nif,nombre,telf;
private float aval;


public Cliente(){
	nif="";
	nombre="";
	telf="";
	aval=0;
}
public Cliente (Cliente copia){
	this.nif=copia.nif;
	this.nombre=copia.nombre;
	this.telf=copia.telf;
	this.aval=copia.aval;
}
public String getNif() {
	return nif;
}
public void setNif(String nif) {
	this.nif = nif;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getTelf() {
	return telf;
}
public void setTelf(String telf) {
	this.telf = telf;
}
public float getAval() {
	return aval;
}
public void setAval(float aval) {
	if(aval<0)
	aval=0;
	this.aval = aval;
}
public void leerDatos(){
	setNif(Teclado.leerString("dime el Nif del Cliente: "));
	setNombre(Teclado.leerString("dime el Nombre del cliente:"));
	setTelf(Teclado.leerString("dime el telf del cliente:"));
	setAval(Teclado.leerFloat("dime el dinero del aval:"));
}
public void mostrarDatos(){
	System.out.println("Nif: "+nif);
	System.out.println("Nombre: "+nombre);
	System.out.println("telefono: "+telf);
	System.out.println("avalal: "+aval);
}
public void leerFichero(DataInputStream filtro)throws IOException{
	this.nif=filtro.readLine();
	this.nombre=filtro.readLine();
	this.telf=filtro.readLine();
	setAval(filtro.readFloat());
}
public void guardarFichero(DataOutputStream filtro)throws IOException{
	filtro.writeBytes(nif+"\n");
	filtro.writeBytes(nombre+"\n");
	filtro.writeBytes(telf+"\n");
	filtro.writeFloat(aval);
}
public boolean equals(Object anObject){
	Cliente c=(Cliente) anObject;
	return this.nif.equals(c.nif);
}
}
