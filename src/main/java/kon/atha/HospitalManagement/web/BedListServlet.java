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

import kon.atha.HospitalManagement.Entity.Bed;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/beds")
public class BedListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			List<Bed> beds = em.createQuery("SELECT b FROM Bed b", Bed.class).getResultList();

			request.setAttribute("beds", beds);

			RequestDispatcher dispatcher = request.getRequestDispatcher("beds.jsp");

			dispatcher.forward(request, response);

		} finally {

			em.close();
		}
	}
}