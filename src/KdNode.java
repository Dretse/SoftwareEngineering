

public class KdNode {
	int P;
	Couleur coul;
	KdNode filsG;
	KdNode filsD;
	public KdNode(Couleur C, int p)
	{
		//Constructeur : P=profondeur du noeud, coul= Couleur
		P=p;
		coul=C;
	}
	public int addpointnode(Couleur RVB)
	{
		//ajoute un point par recursivite et renvoie la profondeur a laquelle il a �t� ajout�
		if(RVB.getRVB()[P%3]==coul.getRVB()[P%3])
		{
			if(RVB.egal(coul))
			{
				return 0;
			}
		}
		if (RVB.getRVB()[P%3]>coul.getRVB()[P%3])
		{
			if(filsD!=null)
			{
				return(filsD.addpointnode(RVB));
			}
			else
			{
				filsD= new KdNode(RVB, P+1);
				return P+1;
			}
		}
		else
		{
			if(filsG!=null)
			{
				return(filsG.addpointnode(RVB));
			}
			else
			{
				filsG= new KdNode(RVB, P+1);
				return P+1;
			}
		}
	}
	public Couleur getcoul()
	{
		//renvoie la couleur associ�e au noeud
		return coul;
	}
	public Couleur[] palnode(Couleur[] pal)
	{
		// Renvoie une palette de couleur de la taille de la palette (vide) fournie
		//d�coupe la palette en 2 � chaque fois en demandant aux fils,
		//si il ne reste qu'une palette de 2 cases: demande sa moyenne � chacun des fils
		int L= pal.length;
		if(L==2)
		{
			pal[0]=filsD.Moy();
			pal[1]=filsG.Moy();
			return pal;
		}
		else
		{
			int i;
			Couleur[] A=new Couleur[L/2];
			Couleur[] B=new Couleur[L/2];
			if(filsG!=null) {A=filsG.palnode(A);}
			else {
				for(i=0;i<L/2;i++)
				{
					A[i]=new Couleur(0,0,0);
				}
			}
			if(filsD!=null) {B=filsD.palnode(B);}
			else
			{
				for(i=0;i<L/2;i++)
				{
					B[i]=new Couleur(0,0,0);
				}
			}
			for(i=0;i<L;i++)
			{
				if(i<L/2) {pal[i]=A[i];}
				else {pal[i]=B[i-L/2];}
			}
			return pal;
			
		}
	}
	public Couleur Moy()
	{
		//Renvoie la couleur moyenne d'un noeud
		if(filsG!=null && filsD!=null)
		{
			Couleur A = (filsD.Moy()).moyenne(coul);
			A=(filsG.Moy()).moyenne(A);
			return A;
		}
		else if(filsD!=null)
		{
			Couleur A = (filsD.Moy()).moyenne(coul);
			return A;
		}
		else if(filsG!=null)
		{
			Couleur A = (filsG.Moy()).moyenne(coul);
			return A;
		}
		else
		{
			return coul;
		}
	}
	public Couleur getNN(Couleur A)
	{
		// Trouve le plus proche voisin d'une couleur dans l'arbre 
		if ((A.getRVB())[P%3] > (coul.getRVB())[P%3])
		{
			if (filsD!=null)
			{
				Couleur CurrentBest = filsD.getNN(A);
				if (CurrentBest.distance(A) > coul.distance(A))
				{
					CurrentBest=coul;
				}
				if(CurrentBest.distance(A) > Math.abs((coul.getRVB())[P%3] - (A.getRVB())[P%3])  && filsG!=null)
				{
					Couleur Alter = filsG.getNN(A);
					if(CurrentBest.distance(A) > Alter.distance(A))
					{
						CurrentBest=Alter;
					}
				}
				return CurrentBest;
			}
			else
			{
				return coul;
			}
		}
		else
		{
			if (filsG!=null)
			{
				Couleur CurrentBest = filsG.getNN(A);
				if (CurrentBest.distance(A) > coul.distance(A))
				{
					CurrentBest=coul;
				}
				if(CurrentBest.distance(A) > Math.abs((coul.getRVB())[P%3] - (A.getRVB())[P%3])  && filsD!=null)
				{
					Couleur Alter = filsD.getNN(A);
					if(CurrentBest.distance(A) > Alter.distance(A))
					{
						CurrentBest=Alter;
					}
				}
				return CurrentBest;
			}
			else
			{
				return coul;
			}
		}
	}
}
