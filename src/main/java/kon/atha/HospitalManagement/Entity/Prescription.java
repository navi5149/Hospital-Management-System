package kon.atha.HospitalManagement.Entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Prescription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prescriptionId;
	private LocalDateTime prescriptionDate;
	private String notes;

	@OneToOne
	@JoinColumn(name = "appointment_id")
	private Appointment appointment;

	@OneToMany(mappedBy = "prescription")
	private List<PrescriptionMedicine> medicines;

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public LocalDateTime getPrescriptionDate() {
		return prescriptionDate;
	}

	public void setPrescriptionDate(LocalDateTime prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public List<PrescriptionMedicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<PrescriptionMedicine> medicines) {
		this.medicines = medicines;
	}

}
