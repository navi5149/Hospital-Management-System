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

@WebServlet("/edit-lab-test")
public class LabTestEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// =========================
	// OPEN EDIT PAGE
	// =========================
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParameter = request.getParameter("id");

		if (idParameter == null || idParameter.trim().isEmpty()) {
			response.sendRedirect("lab-tests");
			return;
		}

		EntityManager em = JPAUtil.getEntityManager();

		try {

			int testId = Integer.parseInt(idParameter);

			LabTest test = em.find(LabTest.class, testId);

			if (test != null) {

				request.setAttribute("test", test);

				RequestDispatcher dispatcher = request.getRequestDispatcher("edit-lab-test.jsp");

				dispatcher.forward(request, response);

			} else {

				response.sendRedirect("lab-tests");
			}

		} catch (NumberFormatException e) {

			e.printStackTrace();
			response.sendRedirect("lab-tests");

		} finally {

			em.close();
		}
	}

	// =========================
	// UPDATE LAB TEST
	// =========================
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParameter = request.getParameter("testId");

		if (idParameter == null || idParameter.trim().isEmpty()) {
			response.sendRedirect("lab-tests");
			return;
		}

		EntityManager em = JPAUtil.getEntityManager();

		try {

			int testId = Integer.parseInt(idParameter);

			String testName = request.getParameter("testName");

			String priceParameter = request.getParameter("price");

			String description = request.getParameter("description");

			Double price = Double.parseDouble(priceParameter);

			em.getTransaction().begin();

			LabTest test = em.find(LabTest.class, testId);

			if (test != null) {

				test.setTestName(testName);
				test.setPrice(price);
				test.setDescription(description);

				em.merge(test);
			}

			em.getTransaction().commit();

			response.sendRedirect("lab-tests");

		} catch (NumberFormatException e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

			response.sendRedirect("lab-tests");

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

			response.sendRedirect("lab-tests");

		} finally {

			em.close();
		}
	}
}