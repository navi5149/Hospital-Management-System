package kon.atha.HospitalManagement.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import kon.atha.HospitalManagement.Entity.Admission;
import kon.atha.HospitalManagement.Entity.Appointment;
import kon.atha.HospitalManagement.Entity.Bed;
import kon.atha.HospitalManagement.Entity.Bill;
import kon.atha.HospitalManagement.Entity.Department;
import kon.atha.HospitalManagement.Entity.Doctor;
import kon.atha.HospitalManagement.Entity.LabTest;
import kon.atha.HospitalManagement.Entity.Patient;
import kon.atha.HospitalManagement.Entity.Payment;
import kon.atha.HospitalManagement.Entity.Prescription;
import kon.atha.HospitalManagement.Entity.PrescriptionMedicine;
import kon.atha.HospitalManagement.Entity.TestOrder;
import kon.atha.HospitalManagement.controller.HospitalController;

public class HospitalView {

	Scanner s = new Scanner(System.in);

	private HospitalController controller;

	public void setController(HospitalController controller) {
		this.controller = controller;
	}

	boolean BackToMainPage = true;
	boolean running = true;

	public void showView() {

		while (running) {

			System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
			System.out.println("1. Patient Management");
			System.out.println("2. Doctor Management");
			System.out.println("3. Department Management");
			System.out.println("4. Appointment Management");
			System.out.println("5. Prescription Management");
			System.out.println("6. Admission Management");
			System.out.println("7. Bed Management");
			System.out.println("8. Bill Management");
			System.out.println("9. Payment Management");
			System.out.println("10. Laboratory Management");
			System.out.println("11. Exit");

			System.out.print("Enter your choice: ");

			int choice = s.nextInt();
			s.nextLine();

			switch (choice) {
			case 1: {
				boolean BackToMainPage = true;
				while (BackToMainPage) {
					System.out.println("\n===== PATIENT MANAGEMENT =====");
					System.out.println("1. add patient details");
					System.out.println("2. view patient details");
					System.out.println("3. update patient details");
					System.out.println("4. delete the patient ");
					System.out.println("5. view all patients");
					System.out.println("6. back");
					System.out.println("enter your choice: ");
					int patientchoice = s.nextInt();
					s.nextLine();
					switch (patientchoice) {
					case 1: {
						controller.addPatient();
					}
						break;
					case 2: {
						controller.viewPatient();
					}
						break;
					case 3: {
						controller.updatePatient();
					}
						break;
					case 4: {
						controller.deletePatient();
					}
						break;
					case 5: {
						controller.viewAllPatient();
					}
						break;
					case 6: {
						BackToMainPage = false;
					}
						break;

					default:
						System.out.println("enter correct choice");

					}
				}
			}
				break;

			case 2: {
				boolean BackToMainPage = true;
				while (BackToMainPage) {
					System.out.println("\n===== DOCTOR MANAGEMENT =====");
					System.out.println("1. add doctor details");
					System.out.println("2. view doctor details");
					System.out.println("3. update doctor details");
					System.out.println("4. delete the doctor ");
					System.out.println("5. view all doctors");
					System.out.println("6. back");
					System.out.println("enter your choice: ");
					int doctorchoice = s.nextInt();
					s.nextLine();
					switch (doctorchoice) {
					case 1: {

						controller.addDoctor();
					}
						break;
					case 2: {

						controller.viewDoctor();
					}
						break;
					case 3: {
						controller.updateDoctor();
					}
						break;
					case 4: {

						controller.deleteDoctor();
					}
						break;
					case 5: {
						controller.viewAllDoctors();

					}
						break;
					case 6: {
						BackToMainPage = false;

					}
						break;
					default:
						System.out.println("Enter correct choice");

					}

				}

			}
				break;
			case 3: {
				boolean BackToMainPage = true;
				while (BackToMainPage) {
					System.out.println("\n===== DEPARTMENT MANAGEMENT =====");
					System.out.println("1. add department details");
					System.out.println("2. view department details");
					System.out.println("3. update department details");
					System.out.println("4. delete the department ");
					System.out.println("5. view all department");
					System.out.println("6. view doctors in department");
					System.out.println("7. back");
					System.out.println("enter your choice: ");
					int departmentchoice = s.nextInt();
					s.nextLine();
					switch (departmentchoice) {
					case 1: {
						controller.addDepartment();
					}
						break;
					case 2: {

						controller.viewDepartment();
					}
						break;
					case 3: {
						controller.updateDepartment();
					}
						break;
					case 4: {

						controller.deleteDepartment();
					}
						break;
					case 5: {
						controller.viewAllDepartment();
					}
						break;
					case 6: {
						controller.viewDoctorsInDepartment();
					}
						break;
					case 7: {
						BackToMainPage = false;

					}
						break;
					default:
						System.out.println("Enter correct choice");

					}

				}
			}
				break;
			case 4: {
				boolean BackToMainPage = true;
				while (BackToMainPage) {
					System.out.println("\n===== APPOINTMENT MANAGEMENT =====");
					System.out.println("1. book appointment");
					System.out.println("2. view appointment details");
					System.out.println("3. update appointment details");
					System.out.println("4. cancel the appointment ");
					System.out.println("5. view all appointments");
					System.out.println("6. view patient appointments");
					System.out.println("7. view doctor appointments");
					System.out.println("8. back");
					System.out.println("enter your choice: ");
					int appointmentchoice = s.nextInt();
					s.nextLine();
					switch (appointmentchoice) {
					case 1: {
						controller.bookAppointment();
					}
						break;
					case 2: {
						controller.viewAppointment();
					}
						break;
					case 3: {
						controller.updateAppointment();
					}
						break;
					case 4: {
						controller.cancelAppointment();
					}
						break;
					case 5: {
						controller.viewAllAppointment();
					}
						break;
					case 6: {
						controller.AppointmentInPatient();
					}
						break;
					case 7: {
						controller.AppointmentInDoctor();
					}
						break;
					case 8: {
						BackToMainPage = false;
					}
						break;
					default:
						System.out.println("Enter correct choice");
					}

				}

			}
				break;
			case 5: {
				boolean BackToMainPage = true;
				while (BackToMainPage) {
					System.out.println("\n===== PRESCRIPTION MANAGEMENT =====");
					System.out.println("1. create prescription");
					System.out.println("2. view prescription details");
					System.out.println("3. add medicine");
					System.out.println("4. view medicines");
					System.out.println("5. back");
					System.out.print("enter your choice: ");
					int prescriptionchoice = s.nextInt();
					s.nextLine();
					switch (prescriptionchoice) {
					case 1: {
						controller.createPrescription();
					}
						break;
					case 2: {
						controller.viewPrescription();
					}
						break;
					case 3: {
						controller.addMedicne();
					}
						break;
					case 4: {
						controller.viewMedicine();
					}
						break;
					case 5: {
						BackToMainPage = false;
					}
						break;
					default:
						System.out.println("Enter correct choices");
					}
				}
			}
				break;
			case 6: {
				boolean BackToMainPage = true;
				while (BackToMainPage) {
					System.out.println("\n===== ADMISSION MANAGEMENT =====");
					System.out.println("1. admit patient");
					System.out.println("2. view admission details");
					System.out.println("3. view all admissions");
					System.out.println("4. discharge patient");
					System.out.println("5. update admission details");
					System.out.println("6. back");
					System.out.print("enter your choice: ");
					int AdmissionChoice = s.nextInt();
					s.nextLine();
					switch (AdmissionChoice) {
					case 1: {
						controller.admitPatient();
					}
						break;
					case 2: {
						controller.viewAdmissionDetails();
					}
						break;
					case 3: {
						controller.viewAllAddmissions();
					}
						break;
					case 4: {
						controller.dischargePatient();
					}
						break;
					case 5: {
						controller.updateAddmission();
					}
						break;
					case 6: {
						BackToMainPage = false;
					}
						break;
					default:
						System.out.println("Enter correct choice");
					}
				}
			}
				break;
			case 7: {
				boolean BackToMainPage = true;
				while (BackToMainPage) {
					System.out.println("\n===== BED MANAGEMENT =====");
					System.out.println("1. add bed details");
					System.out.println("2. view bed details");
					System.out.println("3. update bed details");
					System.out.println("4. delete the bed ");
					System.out.println("5. view all beds");
					System.out.println("6. update bed status");
					System.out.println("7. back");
					System.out.print("enter your choice: ");
					int bedChoice = s.nextInt();
					s.nextLine();
					switch (bedChoice) {
					case 1: {
						controller.addBedDetails();
					}
						break;
					case 2: {
						controller.viewBedDetails();
					}
						break;
					case 3: {
						controller.UpdateBedDetails();
					}
						break;
					case 4: {
						controller.deleteBedDetails();
					}
						break;
					case 5: {
						controller.viewAllBedDetails();
					}
						break;
					case 6: {
						controller.updateBedStatus();
					}
						break;
					case 7: {
						BackToMainPage = false;
					}
						break;
					default:
						System.out.println("Enter the Correct choice");
					}
				}
			}
				break;
			case 8: {
				boolean BackToMainPage = true;
				while (BackToMainPage) {
					System.out.println("\n===== BILL MANAGEMENT =====");
					System.out.println("1. create bill");
					System.out.println("2. view bill details");
					System.out.println("3. view all bills");
					System.out.println("4. update bill details");
					System.out.println("5. view patient bills");
					System.out.println("6. back");
					System.out.print("enter your choice: ");
					int billChoice = s.nextInt();
					s.nextLine();
					switch (billChoice) {
					case 1: {
						controller.createBill();
					}
						break;
					case 2: {
						controller.viewBill();
					}
						break;
					case 3: {
						controller.viewAllBills();
					}
						break;
					case 4: {
						controller.updateBillDetails();
					}
						break;
					case 5: {
						controller.viewPatientBills();
					}
						break;
					case 6: {
						BackToMainPage = false;
					}
						break;
					default:
						System.out.println("Enter correct choice");
					}

				}

			}
				break;
			case 9: {
				boolean BackToMainPage = true;
				while (BackToMainPage) {
					System.out.println("\n===== PAYMENT MANAGEMENT =====");
					System.out.println("1. make payment");
					System.out.println("2. view payment details");
					System.out.println("3. view payments for bill");
					System.out.println("4. back");
					System.out.print("enter your choice: ");
					int paymentChoice = s.nextInt();
					s.nextLine();
					switch (paymentChoice) {
					case 1: {
						controller.makePayment();
					}
						break;
					case 2: {
						controller.viewPayment();
					}
						break;
					case 3: {
						controller.viewPaymentForBill();
					}
						break;
					case 4: {
						BackToMainPage = false;
					}
						break;
					default:
						System.out.println("Enter the correct choice");
					}
				}

			}
				break;
			case 10: {
				boolean BackToMainPage = true;
				while (BackToMainPage) {
					System.out.println("\n===== LABORATORY MANAGEMENT =====");
					System.out.println("1. add lab test");
					System.out.println("2. view lab tests");
					System.out.println("3. update lab test");
					System.out.println("4. order test");
					System.out.println("5. view test order");
					System.out.println("6. view patient test orders");
					System.out.println("7. back");
					System.out.print("enter your choice: ");
					int labChoice = s.nextInt();
					s.nextLine();
					switch (labChoice) {
					case 1: {
						controller.addLabTest();
					}
						break;
					case 2: {
						controller.viewLabTest();
					}
						break;
					case 3: {
						controller.UpdateLabTest();
					}
						break;
					case 4: {
						controller.orderTest();
					}
						break;
					case 5: {
						controller.viewTestOrder();
					}
						break;
					case 6: {
						controller.viewPatientTestOrders();
					}
						break;
					case 7: {
						BackToMainPage = false;
					}
						break;
					default:
						System.out.println("Enter the correct choice");
					}
				}
			}
				break;
			case 11: {
				running = false;
				System.out.println("Thank you  🤗🤗");
			}
				break;
			default:
				System.out.println("enter correct choice");
			}
		}

	}

	public Patient getPatientInput() {
		Patient p = new Patient();
		System.out.println("Enter FirstName: ");
		p.setFirstName(s.nextLine());
		System.out.println("Enter LastName: ");
		p.setLastName(s.nextLine());
		System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
		p.setDateOfBirth(LocalDate.parse(s.nextLine()));
		System.out.print("Enter Gender: ");
		p.setGender(s.nextLine());
		System.out.print("Enter Phone: ");
		p.setPhone(s.nextLine());
		System.out.print("Enter Email: ");
		p.setEmail(s.nextLine());
		System.out.print("Enter Address: ");
		p.setAddress(s.nextLine());
		System.out.print("Enter Blood Group: ");
		p.setBloodGroup(s.nextLine());
		return p;
	}

	public void displayPatient(Patient p) {
		System.out.println("FirstName: " + p.getFirstName());
		System.out.println(" LastName: " + p.getLastName());
		System.out.println("Date Of Birth: " + p.getDateOfBirth());
		System.out.println("Gender: " + p.getGender());
		System.out.println("Phone: " + p.getPhone());
		System.out.println("Email: " + p.getEmail());
		System.out.println("Address: " + p.getAddress());
		System.out.println("BloodGroup: " + p.getBloodGroup());
	}

	public Doctor getDoctorInput() {
		Doctor d = new Doctor();
		System.out.println("Enter FirstName: ");
		d.setFirstName(s.nextLine());
		System.out.println("Enter LastName: ");
		d.setLastName(s.nextLine());
		System.out.print("Enter Specialization: ");
		d.setSpecialization(s.nextLine());
		System.out.print("Enter qualification: ");
		d.setQualification(s.nextLine());
		System.out.print("Enter phone: ");
		d.setPhone(s.nextLine());
		System.out.print("Enter Email: ");
		d.setEmail(s.nextLine());
		System.out.print("Enter experience: ");
		d.setExperience(s.nextInt());
		return d;
	}

	public void displayDoctor(Doctor d) {
		System.out.println("FirstName: " + d.getFirstName());
		System.out.println(" LastName: " + d.getLastName());
		System.out.println("specialization: " + d.getSpecialization());
		System.out.println("Qualification: " + d.getQualification());
		System.out.println("Phone: " + d.getPhone());
		System.out.println("Email: " + d.getEmail());
		System.out.println("Experience: " + d.getExperience());
		System.out.println("Department: " + (d.getDepartment() == null ? "-" : d.getDepartment().getDepartmentName()));
	}

	public Department getDepartmentInput() {
		Department d = new Department();
		System.out.println("Enter Department Name: ");
		d.setDepartmentName(s.nextLine());
		System.out.println("Enter Location: ");
		d.setLocation(s.nextLine());
		return d;
	}

	public void displayDepartment(Department d) {
		System.out.println("Department Name: " + d.getDepartmentName());
		System.out.println(" Location: " + d.getLocation());
	}

	public Appointment getAppointmentInput() {
		Appointment a = new Appointment();
		System.out.println("Enter Appointment Date: ");
		a.setAppointmentDate(LocalDateTime.parse(s.nextLine()));
		System.out.println("Enter Reason: ");
		a.setReason(s.nextLine());
		System.out.print("Enter Notes: ");
		a.setNotes(s.nextLine());
		return a;
	}

	public void displayAppointment(Appointment a) {
		System.out.println("Appointment Date: " + a.getAppointmenDate());
		System.out.println("Reason: " + a.getReason());
		System.out.println("Notes: " + a.getNotes());
		System.out.println("Patient: "
				+ (a.getPatient() == null ? "-" : a.getPatient().getFirstName() + " " + a.getPatient().getLastName()));

		System.out.println("Doctor: " + (a.getDoctor() == null ? "-"
				: "Dr. " + a.getDoctor().getFirstName() + " " + a.getDoctor().getLastName()));

		System.out.println(
				"Prescription ID: " + (a.getPrescription() == null ? "-" : a.getPrescription().getPrescriptionId()));
	}

	public Prescription getPrescriptionInput() {
		Prescription p = new Prescription();
		System.out.println("Enter Prescription Date: ");
		p.setPrescriptionDate(LocalDateTime.parse(s.nextLine()));
		System.out.println("Enter Notes: ");
		p.setNotes(s.nextLine());
		return p;
	}

	public void displayPrescription(Prescription p) {
		System.out.println("Prescription ID: " + p.getPrescriptionId());
		System.out.println(
				"Appointment ID: " + (p.getAppointment() == null ? "-" : p.getAppointment().getAppointmentid()));
		System.out.println("Prescription Date: " + p.getPrescriptionDate());
		System.out.println("Notes: " + p.getNotes());
		if (p.getMedicines() == null || p.getMedicines().isEmpty()) {
			System.out.println("Medicine not yet added");
		} else {
			System.out.println("Medicine");
			for (PrescriptionMedicine m : p.getMedicines()) {
				System.out.println("  - " + m.getMedicineName() + " | Dosage: " + m.getDosage() + " | Duration: "
						+ m.getDuration());
			}
		}
	}

	public PrescriptionMedicine getMedicineInput() {
		PrescriptionMedicine m = new PrescriptionMedicine();
		System.out.print("Enter Medicine Name: ");
		m.setMedicineName(s.nextLine());
		System.out.print("Enter Dosage: ");
		m.setDosage(s.nextLine());
		System.out.print("Enter Duration: ");
		m.setDuration(s.nextLine());
		return m;
	}

	public void displayMedicine(PrescriptionMedicine m) {
		System.out.println("Medicine Name: " + m.getMedicineName());
		System.out.println("Dosage : " + m.getDosage());
		System.out.println("Duration :" + m.getDuration());
	}

	public Bed getBedInput() {
		Bed b = new Bed();
		System.out.print("Enter Bed Number: ");
		b.setBedNumber(s.nextLine());
		System.out.print("Enter BedType: ");
		b.setBedType(s.nextLine());
		System.out.print("Enter ChargePerday: ");
		b.setChargePerDay(s.nextDouble());
		s.nextLine();
		System.out.print("Enter Status: ");
		b.setStatus(s.nextLine());
		return b;
	}

	public void displayBed(Bed b) {
		System.out.println("Bed Number: " + b.getBedNumber());
		System.out.println("Bed Type : " + b.getBedType());
		System.out.println("charge Per Day :" + b.getChargePerDay());
		System.out.println("status :" + b.getStatus());
	}

	public Admission getAddmissionInput() {
		Admission a = new Admission();
		System.out.println("Enter the Reason: ");
		a.setReasons(s.nextLine());
		return a;
	}

	public void displayAdmission(Admission a) {
		System.out.println("Addmission Date: " + a.getAdmissiondate());
		System.out
				.println("Discharge Date: " + (a.getDischargedate() == null ? "Still admitted" : a.getDischargedate()));
		System.out.println("Reasons " + a.getReasons());
		System.out.println("Patient: "
				+ (a.getPatient() == null ? "-" : a.getPatient().getFirstName() + " " + a.getPatient().getLastName()));
		System.out.println("Bed: " + (a.getBed() == null ? "-" : a.getBed().getBedNumber()));
	}

	public Bill getBillInput() {
		Bill b = new Bill();
		b.setTotalAmount(readAmount("Enter Total Amount: "));
		b.setDiscount(readAmount("Enter Discount (0 if none): "));
		return b;
	}

	public void displayBill(Bill b) {
		System.out.println("Bill ID: " + b.getBillId());
		System.out.println("Patient: "
				+ (b.getPatient() == null ? "-" : b.getPatient().getFirstName() + " " + b.getPatient().getLastName()));
		System.out.println("Bill Date: " + b.getBillDate());
		System.out.println("Total Amount: " + b.getTotalAmount());
		System.out.println("Discount: " + b.getDiscount());
		System.out.println("Net Amount: " + b.getNetAmount());
	}

	public Payment getPaymentInput() {
		Payment pay = new Payment();

		pay.setAmount(readAmount("Enter Amount: "));

		// keep asking until the mode is one of the allowed values
		while (true) {
			System.out.print("Enter Payment Mode (Cash/Card/UPI): ");
			String mode = s.nextLine().trim();
			if (mode.equalsIgnoreCase("Cash")) {
				pay.setPaymentMode("Cash");
				break;
			} else if (mode.equalsIgnoreCase("Card")) {
				pay.setPaymentMode("Card");
				break;
			} else if (mode.equalsIgnoreCase("UPI")) {
				pay.setPaymentMode("UPI");
				break;
			}
			System.out.println("Invalid mode. Use Cash, Card or UPI.");
		}

		// keep asking until a transaction ID is typed
		while (true) {
			System.out.print("Enter Transaction ID: ");
			String txn = s.nextLine().trim();
			if (!txn.isEmpty()) {
				pay.setTransactionId(txn);
				break;
			}
			System.out.println("Transaction ID cannot be empty.");
		}

		return pay;
	}

	public void displayPayment(Payment p) {
		System.out.println("Payment ID: " + p.getPaymentId());
		System.out.println("Bill ID: " + (p.getBill() == null ? "-" : p.getBill().getBillId()));
		System.out.println("Payment Date: " + p.getPaymentDate());
		System.out.println("Amount: " + p.getAmount());
		System.out.println("Payment Mode: " + p.getPaymentMode());
		System.out.println("Transaction ID: " + p.getTransactionId());
	}

	public LabTest getLabTestInput() {
		LabTest t = new LabTest();

		// keep asking until a name is typed
		while (true) {
			System.out.print("Enter Test Name: ");
			String name = s.nextLine().trim();
			if (!name.isEmpty()) {
				t.setTestName(name);
				break;
			}
			System.out.println("Test name cannot be empty.");
		}

		// keep asking until the price is above 0
		while (true) {
			double price = readAmount("Enter Price: ");
			if (price > 0) {
				t.setPrice(price);
				break;
			}
			System.out.println("Price must be greater than 0.");
		}

		System.out.print("Enter Description: ");
		t.setDescription(s.nextLine().trim());
		return t;
	}

	public void displayLabTest(LabTest t) {
		System.out.println("Test ID: " + t.getTestId());
		System.out.println("Test Name: " + t.getTestName());
		System.out.println("Price: " + t.getPrice());
		System.out.println("Description: " + t.getDescription());
	}

	public TestOrder getTestInput() {
		TestOrder o = new TestOrder();
		System.out.print("Enter Remarks (press Enter to skip): ");
		o.setRemarks(s.nextLine().trim());
		return o;
	}

	public void displayTestOrder(TestOrder o) {
		System.out.println("Order ID: " + o.getTestOrderId());
		System.out.println("Patient: "
				+ (o.getPatient() == null ? "-" : o.getPatient().getFirstName() + " " + o.getPatient().getLastName()));
		System.out.println("Test: " + (o.getLabTest() == null ? "-"
				: o.getLabTest().getTestName() + " (Price: " + o.getLabTest().getPrice() + ")"));
		System.out.println("Order Date: " + o.getOrderDate());
		System.out.println("Remarks: " + (o.getRemarks() == null || o.getRemarks().isEmpty() ? "-" : o.getRemarks()));
	}

	public double getPriceInput() {
		while (true) {
			double price = readAmount("Enter the updated price: ");
			if (price > 0) {
				return price;
			}
			System.out.println("Price must be greater than 0.");
		}
	}

	public String getBedStatusInput() {
		System.out.print("Enter new status (Available/Occupied): ");
		return s.nextLine().trim();
	}

	public int getDepartmentIdInput() {
		System.out.print("Enter Department ID: ");
		int id = s.nextInt();
		s.nextLine(); // clears the leftover Enter
		return id;
	}

	public int getDoctorIdInput() {
		System.out.print("Enter Doctor ID: ");
		int id = s.nextInt();
		s.nextLine(); // clears the leftover Enter
		return id;
	}

	public int getPatientIdInput() {
		System.out.print("Enter Patient ID: ");
		int id = s.nextInt();
		s.nextLine(); // clears the leftover Enter
		return id;
	}

	public int getPrescriptionIdInput() {
		System.out.print("Enter Prescription ID: ");
		int id = s.nextInt();
		s.nextLine(); // clears the leftover Enter
		return id;
	}

	public int getAppointmentIdInput() {
		System.out.print("Enter Appointment ID: ");
		int id = s.nextInt();
		s.nextLine(); // clears the leftover Enter
		return id;
	}

	public int getBedIdInput() {
		System.out.print("Enter Bed ID: ");
		int id = s.nextInt();
		s.nextLine(); // clears the leftover Enter
		return id;
	}

	public int getAdmissionIdInput() {
		System.out.print("Enter Addmisssion ID: ");
		int id = s.nextInt();
		s.nextLine(); // clears the leftover Enter
		return id;
	}

	public int getBillIdInput() {
		System.out.print("Enter Bill ID: ");
		int id = s.nextInt();
		s.nextLine(); // clears the leftover Enter
		return id;
	}

	private double readAmount(String prompt) {
		while (true) {
			System.out.print(prompt);
			try {
				return Double.parseDouble(s.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			}
		}
	}

	public double getDiscountInput() {
		return readAmount("Enter the updated discount: ");
	}

	public int getPaymentIdInput() {
		System.out.print("Enter Payment ID: ");
		int id = s.nextInt();
		s.nextLine(); // clears the leftover Enter
		return id;
	}

	public int getTestOrderIdInput() {
		System.out.print("Enter Test Order ID: ");
		int id = s.nextInt();
		s.nextLine(); // clears the leftover Enter
		return id;
	}

	public int getLabTestIdInput() {
		System.out.print("Enter LabTest  ID: ");
		int id = s.nextInt();
		s.nextLine(); // clears the leftover Enter
		return id;
	}

	public String getReasonInput() {
		System.out.print("Enter the updated reason: ");
		return s.nextLine().trim();
	}
}
