package application;

import java.util.List;
import java.util.Random;

import application.objects.Addresses;
import application.objects.Customers;
import database.HibernateUtil;
import database.dao.AddressesDAO;

public class MainInserts {
	static String getAlphaNumericString(int min, int max) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";

		int n = (int) (Math.random() * (max - min + 1) + min);
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Random r = new Random();
		HibernateUtil.getInstance().connect("localhost", "3308", "gecompt", "root", "");
		Customers temp;
		
		List<Addresses> addds = AddressesDAO.getInstance().list("17 f%");
		
		for (int i = 0; i < addds.size(); i++) {
			System.out.println(addds.get(i));
		}
		
		/*for (int i = 0; i < 10000; i++) {
			temp = new Customers(AddressesDAO.getInstance().getById((int) (Math.random() * (10000 - 1 + 1) + 1)),
					MainInserts.getAlphaNumericString(10, 20), MainInserts.getAlphaNumericString(10, 20),
					MainInserts.getAlphaNumericString(0, 7), r.nextBoolean(), "");
			System.out.println("" + i + " - " + temp);
			CustomersDAO.getInstance().save(temp);
		}*/
		
		/*
		 * Addresses temp; for (int i = 0; i < 10000; i++) { temp = new Addresses((int)
		 * (Math.random() * (99 - 1 + 1) + 1), MainInserts.getAlphaNumericString((int)
		 * (Math.random() * (20 - 10 + 1) + 10)),
		 * MainInserts.getAlphaNumericString((int) (Math.random() * (13 - 5 + 1) + 5)));
		 * System.out.println("" + i + " - " + temp);
		 * AddressesDAO.getInstance().save(temp); }
		 */

	}

}
