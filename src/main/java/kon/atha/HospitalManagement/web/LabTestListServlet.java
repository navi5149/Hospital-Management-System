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
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/lab-tests")
public class LabTestListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			List<LabTest> tests = em.createQuery("SELECT t FROM LabTest t", LabTest.class).getResultList();

			request.setAttribute("tests", tests);

			RequestDispatcher dispatcher = request.getRequestDispatcher("lab-tests.jsp");

			dispatcher.forward(request, response);

		} finally {

			em.close();
		}
	}
}
