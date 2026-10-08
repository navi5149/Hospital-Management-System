package kon.atha.HospitalManagement.web;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kon.atha.HospitalManagement.Entity.Department;
import kon.atha.HospitalManagement.Entity.Doctor;
import kon.atha.HospitalManagement.util.JPAUtil;

@WebServlet("/add-doctor")
public class DoctorAddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            List<Department> departments =
                    em.createQuery(
                            "SELECT d FROM Department d",
                            Department.class)
                      .getResultList();

            request.setAttribute("departments", departments);

            request.getRequestDispatcher("add-doctor.jsp")
                   .forward(request, response);

        } finally {

            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String firstName =
                request.getParameter("firstName");

        String lastName =
                request.getParameter("lastName");

        String specialization =
                request.getParameter("specialization");

        String qualification =
                request.getParameter("qualification");

        String phone =
                request.getParameter("phone");

        String email =
                request.getParameter("email");

        int experience =
                Integer.parseInt(
                        request.getParameter("experience"));

        int departmentId =
                Integer.parseInt(
                        request.getParameter("departmentId"));

        EntityManager em = JPAUtil.getEntityManager();

        try {

            Department department =
                    em.find(Department.class, departmentId);

            if (department == null) {

                response.sendRedirect("add-doctor");

                return;
            }

            Doctor doctor = new Doctor();

            doctor.setFirstName(firstName);
            doctor.setLastName(lastName);
            doctor.setSpecialization(specialization);
            doctor.setQualification(qualification);
            doctor.setPhone(phone);
            doctor.setEmail(email);
            doctor.setExperience(experience);

            // Same relationship as your Controller
            doctor.setDepartment(department);

            em.getTransaction().begin();

            em.persist(doctor);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {

            em.close();
        }

        response.sendRedirect("doctors");
    }
}