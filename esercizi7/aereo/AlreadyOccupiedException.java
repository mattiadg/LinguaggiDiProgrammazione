/* Eccezione lanciata quando si prova a occupare un posto già occupato*/
package aereo;

public class AlreadyOccupiedException extends RuntimeException{
	
	public AlreadyOccupiedException(){
		super("Il posto è già occupato!");
	}

	public AlreadyOccupiedException(String msg){
		super(msg);
	}
}
