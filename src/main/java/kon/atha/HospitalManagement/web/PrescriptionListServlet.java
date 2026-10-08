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

import kon.atha.HospitalManagement.Entity.Prescription;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/prescriptions")
public class PrescriptionListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			List<Prescription> prescriptions = em
					.createQuery("SELECT DISTINCT p FROM Prescription p " + "LEFT JOIN FETCH p.appointment a "
							+ "LEFT JOIN FETCH a.patient " + "LEFT JOIN FETCH a.doctor", Prescription.class)
					.getResultList();

			request.setAttribute("prescriptions", prescriptions);

			RequestDispatcher dispatcher = request.getRequestDispatcher("prescriptions.jsp");

			dispatcher.forward(request, response);

		} finally {

			em.close();
		}
	}
}