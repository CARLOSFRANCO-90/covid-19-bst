package covid;

import java.util.Arrays;

public class Patient {
			
	public int id;
	public String name;
	public String [] symptoms;
	public int cuotaPagar;
		
	//Constructores
	public Patient(int id, String name, String[] symptoms, int cuotaPagar) {
		this.id = id;
		this.name = name;
		this.symptoms = symptoms;
		this.cuotaPagar = cuotaPagar;
	}
	
	//Getters an Setters
public Patient() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String[] symptoms) {
		this.symptoms = symptoms;
	}

	public int getCuotaPagar() {
		return cuotaPagar;
	}

	public void setCuotaPagar(int cuotaPagar) {
		this.cuotaPagar = cuotaPagar;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", symptoms=" + Arrays.toString(symptoms) + ", cuotaPagar="
				+ cuotaPagar + "]";
	}
}
