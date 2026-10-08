package kon.atha.HospitalManagement.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TestOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int testOrderId;
	private LocalDateTime orderDate;
	private String remarks;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "test_id")
	private LabTest labTest;

	public int getTestOrderId() {
		return testOrderId;
	}

	public void setTestOrderId(int testOrderId) {
		this.testOrderId = testOrderId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LabTest getLabTest() {
		return labTest;
	}

	public void setLabTest(LabTest labTest) {
		this.labTest = labTest;
	}

}