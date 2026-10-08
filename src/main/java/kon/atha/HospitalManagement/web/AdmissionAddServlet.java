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

@WebServlet("/add-admission")
public class AdmissionAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			List<Patient> patients = em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();

			List<Bed> beds = em.createQuery("SELECT b FROM Bed b " + "WHERE b.status = 'Available'", Bed.class)
					.getResultList();

			request.setAttribute("patients", patients);
			request.setAttribute("beds", beds);

			request.getRequestDispatcher("add-admission.jsp").forward(request, response);

		} finally {

			em.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int patientId = Integer.parseInt(request.getParameter("patientId"));

		int bedId = Integer.parseInt(request.getParameter("bedId"));

		String admissionDate = request.getParameter("admissionDate");

		String reason = request.getParameter("reasons");

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Patient patient = em.find(Patient.class, patientId);

			Bed bed = em.find(Bed.class, bedId);

			if (patient == null || bed == null) {
				response.sendRedirect("add-admission");
				return;
			}

			if (!"Available".equalsIgnoreCase(bed.getStatus())) {
				response.sendRedirect("add-admission");
				return;
			}

			Admission admission = new Admission();

			admission.setAdmissiondate(LocalDateTime.parse(admissionDate));

			admission.setReasons(reason);
			admission.setPatient(patient);
			admission.setBed(bed);

			em.getTransaction().begin();

			em.persist(admission);

			// Mark bed as occupied
			bed.setStatus("Occupied");

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