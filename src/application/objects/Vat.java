package application.objects;

import java.math.BigDecimal;

public class Vat implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private byte id;
	private BigDecimal pourcentage;

	public Vat() {
	}

	public Vat(byte id, BigDecimal pourcentage) {
		this.id = id;
		this.pourcentage = pourcentage;
	}
	
	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public BigDecimal getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(BigDecimal pourcentage) {
		this.pourcentage = pourcentage;
	}
	
	@Override
	public boolean equals(Object obj) {
	if(this == obj) return true;
	if(!(obj instanceof Vat)) return false;
	if(this.id !=((Vat)obj).id) return false;
	if(!this.pourcentage.equals(((Vat)obj).pourcentage)) return false;
	
	return true;
	
	}
	
	@Override
	public String toString() {
		return pourcentage.toString();

	}

}
