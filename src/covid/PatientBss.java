package covid;

public class PatientBss {
	
	PatientNodo root;
	
	int totalAccount=0;
	int patientsNumber=0;
	int fluPatients =0;
	
	//Constructores
	public PatientBss(PatientNodo root, int totalAccount, int patientsNumber, int fluPatients) {
		super();
		this.root = root;
		this.totalAccount = totalAccount;
		this.patientsNumber = patientsNumber;
		this.fluPatients = fluPatients;
	}

	public PatientBss() {
		root = null;
	}

	//Getter and Setter
	public PatientNodo getRoot() {
		return root;
	}

	public void setRoot(PatientNodo root) {
		this.root = root;
	}

	public int getTotalAccount() {
		return totalAccount;
	}

	public void setTotalAccount(int totalAccount) {
		this.totalAccount = totalAccount;
	}

	public int getPatientsNumber() {
		return patientsNumber;
	}

	public void setPatientsNumber(int patientsNumber) {
		this.patientsNumber = patientsNumber;
	}

	public int getFluPatients() {
		return fluPatients;
	}

	public void setFluPatients(int fluPatients) {
		this.fluPatients = fluPatients;
	}
	
	 public PatientNodo addPatient(PatientNodo arbTemp, PatientNodo nNodo){
		 if (find(nNodo.getPatient().getId())==false) {
	        if (arbTemp == null) {
	            arbTemp = nNodo;
	            patientsNumber++;
	            System.out.println(nNodo.patient.name + " Paciente se agrego");
	        }
	        else {
	            if (nNodo.getPatient().getId()<arbTemp.getPatient().getId()) {
	                arbTemp.setLeft(addPatient(arbTemp.left, nNodo));
	            }
	            else{
	                arbTemp.setRight(addPatient(arbTemp.right, nNodo));
	            }
	        }  
	        
	        return arbTemp;
	    }else {
			System.out.println("This patient is regitered already");
			return null;
		}
	 }
	 
		public boolean find(int id) {
			return find(root, id);
		}
		
		private boolean find(PatientNodo nodo, int id) {
			if(root == null) {
				return false;	
			}
			if(nodo.patient.id ==id) {
				return true;
			}
			if(nodo.patient.id<id) {
				return find(nodo.right, id);
			}
			return find(nodo.left, id);
		}
		
		// recorrer el arbol 
		public void traverse(PatientNodo root){ 
			if (root == null) {
				System.out.println("Arbol vacio");
			}else {
				System.out.println(root.getPatient().getName());
				if (root.getLeft() != null){
			        traverse(root.left);
			    }
			    if (root.getRight() != null){
			        traverse (root.right);
			    }
			}
		}
		

		// Mayor sintomas
		public Patient mostSymptoms(PatientNodo nodo) {
			if(nodo == null) {
				return null;
			}
			
			Patient mostSymptomsPatienleft = null;
			if(nodo.left != null) {
				mostSymptomsPatienleft = mostSymptoms(nodo.left);
			}
			
			Patient mostSymptomsPatienRight = null;
			if(nodo.right !=null) {
				mostSymptomsPatienRight = mostSymptoms(nodo.right);
			}
			
			Patient theBiggesOne = nodo.patient;
			if(mostSymptomsPatienleft != null) {
				if(theBiggesOne.symptoms.length <= mostSymptomsPatienleft.symptoms.length) {
					theBiggesOne = mostSymptomsPatienleft;
				}
			}
			if(mostSymptomsPatienRight !=null) {
				if(mostSymptomsPatienRight.symptoms.length <= theBiggesOne.symptoms.length){
					theBiggesOne = mostSymptomsPatienRight;
				}
			}
			
			return theBiggesOne;
		}
}
