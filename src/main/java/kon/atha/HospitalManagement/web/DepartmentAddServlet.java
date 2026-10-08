package kon.atha.HospitalManagement.web;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.Department;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/add-department")
public class DepartmentAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("add-department.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String departmentName = request.getParameter("departmentName");

		String location = request.getParameter("location");

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Department department = new Department();

			department.setDepartmentName(departmentName);
			department.setLocation(location);

			em.getTransaction().begin();

			em.persist(department);

			em.getTransaction().commit();

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

		} finally {

			em.close();
		}

		response.sendRedirect("departments");
	}
}