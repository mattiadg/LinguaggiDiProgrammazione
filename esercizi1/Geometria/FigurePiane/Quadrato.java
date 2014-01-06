package Geometria.FigurePiane;

public class Quadrato{
	private double lato;

	public Quadrato(){
		this(1);
	}

	public Quadrato(double l){
		this.lato = l;
	}

	public double calcolaArea(){
		return lato * lato;
	}

	public void raddoppiaLato(){
		lato *= 2;
	}

	public static void main(String args[]){
		Quadrato q = new Quadrato(2.0);
		System.out.println(q.calcolaArea());
		q.raddoppiaLato();
		System.out.println(q.calcolaArea());
	}
}
