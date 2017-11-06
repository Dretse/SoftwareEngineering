
public class Couleur {
	int R,V,B;
	int N=1;
	// N repr�sente de combien de Couleurs est issue celle ci, 
	//pour pouvoir faire des moyennes sans consommer trop de m�moire 
	public Couleur(int Red, int Green, int Blue)
	{
		//Fonction constructeur avec 3 entiers
		R=Red;
		V=Green;
		B=Blue;
	}
	public Couleur(int[] RVB)
	{
		// deuxi�me constructeur pour construire � partir d'un tableau de int
		R=RVB[0];
		V=RVB[1];
		B=RVB[2];
	}
	
	private void initN(int n)
	{
		//pour changer la valeur de n, utilis�e uniquement par la fonction moyenne
		N=n;
	}
	public int getR()
	{
		return R;
	}
	public int getV()
	{
		return V;
	}
	public int getB()
	{
		return B;
	}
	public int getN()
	{
		return N;
	}
	public int[] getRVB()
	{
		int[] a= {R,V,B};
		return a;
	}
	
	public int distance(Couleur C)
	{
		//calcule la distance au carr� entre 2 Couleurs, sur les 3 axes
		int A=(R-C.getR())*(R-C.getR());
		A+=(V-C.getV())*(V-C.getV());
		A+=(B-C.getB())*(B-C.getB());
		return A;
	}
	
	public void printcoul()
	{
		// affiche les 3 coordonn�es d'une couleur
		//uniquement � but de test
		System.out.printf(" %d , %d , %d \n",R, V, B);
	}
	
	public Couleur moyenne(Couleur C)
	{
		//Calcule la moyenne entre 2 couleurs, renvoie une nouvelle couleur,
		//contenant l'information dans N du nombre de couleurs dont elle est issue
		int[] A=new int[3];
		A[0]=(R*N + C.getR()*C.getN())/(N+C.getN());
		A[1]=(V*N + C.getV()*C.getN())/(N+C.getN());
		A[2]=(B*N + C.getB()*C.getN())/(N+C.getN());
		Couleur Retour=new Couleur(A);
		Retour.initN(N+C.getN());
		return Retour;
	}
	public boolean egal(Couleur C)
	{
		// renvoie un booleen pour savoir les 2 couleurs sont �gales
		return(R==C.getR() && V==C.getV() && B==C.getB());
	}
	public int comparaison(int direction, Couleur col){
		//compare 2 couleurs suivan une direction: renvoie 1 si la premiere est plus grande,
		//0 si elles sont �gales
		//-1 si la premiere est plus petite
		switch (direction){
		default: //case 0, par defaut la racine
			if (this.getR()>col.getR()){
				return 1;
			}
			else if(this.getR()==col.getR()){
				return 0;
			}
			else{
				return -1;
			}
		case 1:
			if (this.getV()>col.getV()){
				return 1;
			}
			else if(this.getV()==col.getV()){
				return 0;
			}
			else{
				return -1;
			}
		case 2:
			if (this.getB()>col.getB()){
				return 1;
			}
			else if(this.getB()==col.getB()){
				return 0;
			}
			else{
				return -1;
			}
		}
	}
	
}
