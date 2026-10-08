package kon.atha.HospitalManagement.web;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.Admission;
import kon.atha.HospitalManagement.Entity.Bed;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/delete-admission")
public class AdmissionDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int admissionId = Integer.parseInt(request.getParameter("id"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Admission admission = em.find(Admission.class, admissionId);

			if (admission != null) {

				Bed bed = admission.getBed();

				em.getTransaction().begin();

				em.remove(admission);

				if (bed != null) {
					bed.setStatus("Available");
				}

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

		response.sendRedirect("admissions");
	}
}