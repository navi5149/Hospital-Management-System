package kon.atha.HospitalManagement.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.Bill;
import kon.atha.HospitalManagement.Entity.Patient;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/add-bill")
public class BillAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			List<Patient> patients = em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();

			request.setAttribute("patients", patients);

			RequestDispatcher rd = request.getRequestDispatcher("add-bill.jsp");

			rd.forward(request, response);

		} finally {
			em.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int patientId = Integer.parseInt(request.getParameter("patientId"));

		double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));

		double discount = Double.parseDouble(request.getParameter("discount"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Patient patient = em.find(Patient.class, patientId);

			if (patient == null) {
				response.getWriter().println("Patient not found.");
				return;
			}

			if (totalAmount <= 0) {
				response.getWriter().println("Total amount must be greater than 0.");
				return;
			}

			if (discount < 0 || discount > totalAmount) {
				response.getWriter().println("Discount must be between 0 and total amount.");
				return;
			}

			double netAmount = totalAmount - discount;

			Bill bill = new Bill();

			bill.setBillDate(LocalDateTime.now());
			bill.setTotalAmount(totalAmount);
			bill.setDiscount(discount);
			bill.setNetAmount(netAmount);
			bill.setPatient(patient);

			em.getTransaction().begin();

			em.persist(bill);

			em.getTransaction().commit();

			response.sendRedirect("bills");

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

			response.getWriter().println("Error while adding bill.");

		} finally {
			em.close();
		}
	}
}