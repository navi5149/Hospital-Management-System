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

import kon.atha.HospitalManagement.Entity.LabTest;
import kon.atha.HospitalManagement.Entity.Patient;
import kon.atha.HospitalManagement.Entity.TestOrder;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/add-test-order")
public class TestOrderAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			List<Patient> patients = em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();

			List<LabTest> labTests = em.createQuery("SELECT l FROM LabTest l", LabTest.class).getResultList();

			request.setAttribute("patients", patients);
			request.setAttribute("labTests", labTests);

			RequestDispatcher rd = request.getRequestDispatcher("add-test-order.jsp");

			rd.forward(request, response);

		} finally {
			em.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String orderDate = request.getParameter("orderDate");
		int patientId = Integer.parseInt(request.getParameter("patientId"));
		int testId = Integer.parseInt(request.getParameter("testId"));
		String remarks = request.getParameter("remarks");

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Patient patient = em.find(Patient.class, patientId);
			LabTest labTest = em.find(LabTest.class, testId);

			if (patient == null || labTest == null) {
				response.getWriter().println("Invalid Patient or Lab Test.");
				return;
			}

			TestOrder testOrder = new TestOrder();

			testOrder.setOrderDate(LocalDateTime.parse(orderDate));
			testOrder.setRemarks(remarks);
			testOrder.setPatient(patient);
			testOrder.setLabTest(labTest);

			em.getTransaction().begin();

			em.persist(testOrder);

			em.getTransaction().commit();

			response.sendRedirect("test-orders");

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();
			response.getWriter().println("Error while adding Test Order.");

		} finally {
			em.close();
		}
	}
}
