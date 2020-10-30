package covid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;

public class Program {
	public static PatientBss treePatient = new PatientBss();
	
	public static void dataAsing(String nameFile) throws IOException {
		int numberPatients;
		
		FileReader fr;
		BufferedReader br;
		String line;
		String[] data;
		
		PatientBss tp = new PatientBss();
		PatientNodo root = null;
		PatientNodo objPatientNodo;
		Patient objPatient = new Patient();
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {			
			//File Extraction
			fr = new FileReader(nameFile);
			br = new BufferedReader(fr);
			
			//Extraer el número de pacientes
			line = br.readLine();
			data = line.split(" ");
			numberPatients = Integer.parseInt(data[0]);
			
			System.out.println("El numero de pacientes es: "+numberPatients);
			
			//Add Patients
			for (int i = 0; i < numberPatients; i++) {
				line = br.readLine();
				data = line.split(" ");
				int id = Integer.parseInt(data[0]);	
				String name = data[1];
				String symptoms[] = new String[(data.length-2)];
				int flag = 2;
				int j = 0;
				while(flag<data.length) {
					symptoms[j] = data[flag];
					j++;
					flag++;
				}
				int symptomPay = symptoms.length * 100;
				objPatient = new Patient(id, name, symptoms, symptomPay);
				objPatientNodo = new PatientNodo(objPatient);
				root = tp.addPatient(root, objPatientNodo);
			}
		}catch (IOException e) {
		}
		tp.traverse(root);
		int valor = 1;
		do {
			System.out.println("Desea revisar los sintomas de un paciente");
			System.out.println("1- SI");
			System.out.println("2- NO");
			valor = Integer.parseInt(bf.readLine());
			if (valor ==1) {
				System.out.println("Ingrese la ID del paciente que desea: ");
				int id = Integer.parseInt(bf.readLine());
				if (tp.find(root, id)==true) {
					PatientNodo nodo = tp.returnNodo(root, id);
					System.out.println("El usuario " + nodo.getPatient().getName() + " tiene " + nodo.patient.symptoms.length + " sintomas los cuales son:");
					for (int i = 0; i < nodo.patient.symptoms.length; i++) {
						System.out.println(nodo.patient.symptoms[i]);
					}
					System.out.println("Debera pagar " + nodo.getPatient().getCuotaPagar());
				}else {
					System.out.println("Este usuario no se encuentra registrado");					
				}
			}
		}while(valor == 1);
		
		System.out.println("=========================================================================");
		tp.pay(root);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		JFileChooser file = new JFileChooser();	
		file.showOpenDialog(null);
		String nameFile = file.getSelectedFile().getPath();
		
		dataAsing(nameFile);		
	}	
}

