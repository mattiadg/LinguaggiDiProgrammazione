/* Eccezione lanciata quando si prova a inserire un codice fiscale non valido */
package aereo;
public class NotValidCodiceFiscaleException extends RuntimeException{

	public NotValidCodiceFiscaleException(){
		super("Non è un codice fiscale!");
	}

	public NotValidCodiceFiscaleException(String msg){
		super(msg);
	}
}
