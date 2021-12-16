package donjon.elements.objets;

public class BourseOr extends Objet {
	private int piecesOr;

	public BourseOr() {
	}

	public BourseOr(int PO) {
		this.piecesOr = PO;
		this.setNom("Bourse d'or");
	}

	public BourseOr(String n, String desc) {
		super(n, desc);
		// TODO Auto-generated constructor stub
	}

	public int getPiecesOr() {
		return piecesOr;
	}

	public void setPiecesOr(int piecesOr) {
		this.piecesOr = piecesOr;
	}
}
