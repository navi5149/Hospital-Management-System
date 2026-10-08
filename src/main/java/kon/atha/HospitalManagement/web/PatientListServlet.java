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

import kon.atha.HospitalManagement.Entity.Patient;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/patients")
public class PatientListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            List<Patient> patients =
                    em.createQuery(
                            "SELECT p FROM Patient p",
                            Patient.class)
                      .getResultList();

            request.setAttribute("patients", patients);

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("patients.jsp");

            dispatcher.forward(request, response);

        } finally {
            em.close();
        }
    }
}
