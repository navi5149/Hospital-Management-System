package kon.atha.HospitalManagement.web;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.PrescriptionMedicine;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/delete-prescription-medicine")
public class PrescriptionMedicineDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			PrescriptionMedicine medicine = em.find(PrescriptionMedicine.class, id);

			if (medicine != null) {

				em.getTransaction().begin();

				em.remove(medicine);

				em.getTransaction().commit();
			}

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

		} finally {

			em.close();
		}

		response.sendRedirect("prescription-medicines");
	}
}