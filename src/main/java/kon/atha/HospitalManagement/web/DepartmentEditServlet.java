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

@WebServlet("/edit-department")
public class DepartmentEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int departmentId = Integer.parseInt(request.getParameter("id"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Department department = em.find(Department.class, departmentId);

			if (department != null) {

				request.setAttribute("department", department);

				request.getRequestDispatcher("edit-department.jsp").forward(request, response);

			} else {

				response.sendRedirect("departments");
			}

		} finally {

			em.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int departmentId = Integer.parseInt(request.getParameter("departmentId"));

		String departmentName = request.getParameter("departmentName");

		String location = request.getParameter("location");

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Department department = em.find(Department.class, departmentId);

			if (department != null) {

				department.setDepartmentName(departmentName);
				department.setLocation(location);

				em.getTransaction().begin();

				em.merge(department);

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

		response.sendRedirect("departments");
	}
}