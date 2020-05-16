package application.objects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Categories extends ObjectCOR implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private int id;
	private Categories categories;
	private String name;
	private boolean referencedWeb;
	private boolean referencedSellers;
	private Set<Products> productses = new HashSet<Products>(0);
	private List<Categories> categorieses = new ArrayList<Categories>(0);

	public Categories() {
	}

	public Categories(int id, String name, boolean referencedWeb, boolean referencedSellers) {
		this.id = id;
		this.name = name;
		this.referencedWeb = referencedWeb;
		this.referencedSellers = referencedSellers;
	}
	public Categories(String name, Categories categories, boolean referencedWeb, boolean referencedSellers) {
		this.categories = categories;
		this.name = name;
		this.referencedWeb = referencedWeb;
		this.referencedSellers = referencedSellers;
	}
	public Categories(int id, Categories categories, String name, boolean referencedWeb, boolean referencedSellers,
			Set<Products> productses, List<Categories> categorieses) {
		this.id = id;
		this.categories = categories;
		this.name = name;
		this.referencedWeb = referencedWeb;
		this.referencedSellers = referencedSellers;
		this.productses = productses;
		this.categorieses = categorieses;
	}

	@Override
	public String toString() {
		return "[Id :" + id + "; Name:" + name + "; web:"+ referencedSellers +"; sellers: "+referencedSellers +"]";

	}
	
	//Ne fonctionne pas wtf
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;

		if(!(obj instanceof Categories)) return false;

		Categories temp = (Categories)obj;
		
		if(this.id == temp.id) return true;
		if(this.name.equals(temp.name) && this.referencedSellers == temp.referencedSellers && this.referencedWeb == temp.referencedWeb) return true;
		return false;
	}
	
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Categories getCategories() {
		return this.categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isReferencedWeb() {
		return this.referencedWeb;
	}

	public void setReferencedWeb(boolean referencedWeb) {
		this.referencedWeb = referencedWeb;
	}

	public boolean isReferencedSellers() {
		return this.referencedSellers;
	}

	public void setReferencedSellers(boolean referencedSellers) {
		this.referencedSellers = referencedSellers;
	}

	public Set<Products> getProductses() {
		return this.productses;
	}

	public void setProductses(Set<Products> productses) {
		this.productses = productses;
	}

	public List<Categories> getCategorieses() {
		return this.categorieses;
	}

	public void setCategorieses(List<Categories> categorieses) {
		this.categorieses = categorieses;
	}

}
