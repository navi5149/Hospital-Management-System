package kon.atha.HospitalManagement.web;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.TestOrder;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/delete-test-order")
public class TestOrderDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int testOrderId = Integer.parseInt(request.getParameter("id"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			TestOrder testOrder = em.find(TestOrder.class, testOrderId);

			if (testOrder != null) {

				em.getTransaction().begin();

				em.remove(testOrder);

				em.getTransaction().commit();
			}

			response.sendRedirect("test-orders");

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

			response.getWriter().println("Error while deleting Test Order.");

		} finally {
			em.close();
		}
	}
}