package kon.atha.HospitalManagement.web;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.Patient;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/delete-patient")
public class PatientDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int patientId = Integer.parseInt(request.getParameter("id"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Patient patient = em.find(Patient.class, patientId);

			if (patient != null) {

				em.getTransaction().begin();

				em.remove(patient);

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