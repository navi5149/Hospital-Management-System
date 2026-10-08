package kon.atha.HospitalManagement.web;

import java.io.IOException;
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

@WebServlet("/edit-test-order")
public class TestOrderEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int testOrderId = Integer.parseInt(request.getParameter("id"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			TestOrder testOrder = em.find(TestOrder.class, testOrderId);

			if (testOrder == null) {
				response.getWriter().println("Test Order not found.");
				return;
			}

			List<Patient> patients = em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();

			List<LabTest> labTests = em.createQuery("SELECT l FROM LabTest l", LabTest.class).getResultList();

			request.setAttribute("testOrder", testOrder);
			request.setAttribute("patients", patients);
			request.setAttribute("labTests", labTests);

			RequestDispatcher rd = request.getRequestDispatcher("edit-test-order.jsp");

			rd.forward(request, response);

		} finally {
			em.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int testOrderId = Integer.parseInt(request.getParameter("testOrderId"));

		int patientId = Integer.parseInt(request.getParameter("patientId"));

		int testId = Integer.parseInt(request.getParameter("testId"));

		String orderDate = request.getParameter("orderDate");

		String remarks = request.getParameter("remarks");

		EntityManager em = JPAUtil.getEntityManager();

		try {

			TestOrder testOrder = em.find(TestOrder.class, testOrderId);

			Patient patient = em.find(Patient.class, patientId);

			LabTest labTest = em.find(LabTest.class, testId);

			if (testOrder == null || patient == null || labTest == null) {

				response.getWriter().println("Invalid Test Order, Patient or Lab Test.");

				return;
			}

			em.getTransaction().begin();

			testOrder.setOrderDate(java.time.LocalDateTime.parse(orderDate));

			testOrder.setRemarks(remarks);
			testOrder.setPatient(patient);
			testOrder.setLabTest(labTest);

			em.merge(testOrder);

			em.getTransaction().commit();

			response.sendRedirect("test-orders");

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

			response.getWriter().println("Error while updating Test Order.");

		} finally {
			em.close();
		}
	}
}