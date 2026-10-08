package kon.atha.HospitalManagement.web;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.Payment;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/delete-payment")
public class PaymentDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int paymentId = Integer.parseInt(request.getParameter("id"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Payment payment = em.find(Payment.class, paymentId);

			if (payment != null) {

				em.getTransaction().begin();

				em.remove(payment);

				em.getTransaction().commit();
			}

			response.sendRedirect("payments");

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

			response.getWriter().println("Error while deleting payment.");

		} finally {
			em.close();
		}
	}
}