package com.bridgeLabz;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opencsv.CSVParserWriter;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class AddressBook implements AddressBookInterface {
	Scanner s = new Scanner(System.in);
	ArrayList<Person> list = new ArrayList<Person>();

	public void operation(ArrayList<Person> list) {
		boolean status = true;
		do {
			System.out.println("Choose Operation you want to do");
			System.out.println(
					"1. Add\t2. Edit\t3. SortById\t4. SortByZip\t5. Show\t6.WriteIntofile\t7.ReadFromeFile\t8.WriteIntoCSV \t9.ReadFromCsv\t10.WriteJson\t11. Back");
			switch (s.nextInt()) {
			case 1:
				add();
				break;
			case 2:
				edit();
				break;
			case 3:
				sortByLastname();
				break;
			case 4:
				sortByZip();
				break;
			case 5:
				print();
				break;
			case 6:
				writeIntoFile();
				break;
			case 7:
				readFromFile();
				break;
			case 8:
				writeIntoCSV();
				break;
			case 9:
				ReadFromCSV();
				break;
			case 10:
				WriteJson();
				break;
			case 11:
				status = false;
				break;
			}
		} while (status);
	}

	public void add() {
		Person person = new Person();
		Address address = new Address();
		System.out.println("Enter the First name:");
		String fname = s.next();
		person.setFname(fname);

		System.out.println("Enter the Last name:");
		String lname = s.next();
		person.setLname(lname);

		System.out.println("Enter the Phone Number:");
		Long phone = s.nextLong();
		person.setPhonenumber(phone);

		System.out.println("Enter the City:");
		String city = s.next();
		address.setCity(city);

		System.out.println("Enter the Zip:");
		long zip = s.nextLong();
		address.setZip(zip);

		System.out.println("Enter the State:");
		String state = s.next();
		address.setState(state);
		person.setAddress(address);
	
		list.add(person);

	}

	public void edit() {
		System.out.println("Edit the contact using First Name: ");
		System.out.println("Enter your First name:");
		String fname = s.next();

		Iterator<Person> iterator = list.listIterator();

		while (iterator.hasNext()) {
			Person person = iterator.next();

			if (fname.equals(person.getFname())) {
				Address address = person.getAddress();
				System.out.println("Choose field you want to add:");
				System.out.println("1.Last Name\t2.Phone Number\t3.City\t4.Zip\t5. State");
				switch (s.nextInt()) {
				case 1:
					System.out.println("Re-Correct your lastname");
					person.setLname(s.next());
					break;
				case 2:
					System.out.println("Re-Correct your Phone Number");
					person.setPhonenumber(s.nextLong());
					break;
				case 3:
					System.out.println("Re-Correct your City");
					address.setCity(s.next());
					break;
				case 4:
					System.out.println("Re-Correct your Zip");
					address.setZip(s.nextLong());
					break;
				case 5:
					System.out.println("Re-Correct your State");
					address.setState(s.next());
					break;
				}

			}
		}

	}

	public void delete() {
		System.out.println("Delete the contact using First Name: ");
		System.out.println("Enter your First name:");
		String fname = s.next();

		Iterator<Person> iterator = list.listIterator();
		while (iterator.hasNext()) {
			Person person = iterator.next();

			if (fname.equals(person.getFname())) {
				list.remove(person);
			}
		}
	}

	public ArrayList<Person> list() {
		return list;
	}

	public void print() {
		Iterator<Person> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public static void main(String[] args) {
		AddressBook ab = new AddressBook();
		ab.operation(null);
		ab.add();
		ab.edit();
		ab.print();
		ab.delete();
		ab.print();
		ab.writeIntoFile();
		ab.writeIntoCSV();

	}

	@Override
	public void sortByLastname() {
		Collections.sort(list, Sort.compareLastName);

	}

	@Override
	public void sortByZip() {
		Collections.sort(list, Sort.compareZip);

	}

	public static class Sort {
		static Comparator<Person> compareLastName = new Comparator<Person>() {
			public int compare(Person one, Person two) {
				return one.getLname().compareTo(two.getLname());
			}
		};

		static Comparator<Person> compareZip = new Comparator<Person>() {
			public int compare(Person s1, Person s2) {
				Address address1 = s1.getAddress();
				Address address2 = s2.getAddress();
				int zip1 = (int) address1.getZip();
				int zip2 = (int) address2.getZip();
				return zip1 - zip2;
			}
		};
	}

	public void writeIntoFile() {

		Path path = Paths.get(
				"C:\\Users\\Administrator\\Desktop\\AddressBookJava-\\AddressBook!\\src\\main\\resources\\AddressBook.txt");

		try {
			String data = "";
			for (Person person : list) {
				data += person.toString();
				System.out.println("");
			}
			Files.write(path, data.getBytes());

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void readFromFile() {
		Path path = Paths.get(
				"C:\\Users\\Administrator\\Desktop\\AddressBookJava-\\AddressBook!\\src\\main\\resources\\AddressBook.txt");

		try {
			System.out.println(Files.readAllLines(path));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void writeIntoCSV() {
		try {
			CSVWriter csv = new CSVWriter(new FileWriter(
					"C:\\Users\\Administrator\\Desktop\\AddressBookJava-\\AddressBook!\\src\\main\\resources\\AddressBookCSV.csv"));

			for (Person person : list) {

				String[] data = new String[] { list.toString() };
				csv.writeNext(data);
			}
			csv.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void ReadFromCSV() {
		try {
			CSVReader reader = new CSVReader(new FileReader(
					"C:\\Users\\Administrator\\Desktop\\AddressBookJava-\\AddressBook!\\src\\main\\resources\\AddressBookCSV.csv"));
			String[] nextLine;
			// reads one line at a time
			while ((nextLine = reader.readNext()) != null) {
				for (String token : nextLine) {
					System.out.print(token);
				}
				System.out.print("\n");
			}

		} catch (Exception e) {

		}
	}

	public void WriteJson() {

		JSONArray personList = new JSONArray();
		personList.add(list);

		try {
			FileWriter file = new FileWriter(
					"C:\\Users\\Administrator\\Desktop\\AddressBookJava-\\AddressBook!\\src\\main\\resources\\person.json");
			// We can write any JSONArray or JSONObject instance to the file
			file.write(personList.toJSONString());
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
