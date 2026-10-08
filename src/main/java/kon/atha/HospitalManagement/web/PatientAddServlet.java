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

@WebServlet("/add-patient")
public class PatientAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("add-patient.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String bloodGroup = request.getParameter("bloodGroup");

		Patient patient = new Patient();

		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setDateOfBirth(LocalDate.parse(dateOfBirth));
		patient.setGender(gender);
		patient.setPhone(phone);
		patient.setEmail(email);
		patient.setAddress(address);
		patient.setBloodGroup(bloodGroup);

		EntityManager em = JPAUtil.getEntityManager();

		try {

			em.getTransaction().begin();

			em.persist(patient);

			em.getTransaction().commit();

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