import java.io.Serializable;

public abstract class Documento implements Serializable{
	private String locazione;

	public String getLocazione(){
		return locazione;
	}

	public void setLocazione(String l){
		this.locazione = l;
	}

	public abstract String getInfo();
}
