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

import kon.atha.HospitalManagement.Entity.Appointment;
import kon.atha.HospitalManagement.Entity.Prescription;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/add-prescription")
public class PrescriptionAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			List<Appointment> appointments = em.createQuery("SELECT a FROM Appointment a", Appointment.class)
					.getResultList();

			System.out.println("Appointments found: " + appointments.size());

			request.setAttribute("appointments", appointments);

			RequestDispatcher dispatcher = request.getRequestDispatcher("add-prescription.jsp");

			dispatcher.forward(request, response);

		} finally {
			em.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));

			String prescriptionDate = request.getParameter("prescriptionDate");

			String notes = request.getParameter("notes");

			System.out.println("Appointment ID: " + appointmentId);
			System.out.println("Prescription Date: " + prescriptionDate);
			System.out.println("Notes: " + notes);

			Appointment appointment = em.find(Appointment.class, appointmentId);

			if (appointment == null) {

				response.getWriter().println("Appointment not found.");

				return;
			}

			// Check whether this appointment already has a prescription
			if (appointment.getPrescription() != null) {

				response.getWriter().println("This appointment already has a prescription.");

				return;
			}

			Prescription prescription = new Prescription();

			prescription.setPrescriptionDate(java.time.LocalDateTime.parse(prescriptionDate));

			prescription.setNotes(notes);

			prescription.setAppointment(appointment);

			em.getTransaction().begin();

			em.persist(prescription);

			em.getTransaction().commit();

			System.out.println("Prescription added successfully!");

			response.sendRedirect("prescriptions");

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

			response.setContentType("text/html");

			response.getWriter().println("<h2>Prescription could not be added</h2>");
			response.getWriter().println("<p>Error: " + e.getMessage() + "</p>");

		} finally {

			em.close();
		}
	}
}