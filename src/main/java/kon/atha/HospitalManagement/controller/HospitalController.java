package kon.atha.HospitalManagement.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
import kon.atha.HospitalManagement.view.HospitalView;

public class HospitalController {

	Scanner s = new Scanner(System.in);
	private HospitalView view;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("HospitalPU");

	public HospitalController(HospitalView view) {
		this.view = view;
	}

	public void addPatient() {
		Patient p = view.getPatientInput();
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			System.out.println(p.getPatientId() + " patient added sucessfully");
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewPatient() {
		EntityManager em = emf.createEntityManager();
		System.out.println("Enter patient Id: ");
		int id = s.nextInt();
		try {
			Patient p = em.find(Patient.class, id);
			if (p != null) {
				view.displayPatient(p);
			} else {
				System.out.println(id + "  doesnt exist");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void deletePatient() {
		EntityManager em = emf.createEntityManager();
		System.out.println("Enter patient Id: ");
		int id = s.nextInt();
		try {
			Patient p = em.find(Patient.class, id);
			if (p != null) {
				em.getTransaction().begin();
				em.remove(p);
				em.getTransaction().commit();
				System.out.println(p.getPatientId() + " get deleted successfully");
			} else {
				System.out.println(id + " doesnt exist");
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewAllPatient() {
		EntityManager em = emf.createEntityManager();
		String jpql = "select p from Patient p";
		Query q = em.createQuery(jpql);
		List<Patient> list = q.getResultList();
		for (Patient p : list) {
			if (p != null) {
				System.out.println("---------------------------------");
				view.displayPatient(p);
				System.out.println("---------------------------------");
			} else {
				System.out.println("Patients doesnt exist");
			}
		}
		em.close();
	}

	public void addDoctor() {
		Doctor d = view.getDoctorInput();
		int deptId = view.getDepartmentIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Department dept = em.find(Department.class, deptId);
			if (dept == null) {
				System.out.println("Department " + deptId + " not found");
				return;
			}
			d.setDepartment(dept);
			em.getTransaction().begin();
			em.persist(d);
			em.getTransaction().commit();
			System.out.println(d.getDoctorId() + "  added sucessfully");
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewDoctor() {
		EntityManager em = emf.createEntityManager();
		System.out.println("Enter Doctor Id: ");
		int id = s.nextInt();
		try {
			Doctor d = em.find(Doctor.class, id);
			if (d != null) {
				view.displayDoctor(d);
			} else {
				System.out.println(id + "  doesnt exist");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}

	}

	public void deleteDoctor() {
		EntityManager em = emf.createEntityManager();
		System.out.println("Enter Doctor Id: ");
		int id = s.nextInt();
		try {
			Doctor d = em.find(Doctor.class, id);
			if (d != null) {
				em.getTransaction().begin();
				em.remove(d);
				em.getTransaction().commit();
				System.out.println(d.getDoctorId() + " get deleted successfully");
			} else {
				System.out.println(id + "doesn't exist ");
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewAllDoctors() {
		EntityManager em = emf.createEntityManager();
		String jpql = "select d from Doctor d";
		Query q = em.createQuery(jpql);
		List<Doctor> list = q.getResultList();
		for (Doctor d : list) {
			if (d != null) {
				System.out.println("--------------------------");
				view.displayDoctor(d);
				System.out.println("--------------------------");
			} else {
				System.out.println("values doesnt exit");
			}
		}
		em.close();

	}

	public void updatePatient() {
		System.out.println("1. Update FirstName");
		System.out.println("2. Update LastName");
		System.out.println("3. Update Date Of Birth");
		System.out.println("4. Update Gender");
		System.out.println("5. Update Phone");
		System.out.println("6. Update Email");
		System.out.println("7. Update Address");
		System.out.println("8. Update BloodGroup");
		System.out.println("Enter Your Choice");
		int choice = s.nextInt();
		System.out.println("Enter the id to be updated:");
		int id = s.nextInt();
		s.nextLine();
		EntityManager em = emf.createEntityManager();
		try {
			Patient p = em.find(Patient.class, id);
			if (p != null) {
				switch (choice) {
				case 1: {
					System.out.println("Enter the Updated FirstName: ");
					p.setFirstName(s.nextLine());
				}
					break;
				case 2: {
					System.out.println("Enter the Updated LastName");
					p.setLastName(s.nextLine());
				}
					break;
				case 3: {
					System.out.println("Enter the updated DateofBirth");
					p.setDateOfBirth(LocalDate.parse(s.nextLine()));
				}
					break;
				case 4: {
					System.out.println("Enter the updated Gender");
					p.setGender(s.nextLine());
				}
					break;
				case 5: {
					System.out.println("Enter the Updated Phone");
					p.setPhone(s.nextLine());
				}
					break;
				case 6: {
					System.out.println("Enter the updated Email");
					p.setEmail(s.nextLine());
				}
					break;
				case 7: {
					System.out.println("Enter the Updated Address");
					p.setAddress(s.nextLine());
				}
					break;
				case 8: {
					System.out.println("Enter the updated Bloodgroup");
					p.setBloodGroup(s.nextLine());
				}
					break;
				default:
					System.out.println("Enter the correct choice");
				}

				em.getTransaction().begin();
				em.merge(p);
				em.getTransaction().commit();
				System.out.println("Updated Successfully");
			} else {
				System.out.println(id + "  doesnt exist");
			}

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Could not update: " + e.getMessage());
		} finally {
			em.close();
		}

	}

	public void updateDoctor() {
		System.out.println("1. Update FirstName");
		System.out.println("2. Update LastName");
		System.out.println("3. Update Specialization");
		System.out.println("4. Update Qualification");
		System.out.println("5. Update Phone");
		System.out.println("6. Update Email");
		System.out.println("7. Update experiece");
		System.out.println("8.Update Departmentid");
		System.out.println("Enter Your Choice");
		int choice = s.nextInt();
		System.out.println("Enter the id to be updated:");
		int id = s.nextInt();
		s.nextLine();
		EntityManager em = emf.createEntityManager();
		try {
			Doctor d = em.find(Doctor.class, id);
			if (d != null) {
				switch (choice) {
				case 1: {
					System.out.println("Enter the Updated FirstName: ");
					d.setFirstName(s.nextLine());
				}
					break;
				case 2: {
					System.out.println("Enter the Updated LastName");
					d.setLastName(s.nextLine());
				}
					break;
				case 3: {
					System.out.println("Enter the updated specialiization");
					d.setSpecialization(s.nextLine());
					;
				}
					break;
				case 4: {
					System.out.println("Enter the updated Qualification");
					d.setQualification(s.nextLine());
				}
					break;
				case 5: {
					System.out.println("Enter the Updated Phone");
					d.setPhone(s.nextLine());
				}
					break;
				case 6: {
					System.out.println("Enter the updated Email");
					d.setEmail(s.nextLine());
				}
					break;
				case 7: {
					System.out.println("Enter the Updated Experiece");
					d.setExperience(s.nextInt());
					s.nextLine();
				}
					break;
				case 8: {
					int deptId = view.getDepartmentIdInput();
					Department dept = em.find(Department.class, deptId);
					if (dept == null) {
						System.out.println("Department " + deptId + " not found");
						return;
					}
					d.setDepartment(dept);
				}
					break;
				default:
					System.out.println("Enter the correct choice");
				}

				em.getTransaction().begin();
				em.merge(d);
				em.getTransaction().commit();
				System.out.println("Updated Successfully");
			} else {
				System.out.println(id + "  doesnt exist");
			}

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Could not update: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	public void addDepartment() {
		Department d = view.getDepartmentInput();
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(d);
			em.getTransaction().commit();
			System.out.println(d.getDepartmentId() + "  added sucessfully");
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}

	}

	public void viewDepartment() {
		EntityManager em = emf.createEntityManager();
		System.out.println("Enter Department  Id: ");
		int id = s.nextInt();
		s.nextLine();
		try {
			Department d = em.find(Department.class, id);
			if (d != null) {
				view.displayDepartment(d);
			} else {
				System.out.println(id + "  doesnt exist");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}

	}

	public void updateDepartment() {
		System.out.println("1. Update DepartmentName");
		System.out.println("2. Update Location");
		System.out.println("Enter Your Choice");
		int choice = s.nextInt();
		System.out.println("Enter the id to be updated:");
		int id = s.nextInt();
		s.nextLine();
		EntityManager em = emf.createEntityManager();
		try {
			Department d = em.find(Department.class, id);
			if (d != null) {
				switch (choice) {
				case 1: {
					System.out.println("Enter the Updated DepartmentName: ");
					d.setDepartmentName(s.nextLine());
				}
					break;
				case 2: {
					System.out.println("Enter the Updated Location");
					d.setLocation(s.nextLine());
				}
					break;
				default:
					System.out.println("Enter the correct choice");
				}

				em.getTransaction().begin();
				em.merge(d);
				em.getTransaction().commit();
				System.out.println("Updated Successfully");
			} else {
				System.out.println(id + "  doesnt exist");
			}

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Could not update: " + e.getMessage());
		} finally {
			em.close();
		}

	}

	public void deleteDepartment() {

		EntityManager em = emf.createEntityManager();
		System.out.println("Enter Department Id: ");
		int id = s.nextInt();
		try {
			Department d = em.find(Department.class, id);
			if (d != null) {
				em.getTransaction().begin();
				em.remove(d);
				em.getTransaction().commit();
				System.out.println(d.getDepartmentId() + " get deleted successfully");
			} else {
				System.out.println(id + "doesn't exist ");
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewAllDepartment() {
		EntityManager em = emf.createEntityManager();
		String jpql = "select d from Department d";
		Query q = em.createQuery(jpql);
		List<Department> list = q.getResultList();
		for (Department d : list) {
			if (d != null) {
				System.out.println("--------------------------");
				view.displayDepartment(d);
				System.out.println("--------------------------");
			} else {
				System.out.println("menbers doesnt exit");
			}
		}
		em.close();
	}

	public void viewDoctorsInDepartment() {
		int deptId = view.getDepartmentIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Department dept = em.find(Department.class, deptId);
			if (dept == null) {
				System.out.println("Department " + deptId + " not found");
				return;
			}
			if (dept.getDoctors().isEmpty()) {
				System.out.println("No doctors in " + dept.getDepartmentName());
			} else {
				for (Doctor d : dept.getDoctors()) {
					view.displayDoctor(d);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void bookAppointment() {
		Appointment a = view.getAppointmentInput();
		int patientId = view.getPatientIdInput();
		int doctorId = view.getDoctorIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Patient p = em.find(Patient.class, patientId);
			Doctor d = em.find(Doctor.class, doctorId);
			if (p == null) {
				System.out.println("Patient  " + patientId + " not found");
				return;
			}
			if (d == null) {
				System.out.println("Doctor  " + doctorId + " not found");
				return;
			}
			a.setPatient(p);
			a.setDoctor(d);
			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();
			System.out.println(a.getAppointmentid() + "  added sucessfully");
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewAppointment() {
		EntityManager em = emf.createEntityManager();
		System.out.println("Enter Appointment  Id: ");
		int id = s.nextInt();
		s.nextLine();
		try {
			Appointment a = em.find(Appointment.class, id);
			if (a != null) {
				view.displayAppointment(a);
			} else {
				System.out.println(id + "  doesnt exist");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void updateAppointment() {
		System.out.println("1. Update AppointmentDate");
		System.out.println("2. Update Reason");
		System.out.println("Enter Your Choice");
		int choice = s.nextInt();
		System.out.println("Enter the id to be updated:");
		int id = s.nextInt();
		s.nextLine();
		EntityManager em = emf.createEntityManager();
		try {
			Appointment a = em.find(Appointment.class, id);
			if (a != null) {
				switch (choice) {
				case 1: {
					System.out.println("Enter the Updated Appointment Date: ");
					a.setAppointmentDate(LocalDateTime.now());
				}
					break;
				case 2: {
					System.out.println("Enter the Updated Reason");
					a.setReason(s.nextLine());
				}
					break;
				default:
					System.out.println("Enter the correct choice");
				}

				em.getTransaction().begin();
				em.merge(a);
				em.getTransaction().commit();
				System.out.println("Updated Successfully");
			} else {
				System.out.println(id + "  doesnt exist");
			}

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Could not update: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	public void cancelAppointment() {
		EntityManager em = emf.createEntityManager();
		System.out.println("Enter Appointment Id: ");
		int id = s.nextInt();
		try {
			Appointment a = em.find(Appointment.class, id);
			if (a != null) {
				em.getTransaction().begin();
				em.remove(a);
				em.getTransaction().commit();
				System.out.println(a.getAppointmentid() + " get deleted successfully");
			} else {
				System.out.println(id + "doesn't exist ");
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewAllAppointment() {
		EntityManager em = emf.createEntityManager();
		String jpql = "select a from Appointment a";
		Query q = em.createQuery(jpql);
		List<Appointment> list = q.getResultList();
		for (Appointment a : list) {
			if (a != null) {
				System.out.println("--------------------------");
				view.displayAppointment(a);
				System.out.println("--------------------------");
			} else {
				System.out.println("menbers doesnt exit");
			}
		}
		em.close();
	}

	public void AppointmentInPatient() {
		int patientId = view.getPatientIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Patient p = em.find(Patient.class, patientId);
			if (p == null) {
				System.out.println("Patient " + patientId + " not found");
				return;
			}
			if (p.getAppointments().isEmpty()) {
				System.out.println("No Appointments with " + p.getFirstName());
			} else {
				for (Appointment a : p.getAppointments()) {
					view.displayAppointment(a);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void AppointmentInDoctor() {
		int doctorId = view.getDoctorIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Doctor d = em.find(Doctor.class, doctorId);
			if (d == null) {
				System.out.println("Doctor " + doctorId + " not found");
				return;
			}
			if (d.getAppointments().isEmpty()) {
				System.out.println("No Appointments with " + d.getFirstName());
			} else {
				for (Appointment a : d.getAppointments()) {
					view.displayAppointment(a);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void createPrescription() {
		Prescription p = view.getPrescriptionInput();
		int appointmentId = view.getAppointmentIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Appointment a = em.find(Appointment.class, appointmentId);
			if (a == null) {
				System.out.println("Appointment with " + appointmentId + " not found");
				return;
			}
			if (a.getPrescription() != null) {
				System.out.println("Appointment " + appointmentId + " already has a prescription");
				return;
			}
			p.setAppointment(a);
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			System.out.println("Prescription added. ID = " + p.getPrescriptionId());
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewPrescription() {

		EntityManager em = emf.createEntityManager();
		System.out.println("Enter Prescription  Id: ");
		int id = s.nextInt();
		s.nextLine();
		try {
			Prescription p = em.find(Prescription.class, id);
			if (p != null) {
				view.displayPrescription(p);
			} else {
				System.out.println(id + "  doesnt exist");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void addMedicne() {
		PrescriptionMedicine m = view.getMedicineInput();
		int prescriptionId = view.getPrescriptionIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Prescription p = em.find(Prescription.class, prescriptionId);
			if (p == null) {
				System.out.println("prescription with " + prescriptionId + " not found");
				return;
			}
			m.setPrescription(p);
			em.getTransaction().begin();
			em.persist(m);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewMedicine() {
		EntityManager em = emf.createEntityManager();
		System.out.println("Enter Prescription  Id: ");
		int id = s.nextInt();
		s.nextLine();
		try {
			Prescription p = em.find(Prescription.class, id);
			if (p == null) {
				System.out.println("Prescription " + id + " not found");
				return;
			}
			if (p.getMedicines().isEmpty()) {
				System.out.println("No medicine with " + p.getPrescriptionId());
			}
			for (PrescriptionMedicine m : p.getMedicines()) {
				view.displayMedicine(m);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void addBedDetails() {
		Bed b = view.getBedInput();
		System.out.println("status read: [" + b.getStatus() + "]");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(b);
			em.getTransaction().commit();
			System.out.println(b.getBedNumber() + " bed added sucessfully");
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewBedDetails() {
		EntityManager em = emf.createEntityManager();
		System.out.println("Enter Bed Id: ");
		int id = s.nextInt();
		s.nextLine();
		try {
			Bed b = em.find(Bed.class, id);
			if (b != null) {
				view.displayBed(b);
			} else {
				System.out.println(id + " doesnt exist ");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void UpdateBedDetails() {
		System.out.println("1. Update BedType");
		System.out.println("2. Update charge PerDay");
		System.out.println("Enter Your Choice");
		int choice = s.nextInt();
		System.out.println("Enter the id to be updated:");
		int id = s.nextInt();
		s.nextLine();
		EntityManager em = emf.createEntityManager();
		try {
			Bed b = em.find(Bed.class, id);
			if (b != null) {
				switch (choice) {
				case 1: {
					System.out.println("Enter the Updated BedType: ");
					b.setBedType(s.nextLine());
				}
					break;
				case 2: {
					System.out.println("Enter the Updated charge per day ");
					b.setChargePerDay(s.nextDouble());
					s.nextLine();
				}
					break;
				default:
					System.out.println("Enter the correct choice");
					return;
				}

				em.getTransaction().begin();
				em.merge(b);
				em.getTransaction().commit();
				System.out.println("Updated Successfully");
			} else {
				System.out.println(id + "  doesnt exist");
			}

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Could not update: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	public void deleteBedDetails() {
		EntityManager em = emf.createEntityManager();
		System.out.println("Enter Bed Id: ");
		int id = s.nextInt();
		try {
			Bed b = em.find(Bed.class, id);
			if (b != null) {
				em.getTransaction().begin();
				em.remove(b);
				em.getTransaction().commit();
				System.out.println(b.getBedNumber() + " get deleted successfully");
			} else {
				System.out.println(id + "doesn't exist ");
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void updateBedStatus() {
		int id = view.getBedIdInput();
		String status = view.getBedStatusInput();
		String clean;
		if (status.equalsIgnoreCase("Available")) {
			clean = "Available";
		} else if (status.equalsIgnoreCase("Occupied")) {
			clean = "Occupied";
		} else {
			System.out.println("Invalid status. Use Available or Occupied.");
			return;
		}
		EntityManager em = emf.createEntityManager();
		try {
			Bed b = em.find(Bed.class, id);
			if (b == null) {
				System.out.println("Bed " + id + " not found");
				return;
			}
			em.getTransaction().begin();
			b.setStatus(clean);
			em.getTransaction().commit();
			System.out.println("Bed " + b.getBedNumber() + " status updated to " + clean);
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Could not update: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewAllBedDetails() {
		EntityManager em = emf.createEntityManager();
		String jpql = "select b from Bed b";
		Query q = em.createQuery(jpql);
		List<Bed> list = q.getResultList();
		for (Bed b : list) {
			if (b != null) {
				System.out.println("--------------------------");
				view.displayBed(b);
				System.out.println("--------------------------");
			} else {
				System.out.println("Beds doesnt exit");
			}
		}
		em.close();
	}

	public void admitPatient() {
		int patientId = view.getPatientIdInput();
		int bedId = view.getBedIdInput();
		Admission a = view.getAddmissionInput();
		EntityManager em = emf.createEntityManager();
		try {
			Patient p = em.find(Patient.class, patientId);
			if (p == null) {
				System.out.println("Patient with " + patientId + " not found");
				return;
			}
			Bed b = em.find(Bed.class, bedId);
			if (b == null) {
				System.out.println("Bed with " + bedId + " not found");
				return;
			}
			if (!"Available".equalsIgnoreCase(b.getStatus())) {
				System.out.println("Bed " + b.getBedNumber() + " is not available");
				return;
			}
			a.setPatient(p);
			a.setBed(b);
			a.setAdmissiondate(LocalDateTime.now());
			em.getTransaction().begin();
			em.persist(a);
			b.setStatus("Occupied");
			em.getTransaction().commit();
			System.out.println("Patient admitted. Admission ID = " + a.getAdmissionid());
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewAdmissionDetails() {
		EntityManager em = emf.createEntityManager();
		System.out.println("Enter Admission Id: ");
		int id = s.nextInt();
		s.nextLine();
		try {
			Admission a = em.find(Admission.class, id);
			if (a != null) {
				view.displayAdmission(a);
			} else {
				System.out.println(id + " doesnt exist ");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewAllAddmissions() {
		EntityManager em = emf.createEntityManager();
		String jpql = "select a from Admission a";
		Query q = em.createQuery(jpql);
		List<Admission> list = q.getResultList();
		for (Admission a : list) {
			if (a != null) {
				System.out.println("--------------------------");
				view.displayAdmission(a);
				System.out.println("--------------------------");
			} else {
				System.out.println("Admissions doesnt exit");
			}
		}
		em.close();
	}

	public void dischargePatient() {
		int admissionId = view.getAdmissionIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Admission a = em.find(Admission.class, admissionId);
			if (a == null) {
				System.out.println("Admission " + admissionId + " not found");
				return;
			}
			if (a.getDischargedate() != null) {
				System.out.println("Admission " + admissionId + " is already discharged");
				return;
			}
			em.getTransaction().begin();
			a.setDischargedate(LocalDateTime.now());
			a.getBed().setStatus("Available");
			em.getTransaction().commit();
			System.out.println("Patient discharged. Bed " + a.getBed().getBedNumber() + " is now Available");
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void updateAddmission() {
		int admissionId = view.getAdmissionIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Admission a = em.find(Admission.class, admissionId);
			if (a == null) {
				System.out.println(admissionId + " doesnt exist");
				return;
			}
			String reason = view.getReasonInput();
			if (reason.isEmpty()) {
				System.out.println("Reason cannot be empty");
				return;
			}
			em.getTransaction().begin();
			a.setReasons(reason);
			em.getTransaction().commit();
			System.out.println("Updated Successfully");
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Could not update: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	public void createBill() {
		Bill b = view.getBillInput(); // total amount and discount
		if (b.getTotalAmount() <= 0 || b.getDiscount() < 0 || b.getDiscount() > b.getTotalAmount()) {
			System.out.println("Total must be above 0, and the discount between 0 and the total");
			return;
		}
		int patientId = view.getPatientIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Patient p = em.find(Patient.class, patientId);
			if (p == null) {
				System.out.println("Patient " + patientId + " not found");
				return;
			}

			b.setPatient(p);
			b.setBillDate(LocalDateTime.now());
			b.setNetAmount(b.getTotalAmount() - b.getDiscount());

			em.getTransaction().begin();
			em.persist(b);
			em.getTransaction().commit();
			System.out.println("Bill created. ID = " + b.getBillId());
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewBill() {
		int id = view.getBillIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Bill b = em.find(Bill.class, id);
			if (b != null) {
				view.displayBill(b);
			} else {
				System.out.println("Bill " + id + " doesn't exist");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewAllBills() {
		EntityManager em = emf.createEntityManager();
		try {
			List<Bill> list = em.createQuery("select b from Bill b", Bill.class).getResultList();

			if (list.isEmpty()) {
				System.out.println("No bills found");
			} else {
				for (Bill b : list) {
					System.out.println("--------------------------");
					view.displayBill(b);
					System.out.println("--------------------------");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void updateBillDetails() {
		int id = view.getBillIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Bill b = em.find(Bill.class, id);
			if (b == null) {
				System.out.println("Bill " + id + " doesn't exist");
				return;
			}

			double discount = view.getDiscountInput();
			if (discount < 0 || discount > b.getTotalAmount()) {
				System.out.println("Discount must be between 0 and the total amount");
				return;
			}

			em.getTransaction().begin();
			b.setDiscount(discount);
			b.setNetAmount(b.getTotalAmount() - discount);
			em.getTransaction().commit();
			System.out.println("Updated Successfully. New net amount = " + b.getNetAmount());
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Could not update: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewPatientBills() {
		int patientId = view.getPatientIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Patient p = em.find(Patient.class, patientId);
			if (p == null) {
				System.out.println("Patient " + patientId + " not found");
				return;
			}

			if (p.getBills().isEmpty()) {
				System.out.println("No bills for " + p.getFirstName());
			} else {
				for (Bill b : p.getBills()) {
					System.out.println("--------------------------");
					view.displayBill(b);
					System.out.println("--------------------------");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewPaymentForBill() {
		int billId = view.getBillIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Bill b = em.find(Bill.class, billId);
			if (b == null) {
				System.out.println("Bill " + billId + " doesn't exist");
				return;
			}

			if (b.getPayments().isEmpty()) {
				System.out.println("No payments for bill " + billId);
				return;
			}

			double total = 0;
			for (Payment p : b.getPayments()) {
				System.out.println("--------------------------");
				view.displayPayment(p);
				total += p.getAmount();
			}
			System.out.println("--------------------------");
			System.out.println("Total paid: " + total);
			System.out.println("Balance: " + (b.getNetAmount() - total));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void makePayment() {
		int billId = view.getBillIdInput();
		Payment pay = view.getPaymentInput();

		if (pay.getAmount() <= 0) {
			System.out.println("Amount must be greater than 0");
			return;
		}

		EntityManager em = emf.createEntityManager();
		try {
			Bill b = em.find(Bill.class, billId);
			if (b == null) {
				System.out.println("Bill " + billId + " doesn't exist");
				return;
			}

			// how much has been paid so far
			double alreadyPaid = 0;
			for (Payment x : b.getPayments()) {
				alreadyPaid += x.getAmount();
			}
			double balance = b.getNetAmount() - alreadyPaid;

			if (pay.getAmount() > balance + 0.001) {
				System.out.println("Payment exceeds the balance of " + balance);
				return;
			}

			pay.setBill(b);
			pay.setPaymentDate(LocalDateTime.now());

			em.getTransaction().begin();
			em.persist(pay);
			em.getTransaction().commit();
			System.out.println("Payment successful. ID = " + pay.getPaymentId() + ", balance now = "
					+ (balance - pay.getAmount()));
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewPayment() {
		int id = view.getPaymentIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Payment p = em.find(Payment.class, id);
			if (p != null) {
				view.displayPayment(p);
			} else {
				System.out.println("Payment " + id + " doesn't exist");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void addLabTest() {
		LabTest t = view.getLabTestInput(); // name and price are already validated in the view
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
			System.out.println("Lab test added. ID = " + t.getTestId());
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewLabTest() {
		EntityManager em = emf.createEntityManager();
		try {
			List<LabTest> list = em.createQuery("select t from LabTest t", LabTest.class).getResultList();
			if (list.isEmpty()) {
				System.out.println("No lab tests found");
			} else {
				for (LabTest t : list) {
					System.out.println("--------------------------");
					view.displayLabTest(t);
					System.out.println("--------------------------");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void UpdateLabTest() {
		int id = view.getLabTestIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			LabTest t = em.find(LabTest.class, id);
			if (t == null) {
				System.out.println("Lab test " + id + " doesn't exist");
				return;
			}

			double price = view.getPriceInput();

			em.getTransaction().begin();
			t.setPrice(price);
			em.getTransaction().commit();
			System.out.println("Updated Successfully. New price = " + t.getPrice());
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Could not update: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	public void orderTest() {
		TestOrder o = view.getTestInput(); // remarks
		int patientId = view.getPatientIdInput();
		int testId = view.getLabTestIdInput();

		EntityManager em = emf.createEntityManager();
		try {
			Patient p = em.find(Patient.class, patientId);
			if (p == null) {
				System.out.println("Patient " + patientId + " not found");
				return;
			}
			LabTest t = em.find(LabTest.class, testId);
			if (t == null) {
				System.out.println("Lab test " + testId + " not found");
				return;
			}

			o.setPatient(p);
			o.setLabTest(t);
			o.setOrderDate(LocalDateTime.now());

			em.getTransaction().begin();
			em.persist(o);
			em.getTransaction().commit();
			System.out.println("Test ordered. Order ID = " + o.getTestOrderId());
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewTestOrder() {
		int id = view.getTestOrderIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			TestOrder o = em.find(TestOrder.class, id);
			if (o != null) {
				view.displayTestOrder(o);
			} else {
				System.out.println("Test order " + id + " doesn't exist");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void viewPatientTestOrders() {
		int patientId = view.getPatientIdInput();
		EntityManager em = emf.createEntityManager();
		try {
			Patient p = em.find(Patient.class, patientId);
			if (p == null) {
				System.out.println("Patient " + patientId + " not found");
				return;
			}

			if (p.getTestOrders().isEmpty()) {
				System.out.println("No test orders for " + p.getFirstName());
			} else {
				for (TestOrder o : p.getTestOrders()) {
					System.out.println("--------------------------");
					view.displayTestOrder(o);
					System.out.println("--------------------------");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}
}
