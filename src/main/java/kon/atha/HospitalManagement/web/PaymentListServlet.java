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

import kon.atha.HospitalManagement.Entity.Payment;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/payments")
public class PaymentListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			List<Payment> payments = em.createQuery("SELECT p FROM Payment p " + "LEFT JOIN FETCH p.bill b "
					+ "LEFT JOIN FETCH b.patient " + "ORDER BY p.paymentId", Payment.class).getResultList();

			request.setAttribute("payments", payments);

			RequestDispatcher rd = request.getRequestDispatcher("payments.jsp");

			rd.forward(request, response);

		} finally {
			em.close();
		}
	}
}
