package kon.atha.HospitalManagement.web;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			Long patientCount = em.createQuery("SELECT COUNT(p) FROM Patient p", Long.class).getSingleResult();

			Long doctorCount = em.createQuery("SELECT COUNT(d) FROM Doctor d", Long.class).getSingleResult();

			Long departmentCount = em.createQuery("SELECT COUNT(d) FROM Department d", Long.class).getSingleResult();

			Long appointmentCount = em.createQuery("SELECT COUNT(a) FROM Appointment a", Long.class).getSingleResult();

			Long bedCount = em.createQuery("SELECT COUNT(b) FROM Bed b", Long.class).getSingleResult();

			Long availableBedCount = em.createQuery("SELECT COUNT(b) FROM Bed b WHERE b.status = :status", Long.class)
					.setParameter("status", "Available").getSingleResult();

			Long occupiedBedCount = em.createQuery("SELECT COUNT(b) FROM Bed b WHERE b.status = :status", Long.class)
					.setParameter("status", "Occupied").getSingleResult();

			Long admissionCount = em.createQuery("SELECT COUNT(a) FROM Admission a", Long.class).getSingleResult();

			Long prescriptionCount = em.createQuery("SELECT COUNT(p) FROM Prescription p", Long.class)
					.getSingleResult();

			Long medicineCount = em.createQuery("SELECT COUNT(p) FROM PrescriptionMedicine p", Long.class)
					.getSingleResult();

			Long labTestCount = em.createQuery("SELECT COUNT(l) FROM LabTest l", Long.class).getSingleResult();

			Long testOrderCount = em.createQuery("SELECT COUNT(t) FROM TestOrder t", Long.class).getSingleResult();

			Long billCount = em.createQuery("SELECT COUNT(b) FROM Bill b", Long.class).getSingleResult();

			Long paymentCount = em.createQuery("SELECT COUNT(p) FROM Payment p", Long.class).getSingleResult();

			request.setAttribute("patientCount", patientCount);
			request.setAttribute("doctorCount", doctorCount);
			request.setAttribute("departmentCount", departmentCount);
			request.setAttribute("appointmentCount", appointmentCount);
			request.setAttribute("bedCount", bedCount);
			request.setAttribute("availableBedCount", availableBedCount);
			request.setAttribute("occupiedBedCount", occupiedBedCount);
			request.setAttribute("admissionCount", admissionCount);
			request.setAttribute("prescriptionCount", prescriptionCount);
			request.setAttribute("medicineCount", medicineCount);
			request.setAttribute("labTestCount", labTestCount);
			request.setAttribute("testOrderCount", testOrderCount);
			request.setAttribute("billCount", billCount);
			request.setAttribute("paymentCount", paymentCount);

			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");

			rd.forward(request, response);

		} finally {
			em.close();
		}
	}
}