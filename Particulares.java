package banco;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;


import daw.com.Teclado;

public class Particulares extends Cuenta {
	private boolean tarjeta;

public Particulares(){
	super();
	tarjeta=false;
	
}
public boolean isTarjeta() {
	return tarjeta;
}

public void setTarjeta(boolean tarjeta) {
	this.tarjeta = tarjeta;
}
public void leerDatos(){
	String opc;
	super.leerDatos();
	opc=Teclado.leerString("tiene tarjeta s/n?");
	if(opc.equals("s")){
		tarjeta=true;
	}
}
public void mostrarDatos(){
	super.mostrarDatos();
	if(tarjeta=true){
		System.out.println("el cliente cuenta con tarjeta");
	}else{
	System.out.println("el cliente no cuenta con tarjeta");
	}
}
public void leerFichero(DataInputStream filtro)throws IOException {
	super.leerFichero(filtro);
	tarjeta=filtro.readBoolean();
}
public void guardarFichero(DataOutputStream filtro)throws IOException {
	super.guardarFichero(filtro);
	filtro.writeBoolean(tarjeta);
}
}

