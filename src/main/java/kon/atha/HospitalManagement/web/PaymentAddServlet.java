package kon.atha.HospitalManagement.web;

import java.io.IOException;
import java.time.LocalDateTime;
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

@WebServlet("/add-payment")
public class PaymentAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			List<Bill> bills = em
					.createQuery("SELECT b FROM Bill b " + "LEFT JOIN FETCH b.patient " + "ORDER BY b.billId",
							Bill.class)
					.getResultList();

			request.setAttribute("bills", bills);

			RequestDispatcher rd = request.getRequestDispatcher("add-payment.jsp");

			rd.forward(request, response);

		} finally {
			em.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int billId = Integer.parseInt(request.getParameter("billId"));

		double amount = Double.parseDouble(request.getParameter("amount"));

		String paymentMode = request.getParameter("paymentMode");

		String transactionId = request.getParameter("transactionId");

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Bill bill = em.find(Bill.class, billId);

			if (bill == null) {

				response.getWriter().println("Bill not found.");

				return;
			}

			if (amount <= 0) {

				response.getWriter().println("Payment amount must be greater than 0.");

				return;
			}

			/*
			 * Calculate total amount already paid for this bill.
			 */
			Double totalPaid = em.createQuery(
					"SELECT COALESCE(SUM(p.amount), 0) " + "FROM Payment p " + "WHERE p.bill.billId = :billId",
					Double.class).setParameter("billId", billId).getSingleResult();

			double remainingBalance = bill.getNetAmount() - totalPaid;

			if (amount > remainingBalance) {

				response.setContentType("text/html");

				response.getWriter().println("<h2>Payment Failed</h2>");

				response.getWriter().println("<p>Remaining balance is ₹" + remainingBalance + "</p>");

				response.getWriter().println("<p>You cannot pay more than the remaining balance.</p>");

				response.getWriter().println("<br><a href='add-payment'>Back to Add Payment</a>");

				return;
			}

			Payment payment = new Payment();

			payment.setPaymentDate(LocalDateTime.now());
			payment.setAmount(amount);
			payment.setPaymentMode(paymentMode);
			payment.setTransactionId(transactionId);
			payment.setBill(bill);

			em.getTransaction().begin();

			em.persist(payment);

			em.getTransaction().commit();

			response.sendRedirect("payments");

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

			response.getWriter().println("Error while adding payment.");

		} finally {
			em.close();
		}
	}
}