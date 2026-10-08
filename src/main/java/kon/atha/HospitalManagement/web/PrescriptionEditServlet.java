package kon.atha.HospitalManagement.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.Appointment;
import kon.atha.HospitalManagement.Entity.Prescription;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/edit-prescription")
public class PrescriptionEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int prescriptionId = Integer.parseInt(request.getParameter("id"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Prescription prescription = em.find(Prescription.class, prescriptionId);

			if (prescription == null) {

				response.sendRedirect("prescriptions");
				return;
			}

			List<Appointment> appointments = em.createQuery(
					"SELECT a FROM Appointment a " + "LEFT JOIN FETCH a.patient " + "LEFT JOIN FETCH a.doctor",
					Appointment.class).getResultList();

			request.setAttribute("prescription", prescription);

			request.setAttribute("appointments", appointments);

			request.getRequestDispatcher("edit-prescription.jsp").forward(request, response);

		} finally {

			em.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int prescriptionId = Integer.parseInt(request.getParameter("prescriptionId"));

		int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));

		String prescriptionDate = request.getParameter("prescriptionDate");

		String notes = request.getParameter("notes");

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Prescription prescription = em.find(Prescription.class, prescriptionId);

			Appointment appointment = em.find(Appointment.class, appointmentId);

			if (prescription != null && appointment != null) {

				prescription.setPrescriptionDate(LocalDateTime.parse(prescriptionDate));

				prescription.setNotes(notes);
				prescription.setAppointment(appointment);

				em.getTransaction().begin();

				em.merge(prescription);

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

		response.sendRedirect("prescriptions");
	}
}