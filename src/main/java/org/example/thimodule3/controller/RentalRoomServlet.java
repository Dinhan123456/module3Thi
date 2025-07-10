package org.example.thimodule3.controller;

import org.example.thimodule3.dao.*;
import org.example.thimodule3.model.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.*;
import java.util.stream.Collectors;

@WebServlet(name = "RentalRoomServlet", urlPatterns = "/rental")
public class RentalRoomServlet extends HttpServlet {
    private final RentalRoomDAO dao = new RentalRoomDAO();
    private final Logger logger = Logger.getLogger(RentalRoomServlet.class.getName());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if ("create-form".equals(action)) {
                PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO();
                List<Map.Entry<Integer, String>> paymentMethods = paymentMethodDAO.findAll();
                req.setAttribute("paymentMethods", paymentMethods);
                req.getRequestDispatcher("form.jsp").forward(req, resp);
                return;
            }

            if ("confirm".equals(action)) {
                String[] confirmIds = req.getParameterValues("confirm");
                if (confirmIds != null) {
                    List<Integer> ids = Arrays.stream(confirmIds)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    List<RentalRoom> rooms = dao.getRoomsByIds(ids);
                    req.setAttribute("roomsToConfirm", rooms);
                    req.getRequestDispatcher("confirm-delete.jsp").forward(req, resp);
                    return;
                }
            }

            String search = req.getParameter("search");
            List<RentalRoom> list = (search == null || search.isEmpty()) ? dao.findAll() : dao.search(search);
            req.setAttribute("rentalRooms", list);
            req.getRequestDispatcher("index.jsp").forward(req, resp);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error in doGet", e);
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            String name = req.getParameter("tenantName");
            String phone = req.getParameter("phoneNumber");
            String date = req.getParameter("startDate");
            String payment = req.getParameter("paymentMethodId");
            String note = req.getParameter("note");

            RentalRoom r = new RentalRoom();
            r.setTenantName(name);
            r.setPhoneNumber(phone);
            r.setStartDate(date);
            r.setPaymentMethodId(Integer.parseInt(payment));
            r.setNote(note);

            try {
                if (name.length() >= 5 && name.length() <= 50 && phone.matches("\\d{10}")) {
                    dao.save(r);
                    resp.sendRedirect("rental");
                } else {
                    req.setAttribute("error", "Thông tin không hợp lệ!");
                    req.setAttribute("paymentMethods", new PaymentMethodDAO().findAll());
                    req.getRequestDispatcher("form.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        } else if ("delete".equals(action)) {
            String[] deleteIds = req.getParameterValues("deleteIds");
            if (deleteIds != null) {
                List<Integer> ids = Arrays.stream(deleteIds)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                try {
                    dao.deleteByIds(ids);
                } catch (SQLException e) {
                    throw new ServletException(e);
                }
            }
            resp.sendRedirect("rental");
        }
    }
}
