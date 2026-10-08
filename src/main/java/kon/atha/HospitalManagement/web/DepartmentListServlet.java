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

import kon.atha.HospitalManagement.Entity.Department;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/departments")
public class DepartmentListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			List<Department> departments = em.createQuery("SELECT d FROM Department d", Department.class)
					.getResultList();

			request.setAttribute("departments", departments);

			RequestDispatcher dispatcher = request.getRequestDispatcher("departments.jsp");

			dispatcher.forward(request, response);

		} finally {

			em.close();
		}
	}
}