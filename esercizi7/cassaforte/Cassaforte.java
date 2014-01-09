import java.util.Scanner;
import java.io.*;

public class Cassaforte implements Serializable{
	private String messaggio;	//Messaggio segreto
	private String password;	//Password utente
	private int tentativi;
	private boolean accesso;

	/* Crea una nuova cassaforte inserendo password e messaggio */
	public Cassaforte(){
		Scanner scan = new Scanner(System.in);
		boolean ok = true;
		do{
			ok = true;
			System.out.println("Inserisci la tua password");
			System.out.println("Deve essere lunga almeno 6 caratteri e contenere almeno una cifra");
			try{
				this.password = scan.nextLine();
				verificaPassword(this.password);
			}catch(NotValidPasswordException e){
				System.out.println(e.getMessage());
				ok = false;
				continue;
			}
			System.out.print("Inserisci messaggio segreto: ");
			this.messaggio = scan.nextLine();
		}while(ok== false);
		tentativi = 3;
		accesso = true;
	}

	/* Solleva un'eccezione se la parola non è lunga almeno 6 caratteri o non contiene almeno un carattere */
	private void verificaPassword(String psw){
		if(psw.length() < 6 || !psw.matches(".*[0-9].*"))
			throw new NotValidPasswordException();
	}

	/* Stampa il messaggio segreto */
	public void stampaMessaggio(){
		System.out.printf("Il tuo messaggio segreto è...\n%s\n", messaggio);
	}

	/* Cambia Password */
	public void cambiaPassword(){
		Scanner scan = new Scanner(System.in);
		String str1, str2;
		System.out.print("Nuova password: ");
		str1 = scan.nextLine();
		System.out.print("Ripeti password: ");
		str2 = scan.nextLine();
		try{
			verificaPassword(str1);
		}catch(NotValidPasswordException e){
			System.out.println("La password deve essere lunga almeno 6 caratteri e contere almeno una cifra!");
			return;
		}
		if(str1.equals(str2)){
			this.password = str1;
			System.out.println("Password cambiata con successo!");
		}
		else{
			System.out.println("Le due password devono essere uguali!");
		}
	}

	public void chiediPassword(){
		Scanner scan = new Scanner(System.in);
		String str;
		while(true){
			System.out.printf("Inserisci password\ntentativi rimanenti: %d\n", tentativi);
			str = scan.nextLine();
			if(str.equals(password)){
				System.out.println("Password corretta!");
				tentativi = 3;
				accesso = true;
				return;
			}
			else{
				System.out.println("Password errata!");
				tentativi--;
				if(tentativi == 0)
					throw new TentativiTerminatiException();
			}
		}
	}

	public void menu(){
		Scanner scan = new Scanner(System.in);
		int scelta;
		if(accesso == false)
			chiediPassword();
		System.out.println("*********************");
		System.out.println("*  Menu cassaforte **");
		System.out.println("Scegli un opzione:");
		System.out.println("1)Mostra messaggio");
		System.out.println("2)Cambia password");
		System.out.println("0)Esci");
		scelta = scan.nextInt();
		switch(scelta){
			case 1:
				stampaMessaggio();
				break;
			case 2:
				cambiaPassword();
				break;
			case 0:	//Prima di uscire salva l'oggetto in un file
				ObjectOutputStream out = null;
				String str;
				Scanner str_scan = new Scanner(System.in);
				accesso = false;
				System.out.print("Salvataggio dati\nNome del file: ");
				str = str_scan.nextLine();
				try{
					out = new ObjectOutputStream( new FileOutputStream(str));
					out.writeObject(this);
				}catch(IOException e){
					System.out.println("Impossibile salvare la cassaforte!");
				}
				try{
					out.close();
				}catch(IOException e){
					System.out.println("Errore nella chiusura del file!");
					System.exit(1);
				}
				System.out.println("Bye bye!");
				System.exit(0);
				break;
			default:
				throw new NotValidChoiceException();
		}
	}

	public static void main(String[] args){
		Cassaforte c = null;
		Scanner scan = new Scanner(System.in);
		int scelta;
		boolean ok;
		ObjectInputStream in = null;
		do{	//L'utente può scegliere se caricare una cassaforte o crearne una nuova
			ok = true;
			System.out.println("1)Nuova cassaforte\n2)Carica da file");
			System.out.print(": ");
			scelta = scan.nextInt();
			if(scelta < 0 || scelta > 2){
				ok = false;
				System.out.println("Scelta non valida!");
			}
		}while(ok == false);
		if(scelta == 1){
			c = new Cassaforte();
		}
		else if(scelta == 2){	//Apre il file e carica l'oggetto
			Scanner str_scan = new Scanner(System.in);
			System.out.print("Nome file: ");
			try{
				in = new ObjectInputStream(new FileInputStream(str_scan.nextLine()));
			}catch(IOException e){
				System.out.println("Il file non esiste!");
				System.exit(1);
			}
			try{
				c = (Cassaforte) in.readObject();
			}catch(IOException e){
				System.out.println("Errore nella lettura del file!");
			}catch(ClassNotFoundException e){
				System.out.println("Il file non contiene dati cassaforte!");
			}
			try{
				in.close();
			}catch(IOException e){
				System.out.println("Errore nella chiusura del file!");
				System.exit(1);
			}
		}
		while(true){
			c.menu();
		}
	}
}

class NotValidPasswordException extends RuntimeException{
	
	public NotValidPasswordException(){
		super("password non valida!");
	}

	public NotValidPasswordException(String msg){
		super(msg);
	}
}

class NotValidChoiceException extends RuntimeException{
	
	public NotValidChoiceException(){
		super("Scelta non valida!");
	}

	public NotValidChoiceException(String msg){
		super(msg);
	}
}

class TentativiTerminatiException extends RuntimeException{

	public TentativiTerminatiException(){
		super("Non è più possibile effettuare l'accesso!");
	}

	public TentativiTerminatiException(String msg){
		super(msg);
	}
}
