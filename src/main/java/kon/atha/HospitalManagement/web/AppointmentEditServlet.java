package kon.atha.HospitalManagement.web;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.Appointment;
import kon.atha.HospitalManagement.Entity.Doctor;
import kon.atha.HospitalManagement.Entity.Patient;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/edit-appointment")
public class AppointmentEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int appointmentId = Integer.parseInt(request.getParameter("id"));

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Appointment appointment = em.find(Appointment.class, appointmentId);

			if (appointment != null) {

				List<Patient> patients = em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();

				List<Doctor> doctors = em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();

				request.setAttribute("appointment", appointment);

				request.setAttribute("patients", patients);

				request.setAttribute("doctors", doctors);

				request.getRequestDispatcher("edit-appointment.jsp").forward(request, response);

			} else {

				response.sendRedirect("appointments");
			}

		} finally {

			em.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));

		int patientId = Integer.parseInt(request.getParameter("patientId"));

		int doctorId = Integer.parseInt(request.getParameter("doctorId"));

		String appointmentDate = request.getParameter("appointmentDate");

		String reason = request.getParameter("reason");

		String notes = request.getParameter("notes");

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Appointment appointment = em.find(Appointment.class, appointmentId);

			Patient patient = em.find(Patient.class, patientId);

			Doctor doctor = em.find(Doctor.class, doctorId);

			if (appointment != null && patient != null && doctor != null) {

				appointment.setAppointmentDate(java.time.LocalDateTime.parse(appointmentDate));

				appointment.setReason(reason);
				appointment.setNotes(notes);
				appointment.setPatient(patient);
				appointment.setDoctor(doctor);

				em.getTransaction().begin();

				em.merge(appointment);

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

		response.sendRedirect("appointments");
	}
}