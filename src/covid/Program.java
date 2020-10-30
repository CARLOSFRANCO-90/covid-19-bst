package covid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

public class Program {
	public static PatientBss treePatient = new PatientBss();
	
	public static void dataAsing(String nameFile) {
		int numberPatients;
		
		FileReader fr;
		BufferedReader br;
		String line;
		String[] data;
		
		PatientBss tp = new PatientBss();
		PatientNodo root = null;
		PatientNodo objPatientNodo;
		Patient objPatient = new Patient();
		
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
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		JFileChooser file = new JFileChooser();	
		file.showOpenDialog(null);
		String nameFile = file.getSelectedFile().getPath();
		
		dataAsing(nameFile);		
	}	
}

