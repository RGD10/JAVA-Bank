package banco;

public class Reloj extends Thread{
	private int contador;
	private AplicacionBanco banco;
	
	public Reloj (AplicacionBanco banco)
	{
		contador = 0;
		this.banco = banco;
		
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	

	public void run ()
	{
		while( true )
		{
		contador+=1;
		
		if (contador % 30 == 0)
		{
			banco.salirGuardar();
			System.out.println("Guardado automatico del fichero");
			System.out.println(toString());
		}
		try {
			sleep( 1000 );
		} 
		catch( InterruptedException e ) {
			System.out.println("Error Reloj");
			e.printStackTrace();
		}
	}

	}
	public String toString()
	{
		int horas, minutos, segundos;
			
		int aux;
		
		String reloj;
		
		aux = contador;
		horas = aux / 3600;
		
		aux -= horas * 3600;
		
		minutos = aux / 60;
		
		aux-= minutos *60;
		
		segundos =aux;
		
		reloj = horas + ":" + minutos + ":" + segundos;
		
		return reloj;
	}
}
