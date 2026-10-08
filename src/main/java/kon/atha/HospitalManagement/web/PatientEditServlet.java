package kon.atha.HospitalManagement.web;

import java.io.IOException;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.Patient;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/edit-patient")
public class PatientEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int patientId = Integer.parseInt(request.getParameter("id"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Patient patient = em.find(Patient.class, patientId);

			if (patient != null) {

				request.setAttribute("patient", patient);

				request.getRequestDispatcher("edit-patient.jsp").forward(request, response);

			} else {

				response.sendRedirect("patients");
			}

		} finally {

			em.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int patientId = Integer.parseInt(request.getParameter("patientId"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Patient patient = em.find(Patient.class, patientId);

			if (patient != null) {

				patient.setFirstName(request.getParameter("firstName"));

				patient.setLastName(request.getParameter("lastName"));

				String dob = request.getParameter("dateOfBirth");

				if (dob != null && !dob.isEmpty()) {
					patient.setDateOfBirth(LocalDate.parse(dob));
				}

				patient.setGender(request.getParameter("gender"));

				patient.setPhone(request.getParameter("phone"));

				patient.setEmail(request.getParameter("email"));

				patient.setAddress(request.getParameter("address"));

				patient.setBloodGroup(request.getParameter("bloodGroup"));

				em.getTransaction().begin();

				em.merge(patient);

				em.getTransaction().commit();
			}

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

		} finally {

			em.close();
		}

		response.sendRedirect("patients");
	}
}