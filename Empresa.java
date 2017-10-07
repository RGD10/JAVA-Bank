package banco;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import daw.com.Teclado;

public class Empresa extends Cuenta {
	private boolean alquilado;
	private String nombre,cifc;

	public Empresa(){
	super();
	alquilado=false;
	nombre="";
	cifc="";
	}

	public boolean isAlquilado() {
		return alquilado;
	}

	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCifc() {
		return cifc;
	}

	public void setCifc(String cifc) {
		this.cifc = cifc;
	}
	public void leerDatos(){
		String opc;
		super.leerDatos();
		setNombre(Teclado.leerString("dime el nombre de la empresa"));
		setCifc(Teclado.leerString("dime el cifc de la empresa"));
		opc=Teclado.leerString("cuenta con local alquilado? s/n");
		if(opc.equals("s"))
			alquilado=true;
	
	}
	public void mostrarDatos(){
		super.mostrarDatos();
		System.out.println("el nombre de la empresa es :"+nombre);
		System.out.println("el Cifc de la empresa es : "+cifc);
		if(alquilado){
		System.out.println("la empresa "+nombre+" cuenta con un local alquilado");
		}else{
			System.out.println("la empresa "+nombre+" no cuenta con un local alquilado");
		}
	}
	public void leerFichero(DataInputStream filtro)throws IOException {
	super.leerFichero(filtro);
	this.nombre=filtro.readLine();
	this.cifc=filtro.readLine();
	this.alquilado=filtro.readBoolean();
	}
	public void guardarFichero(DataOutputStream filtro)throws IOException{
	super.guardarFichero(filtro);
	filtro.writeBytes(nombre+"\n");
	filtro.writeBytes(cifc+"\n");
	filtro.writeBoolean(alquilado);
	}
}
