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

@WebServlet("/add-appointment")
public class AppointmentAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			List<Patient> patients = em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();

			List<Doctor> doctors = em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();

			request.setAttribute("patients", patients);
			request.setAttribute("doctors", doctors);

			request.getRequestDispatcher("add-appointment.jsp").forward(request, response);

		} finally {

			em.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int patientId = Integer.parseInt(request.getParameter("patientId"));

		int doctorId = Integer.parseInt(request.getParameter("doctorId"));

		String appointmentDate = request.getParameter("appointmentDate");

		String reason = request.getParameter("reason");

		String notes = request.getParameter("notes");

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Patient patient = em.find(Patient.class, patientId);

			Doctor doctor = em.find(Doctor.class, doctorId);

			if (patient != null && doctor != null) {

				Appointment appointment = new Appointment();

				appointment.setAppointmentDate(java.time.LocalDateTime.parse(appointmentDate));

				appointment.setReason(reason);
				appointment.setNotes(notes);
				appointment.setPatient(patient);
				appointment.setDoctor(doctor);

				em.getTransaction().begin();

				em.persist(appointment);

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