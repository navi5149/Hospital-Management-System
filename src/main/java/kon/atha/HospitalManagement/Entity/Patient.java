package kon.atha.HospitalManagement.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patientId;
	@Column(nullable = false, length = 30)
	private String firstName;
	@Column(nullable = false, length = 30)
	private String lastName;
	private LocalDate dateOfBirth;
	private String gender;
	@Column(length = 10)
	private String phone;
	private String email;
	private String address;
	private String bloodGroup;

	@OneToMany(mappedBy = "patient")
	private List<Appointment> appointments;

	@OneToMany(mappedBy = "patient")
	private List<Admission> admissions;

	@OneToMany(mappedBy = "patient")
	private List<Bill> bills;

	@OneToMany(mappedBy = "patient")
	private List<TestOrder> testOrders;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<Admission> getAdmissions() {
		return admissions;
	}

	public void setAdmissions(List<Admission> admissions) {
		this.admissions = admissions;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public List<TestOrder> getTestOrders() {
		return testOrders;
	}

	public void setTestOrders(List<TestOrder> testOrders) {
		this.testOrders = testOrders;
	}

}