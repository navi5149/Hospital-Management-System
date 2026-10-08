package kon.atha.HospitalManagement.web;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.Bill;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/delete-bill")
public class BillDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int billId = Integer.parseInt(request.getParameter("id"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Bill bill = em.find(Bill.class, billId);

			if (bill != null) {

				em.getTransaction().begin();

				em.remove(bill);

				em.getTransaction().commit();
			}

			response.sendRedirect("bills");

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

			response.getWriter().println("Cannot delete this bill. It may already have payments.");

		} finally {
			em.close();
		}
	}
}