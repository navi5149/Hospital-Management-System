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

import kon.atha.HospitalManagement.Entity.Bill;
import kon.atha.HospitalManagement.Entity.Payment;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/edit-payment")
public class PaymentEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int paymentId = Integer.parseInt(request.getParameter("id"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Payment payment = em.find(Payment.class, paymentId);

			if (payment == null) {

				response.getWriter().println("Payment not found.");

				return;
			}

			List<Bill> bills = em
					.createQuery("SELECT b FROM Bill b " + "LEFT JOIN FETCH b.patient " + "ORDER BY b.billId",
							Bill.class)
					.getResultList();

			request.setAttribute("payment", payment);
			request.setAttribute("bills", bills);

			RequestDispatcher rd = request.getRequestDispatcher("edit-payment.jsp");

			rd.forward(request, response);

		} finally {
			em.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int paymentId = Integer.parseInt(request.getParameter("paymentId"));

		int billId = Integer.parseInt(request.getParameter("billId"));

		double amount = Double.parseDouble(request.getParameter("amount"));

		String paymentMode = request.getParameter("paymentMode");

		String transactionId = request.getParameter("transactionId");

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Payment payment = em.find(Payment.class, paymentId);

			Bill bill = em.find(Bill.class, billId);

			if (payment == null || bill == null) {

				response.getWriter().println("Payment or Bill not found.");

				return;
			}

			if (amount <= 0) {

				response.getWriter().println("Payment amount must be greater than 0.");

				return;
			}

			/*
			 * Calculate all payments for this bill except the current payment being edited.
			 */
			Double totalPaid = em
					.createQuery("SELECT COALESCE(SUM(p.amount), 0) " + "FROM Payment p "
							+ "WHERE p.bill.billId = :billId " + "AND p.paymentId <> :paymentId", Double.class)
					.setParameter("billId", billId).setParameter("paymentId", paymentId).getSingleResult();

			double remainingBalance = bill.getNetAmount() - totalPaid;

			if (amount > remainingBalance) {

				response.setContentType("text/html");

				response.getWriter().println("<h2>Payment Update Failed</h2>");

				response.getWriter().println("<p>Maximum allowed payment is ₹" + remainingBalance + "</p>");

				response.getWriter()
						.println("<br><a href='edit-payment?id=" + paymentId + "'>Back to Edit Payment</a>");

				return;
			}

			em.getTransaction().begin();

			payment.setBill(bill);
			payment.setAmount(amount);
			payment.setPaymentMode(paymentMode);
			payment.setTransactionId(transactionId);

			em.merge(payment);

			em.getTransaction().commit();

			response.sendRedirect("payments");

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

			response.getWriter().println("Error while updating payment.");

		} finally {
			em.close();
		}
	}
}