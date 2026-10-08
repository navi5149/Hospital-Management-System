package kon.atha.HospitalManagement.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.Admission;
import kon.atha.HospitalManagement.Entity.Bed;
import kon.atha.HospitalManagement.Entity.Patient;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/edit-admission")
public class AdmissionEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int admissionId = Integer.parseInt(request.getParameter("id"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Admission admission = em.find(Admission.class, admissionId);

			if (admission == null) {

				response.sendRedirect("admissions");
				return;
			}

			List<Patient> patients = em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();

			List<Bed> beds = em.createQuery(
					"SELECT b FROM Bed b " + "WHERE b.status = 'Available' " + "OR b.bedId = :currentBedId", Bed.class)
					.setParameter("currentBedId", admission.getBed().getBedId()).getResultList();

			request.setAttribute("admission", admission);
			request.setAttribute("patients", patients);
			request.setAttribute("beds", beds);

			request.getRequestDispatcher("edit-admission.jsp").forward(request, response);

		} finally {

			em.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int admissionId = Integer.parseInt(request.getParameter("admissionId"));

		int patientId = Integer.parseInt(request.getParameter("patientId"));

		int bedId = Integer.parseInt(request.getParameter("bedId"));

		String admissionDate = request.getParameter("admissionDate");

		String dischargeDate = request.getParameter("dischargeDate");

		String reason = request.getParameter("reasons");

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Admission admission = em.find(Admission.class, admissionId);

			Patient patient = em.find(Patient.class, patientId);

			Bed newBed = em.find(Bed.class, bedId);

			if (admission == null || patient == null || newBed == null) {

				response.sendRedirect("admissions");
				return;
			}

			Bed oldBed = admission.getBed();

			// If changing to another bed,
			// the new bed must be available.
			if (oldBed.getBedId() != newBed.getBedId()) {

				if (!"Available".equalsIgnoreCase(newBed.getStatus())) {

					response.sendRedirect("admissions");
					return;
				}
			}

			em.getTransaction().begin();

			admission.setPatient(patient);

			admission.setAdmissiondate(LocalDateTime.parse(admissionDate));

			admission.setReasons(reason);

			admission.setBed(newBed);

			if (dischargeDate != null && !dischargeDate.trim().isEmpty()) {

				admission.setDischargedate(LocalDateTime.parse(dischargeDate));

				// Patient discharged
				newBed.setStatus("Available");

			} else {

				admission.setDischargedate(null);

				// Patient is currently admitted
				newBed.setStatus("Occupied");
			}

			// If the bed was changed,
			// release the old bed.
			if (oldBed.getBedId() != newBed.getBedId()) {

				oldBed.setStatus("Available");
			}

			em.merge(admission);

			em.getTransaction().commit();

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

		} finally {

			em.close();
		}

		response.sendRedirect("admissions");
	}
}