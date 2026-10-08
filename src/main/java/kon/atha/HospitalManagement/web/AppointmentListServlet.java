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

import kon.atha.HospitalManagement.Entity.Appointment;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/appointments")
public class AppointmentListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			List<Appointment> appointments = em.createQuery(
					"SELECT a FROM Appointment a " + "LEFT JOIN FETCH a.patient " + "LEFT JOIN FETCH a.doctor",
					Appointment.class).getResultList();

			request.setAttribute("appointments", appointments);

			RequestDispatcher dispatcher = request.getRequestDispatcher("appointments.jsp");

			dispatcher.forward(request, response);

		} finally {

			em.close();
		}
	}
}