package kon.atha.HospitalManagement.web;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.LabTest;
import kon.atha.HospitalManagement.Entity.TestOrder;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/delete-lab-test")
public class LabTestDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int testId = Integer.parseInt(request.getParameter("id"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			LabTest test = em.find(LabTest.class, testId);

			if (test != null) {

				List<TestOrder> orders = em
						.createQuery("SELECT t FROM TestOrder t WHERE t.labTest.testId = :testId", TestOrder.class)
						.setParameter("testId", testId).getResultList();

				if (!orders.isEmpty()) {

					response.setContentType("text/html");

					response.getWriter().println("<h2>Cannot delete this Lab Test</h2>");

					response.getWriter().println("<p>This Lab Test is already used in a Test Order.</p>");

					response.getWriter().println("<br><a href='lab-tests'>Back to Lab Tests</a>");

					return;
				}

				em.getTransaction().begin();

				em.remove(test);

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

		response.sendRedirect("lab-tests");
	}
}