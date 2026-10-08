package kon.atha.HospitalManagement.web;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.LabTest;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/add-lab-test")
public class LabTestAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("add-lab-test.jsp");

		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String testName = request.getParameter("testName");

		String priceString = request.getParameter("price");

		String description = request.getParameter("description");

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Double price = Double.parseDouble(priceString);

			LabTest test = new LabTest();

			test.setTestName(testName);
			test.setPrice(price);
			test.setDescription(description);

			em.getTransaction().begin();

			em.persist(test);

			em.getTransaction().commit();

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