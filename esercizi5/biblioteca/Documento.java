public abstract class Documento{
	private String locazione;

	public String getLocazione(){
		return locazione;
	}

	public void setLocazione(String l){
		this.locazione = l;
	}

	public abstract String getInfo();
}
