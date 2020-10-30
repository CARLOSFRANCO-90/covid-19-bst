package covid;


public class PatientNodo {
	Patient patient;
	PatientNodo left;
	PatientNodo right;
	
	//Constructores
	public PatientNodo(Patient newPatient) {
		this.patient = newPatient;
	}

	public PatientNodo(Patient patient, PatientNodo left, PatientNodo right) {
		super();
		this.patient = patient;
		this.left = left;
		this.right = right;
	}
	
	//Getters and Setters
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public PatientNodo getLeft() {
		return left;
	}

	public void setLeft(PatientNodo left) {
		this.left = left;
	}

	public PatientNodo getRight() {
		return right;
	}

	public void setRight(PatientNodo right) {
		this.right = right;
	}
}
