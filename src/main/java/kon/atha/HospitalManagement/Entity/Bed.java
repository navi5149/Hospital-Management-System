package kon.atha.HospitalManagement.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bedId;
	@Column(unique = true)
	private String bedNumber;
	private String bedType;
	private Double chargePerDay;
	private String status;

	public int getBedId() {
		return bedId;
	}

	public void setBedId(int bedId) {
		this.bedId = bedId;
	}

	public String getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	public String getBedType() {
		return bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public Double getChargePerDay() {
		return chargePerDay;
	}

	public void setChargePerDay(Double chargePerDay) {
		this.chargePerDay = chargePerDay;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}