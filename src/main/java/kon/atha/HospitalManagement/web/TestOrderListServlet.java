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

import kon.atha.HospitalManagement.Entity.TestOrder;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/test-orders")
public class TestOrderListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			List<TestOrder> testOrders = em.createQuery(
					"SELECT t FROM TestOrder t " + "LEFT JOIN FETCH t.patient " + "LEFT JOIN FETCH t.labTest",
					TestOrder.class).getResultList();

			request.setAttribute("testOrders", testOrders);

			RequestDispatcher rd = request.getRequestDispatcher("test-orders.jsp");

			rd.forward(request, response);

		} finally {
			em.close();
		}
	}
}