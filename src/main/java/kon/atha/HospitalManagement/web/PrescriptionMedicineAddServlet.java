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

import kon.atha.HospitalManagement.Entity.Prescription;
import kon.atha.HospitalManagement.Entity.PrescriptionMedicine;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/add-prescription-medicine")
public class PrescriptionMedicineAddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            List<Prescription> prescriptions = em.createQuery(
                    "SELECT p FROM Prescription p " +
                    "LEFT JOIN FETCH p.appointment",
                    Prescription.class
            ).getResultList();

            request.setAttribute("prescriptions", prescriptions);

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("add-prescription-medicine.jsp");

            dispatcher.forward(request, response);

        } finally {
            em.close();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int prescriptionId =
                Integer.parseInt(request.getParameter("prescriptionId"));

        String medicineName =
                request.getParameter("medicineName");

        String dosage =
                request.getParameter("dosage");

        String duration =
                request.getParameter("duration");

        EntityManager em = JPAUtil.getEntityManager();

        try {

            Prescription prescription =
                    em.find(Prescription.class, prescriptionId);

            if (prescription == null) {

                response.sendRedirect("add-prescription-medicine");

                return;
            }

            PrescriptionMedicine medicine =
                    new PrescriptionMedicine();

            medicine.setMedicineName(medicineName);
            medicine.setDosage(dosage);
            medicine.setDuration(duration);
            medicine.setPrescription(prescription);

            em.getTransaction().begin();

            em.persist(medicine);

            em.getTransaction().commit();

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