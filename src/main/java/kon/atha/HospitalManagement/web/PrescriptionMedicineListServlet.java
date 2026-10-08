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

import kon.atha.HospitalManagement.Entity.PrescriptionMedicine;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/prescription-medicines")
public class PrescriptionMedicineListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            List<PrescriptionMedicine> medicines = em.createQuery(
                    "SELECT pm FROM PrescriptionMedicine pm " +
                    "LEFT JOIN FETCH pm.prescription",
                    PrescriptionMedicine.class
            ).getResultList();

            request.setAttribute("medicines", medicines);

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("prescription-medicines.jsp");

            dispatcher.forward(request, response);

        } finally {
            em.close();
        }
    }
}
