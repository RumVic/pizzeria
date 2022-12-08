package by.it_academy.jd2.Mk_JD2_92_22.pizza.controllers.api;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.SelectedItemDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.ISelectedItemDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.ISelectedItem;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.ISelectedItemService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.singletone.SelectedItemServiceSingleton;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "SelectedItemServlet",urlPatterns ="/selected/item")
public class SelectedItemServlet extends HttpServlet {

    private final String CHARACTER_ENCODING = "UTF-8";

    private final String CONTENT_TYPE = "application/json";

    public ObjectMapper mapper;

    public ISelectedItemService selectedItemService;

    public SelectedItemServlet() {

        this.selectedItemService = SelectedItemServiceSingleton.getInstance();

        this.mapper = new ObjectMapper();

        this.mapper = JsonMapper.builder()          //add module as mapper didn't read LocalDateTime
                .addModule(new JavaTimeModule())
                .build();
    }

    // get List<MenuRow>
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();

        if (req.getParameter("id") != null) {
            ISelectedItem positionById = selectedItemService.read(Long.parseLong(req.getParameter("id")));
            writer.write(this.mapper.writeValueAsString(positionById));
        } else {
            try {
                List<ISelectedItem> selectedItems = selectedItemService.get();
                writer.write(this.mapper.writeValueAsString(selectedItems));
            } catch (Exception e) {
                System.out.println("Something went wrong in try block");
            }
        }

    }

    // we send json which we get from get request
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setContentType(CONTENT_TYPE);

        ISelectedItemDTO createDTO = this.mapper.readValue(req.getInputStream(), SelectedItemDTO.class);
        try {
            resp.getWriter().write(this.mapper.writeValueAsString(selectedItemService.create(createDTO)));
            //resp.setStatus(HttpServletResponse.SC_CONFLICT);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    // we can change our selectedItem
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setContentType(CONTENT_TYPE);

        if (req.getParameter("id").isEmpty()) {
            throw new IllegalArgumentException("You didn't pass id parameter");
        }
        if (req.getParameter("dtUpdate").isEmpty()) {
            throw new IllegalArgumentException("You didn't pass dtUpdate parameter");
        }

        long id = Long.parseLong(req.getParameter("id"));
        LocalDateTime dtUpdate = LocalDateTime.parse(req.getParameter("dtUpdate"),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        //2022-11-13T19:36:28.025110 this way send dtUpdate param; you need send in this way
        //2022-11-22T19:50:31.252
        try {
            ISelectedItemDTO createDTO = this.mapper.readValue(req.getInputStream(), SelectedItemDTO.class);
            selectedItemService.update(id, dtUpdate, createDTO);
        } catch (IllegalArgumentException i) {
            System.out.println("Check out accuracy wrote data");
        }

    }

    // we can delete our order
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setContentType(CONTENT_TYPE);

        if (req.getParameter("id").isEmpty()) {
            throw new IllegalArgumentException("You didn't pass id parameter");
        }
        if (req.getParameter("dtUpdate").isEmpty()) {
            throw new IllegalArgumentException("You didn't pass dtUpdate parameter");
        }

        long id = Long.parseLong(req.getParameter("id"));
        LocalDateTime dtUpdate = LocalDateTime.parse(req.getParameter("dtUpdate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        selectedItemService.delete(id, dtUpdate);

    }
}
