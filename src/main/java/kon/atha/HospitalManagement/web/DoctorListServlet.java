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

import kon.atha.HospitalManagement.Entity.Doctor;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/doctors")
public class DoctorListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			List<Doctor> doctors = em.createQuery("SELECT d FROM Doctor d LEFT JOIN FETCH d.department", Doctor.class)
					.getResultList();

			request.setAttribute("doctors", doctors);

			RequestDispatcher dispatcher = request.getRequestDispatcher("doctors.jsp");

			dispatcher.forward(request, response);

		} finally {

			em.close();
		}
	}
}