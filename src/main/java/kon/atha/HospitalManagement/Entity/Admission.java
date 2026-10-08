package kon.atha.HospitalManagement.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Admission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int admissionid;

	private LocalDateTime admissiondate;

	private LocalDateTime dischargedate;

	@Column(nullable = false)
	private String reasons;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "bed_id")
	private Bed bed;

	public int getAdmissionid() {
		return admissionid;
	}

	public void setAdmissionid(int admissionid) {
		this.admissionid = admissionid;
	}

	public LocalDateTime getAdmissiondate() {
		return admissiondate;
	}

	public void setAdmissiondate(LocalDateTime admissiondate) {
		this.admissiondate = admissiondate;
	}

	public LocalDateTime getDischargedate() {
		return dischargedate;
	}

	public void setDischargedate(LocalDateTime dischargedate) {
		this.dischargedate = dischargedate;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Bed getBed() {
		return bed;
	}

	public void setBed(Bed bed) {
		this.bed = bed;
	}

}