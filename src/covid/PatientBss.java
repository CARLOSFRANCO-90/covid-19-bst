package covid;

public class PatientBss {
	
	PatientNodo root;
	
	int totalAccount=0;
	int patientsNumber=0;
	int fluPatients =0;
	int averagePay;
	
	//sintomas
	String [][] sympthomXCount = new String[2][10];

	private PatientNodo nodo; 
	
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
		 if (arbTemp == null) {
	            arbTemp = nNodo;
	            patientsNumber++;
	            System.out.println("El paciente " +nNodo.patient.name + " se agrego correctamente");
	     }else {
	    	 if (!(nNodo.getPatient().getId()==arbTemp.getPatient().getId())) {
	    		 if (nNodo.getPatient().getId()<arbTemp.getPatient().getId()) {
	    			 arbTemp.setLeft(addPatient(arbTemp.left, nNodo));
	    		 }
	    		 if (nNodo.getPatient().getId()>arbTemp.getPatient().getId()){
		        	 arbTemp.setRight(addPatient(arbTemp.right, nNodo));
		         }	    		 
	    	 }else {
	 			System.out.println("El paciente " + nNodo.getPatient().getName() + " ya se encuentra registrado");
			} 
		}
		 return arbTemp;
	 }
	 
		public boolean find(int id) {
			return find(root, id);
		}
		
		public boolean find(PatientNodo nodo, int id) {
			if(nodo == null) {
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
		
		public PatientNodo returnNodo(PatientNodo nodo, int id) {
			if (nodo == null) {
				return null;
			}
			if (nodo.getPatient().getId()==id) {
				return nodo;
			}else {
				if (nodo.getPatient().getId() <id ) {
					return returnNodo(nodo.right, id);
				}
				return returnNodo(nodo.left, id);
			}
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
		
		//Pago Cuota
		public void pay(PatientNodo root){ 
			if (root == null) {
				System.out.println("No hay pacientes en el sistema");
			}else {
				System.out.println("El paciente " + root.getPatient().getName() 
						+ " tiene " + root.getPatient().getSymptoms().length 
						+ " por lo cual debera pagar " + root.getPatient().getCuotaPagar());
				if (root.getLeft() != null){
			        pay(root.left);
			    }
			    if (root.getRight() != null){
			        pay(root.right);
			    }
			}
		}
		
		//Pacientes fiebre, metodo 
		public int fluCount(PatientNodo root){ 
			if (root == null) {
				System.out.println("No hay pacientes en el sistema");
			}else {
				for (int i = 0; i < root.patient.symptoms.length; i++) {
					if (root.patient.symptoms[i].compareTo("fiebre")==0) {
						fluPatients++;
					}
				}
				if (root.getLeft() != null){
					fluCount(root.left);
				}
				if (root.getRight() != null){
					fluCount(root.right);
				}
			}
			return fluPatients;
		}
		
		//Sintoma más común no me funciona 
		public String[][] sympthomMore(PatientNodo root){ 
			int i;
			int j;
			int t;
			if (root == null) {
				System.out.println("No hay pacientes en el sistema");
			}else {
				for (i = 0; i < root.patient.symptoms.length; i++) {
					j=0;
					t=1;
					int b=0;
					while (t!=0) {
						t = root.patient.symptoms[i].compareTo(sympthomXCount[0][j]);
						if(t==0) {
							int a = Integer.parseInt(sympthomXCount[1][j]);
							sympthomXCount[1][j] = Integer.toString(a++);
							b++;
						}
						j++;
					}
					if (b!=0) {
						sympthomXCount[0][sympthomXCount.length] = root.patient.symptoms[i];
						sympthomXCount[1][sympthomXCount.length] = Integer.toString(1);
					}
				}
				
				if (root.getLeft() != null){
					sympthomMore(root.left);
			    }
			    if (root.getRight() != null){
			    	sympthomMore(root.right);
			    }
			}
			return sympthomXCount;
		}
		
		//Promedio de pago
		public int averagePay (PatientNodo root) {
			return averagePay =sumPay(root)/patientsNumber;
		}
		private int sumPay(PatientNodo root){ 
			if (root == null) {
				System.out.println("No hay pacientes en el sistema");
			}else {
				totalAccount = totalAccount + root.patient.cuotaPagar;
				if (root.getLeft() != null){
					sumPay(root.left);
				}
				if (root.getRight() != null){
					sumPay(root.right);
				}
			}
			return totalAccount;
		}
		

		// Mayor sintomas
		/*public PatientNodo mostSymptoms(PatientNodo root) {
			nodo = root;
			return mostSymptoms(root, nodo);
		}*/
		/*private PatientNodo mostSymptoms(PatientNodo root, PatientNodo nodo){ 
			if (root == null) {
				System.out.println("No hay pacientes en el sistema");
			}else {
				if (nodo.patient.symptoms.length<=root.patient.symptoms.length) {
					nodo = root;
				}
				if (root.getLeft() != null){
			        mostSymptoms(root.left, nodo);
			    }
			    if (root.getRight() != null){
			        mostSymptoms(root.right, nodo);
			    }
			}
			return nodo;
		}*/
		
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
				if(theBiggesOne.symptoms.length < mostSymptomsPatienleft.symptoms.length) {
					theBiggesOne = mostSymptomsPatienleft;
				}
			}
			if(mostSymptomsPatienRight !=null) {
				if(mostSymptomsPatienRight.symptoms.length > theBiggesOne.symptoms.length){
					theBiggesOne = mostSymptomsPatienRight;
				}
			}
			
			return theBiggesOne;
		}
}
