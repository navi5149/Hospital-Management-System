package kon.atha.HospitalManagement.web;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.Bill;
import kon.atha.HospitalManagement.util.*;

@WebServlet(name = "BillListServlet", urlPatterns = "/bill-list")
public class BillListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            List<Bill> bills = em.createQuery(
                    "SELECT b FROM Bill b",
                    Bill.class
            ).getResultList();

            request.setAttribute("bills", bills);

            request.getRequestDispatcher("/bill-list.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            throw new ServletException(
                    "Error while loading bills",
                    e
            );

        } finally {

            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}