package kon.atha.HospitalManagement.web;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.Bed;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/add-bed")
public class BedAddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("add-bed.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String bedNumber =
                request.getParameter("bedNumber");

        String bedType =
                request.getParameter("bedType");

        Double chargePerDay =
                Double.parseDouble(
                        request.getParameter("chargePerDay"));

        String status =
                request.getParameter("status");

        EntityManager em = JPAUtil.getEntityManager();

        try {

            Bed bed = new Bed();

            bed.setBedNumber(bedNumber);
            bed.setBedType(bedType);
            bed.setChargePerDay(chargePerDay);
            bed.setStatus(status);

            em.getTransaction().begin();

            em.persist(bed);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {

            em.close();
        }

        response.sendRedirect("beds");
    }
}