package supermerecado;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cliente extends Thread {

	//ATRIBUTOS
	public Random aleatorio = new Random();
	public int miCompra;
	public Semaphore cobrameChino;
	//CONSTRUCTOR
	public Cliente(Semaphore cobrameChino) {
		this.miCompra = (aleatorio.nextInt(20))+1; //MAS 1 PARA QUE NO ME COBRE 0, QUE ESO NO SE COBRA...
		this.cobrameChino = cobrameChino;
	}
	//METODO PARA QUE EL CHINO ME COBRE
	public void cobrar() {
		System.out.println("CHINITO DICE: SON "+miCompra+" EULITOS");
		Cajera.dineroCaja += miCompra;
	}
	//LO QUE HECE EL HILO
	public void run() {
		//CHINITO, COBRAME ANDA
		try {
			//CUANDO PUEDA ATENDERME EL CHINO, ME COBRARA
			cobrameChino.acquire();
			System.out.println(this.getName()+" DICE: HOLA CHINITO, SOY "+this.getName()+" ES MI TURNO PARA QUE ME COBRES");
			Thread.sleep(2000);
			cobrar();
			Thread.sleep(2000);
			System.out.println(this.getName()+" DICE: HASTA LUEGO CHINO!!!");
			Thread.sleep(2000);
			System.out.println("CHINITO DICE: ADIOOOOOOOO");
			Thread.sleep(2000);
			System.out.println("CHINITO DICE: SIGLIENTE????\n\n\n");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println(this.getName()+" SE HA DESMAYADO EN LA COLA ESPERANDO QUE EL CHINO ME COBRE"); //INANICION
			e.printStackTrace();
		}
		
		cobrameChino.release();
	}
	
}
