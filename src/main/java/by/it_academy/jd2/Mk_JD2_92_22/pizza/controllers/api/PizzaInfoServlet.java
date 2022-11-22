package by.it_academy.jd2.Mk_JD2_92_22.pizza.controllers.api;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.PizzaInfoDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IPizzaInfoDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IPizzaInfo;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IPizzaInfoService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.singletone.PizzaInfoServiceSingleton;
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

@WebServlet(name = "PizzaInfoServlet",urlPatterns = "/menu/info")
public class PizzaInfoServlet extends HttpServlet {
    private final String CHARACTER_ENCODING = "UTF-8";              //characterEncoding
    private final String CONTENT_TYPE = "application/json";         //contentType

    private IPizzaInfoService pizzaInfoService;

    private ObjectMapper mapper;

    public PizzaInfoServlet() {
        this.pizzaInfoService = PizzaInfoServiceSingleton.getInstance();
        this.mapper = new ObjectMapper();

        this.mapper = JsonMapper.builder()          //add module as mapper didn't read LocalDateTime
                .addModule(new JavaTimeModule())
                .build();
    }

    //Read POSITION
    //1) Read list
    //2) Read item (card) need id param
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();

        if (req.getParameter("id") != null) {
            IPizzaInfo positionById = pizzaInfoService.read(Long.parseLong(req.getParameter("id")));
            writer.write(this.mapper.writeValueAsString(positionById));
        } else {
            try {
                List<IPizzaInfo> menuList = pizzaInfoService.get();
                writer.write(this.mapper.writeValueAsString(menuList));
            } catch (Exception e) {
                System.out.println("Something went wrong in try block");
            }
        }
    }

    //CREATE POSITION
    //body json
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setContentType(CONTENT_TYPE);

        /*
    {
        "name": "Cuba",
        "description": "wiht rom",
        "size": 33
    }
         */

        IPizzaInfoDTO createDTO = this.mapper.readValue(req.getInputStream(), PizzaInfoDTO.class);
        try {
            resp.getWriter().write(this.mapper.writeValueAsString(pizzaInfoService.create(createDTO)));
            //resp.setStatus(HttpServletResponse.SC_CONFLICT);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }


    //UPDATE POSITION
    //need param id
    //need param version/date_update - optimistic lock
    //body json
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setContentType(CONTENT_TYPE);
        /*
        {
            "name": "Cuba",
            "description": "wiht rom",
            "size": 33,
                  }
        */

        if (req.getParameter("id").isEmpty()) {
            throw new IllegalArgumentException("You didn't pass id parameter");
        }
        if (req.getParameter("dtUpdate").isEmpty()) {
            throw new IllegalArgumentException("You didn't pass dtUpdate parameter");
        }

        long id = Long.parseLong(req.getParameter("id"));
        LocalDateTime dtUpdate = LocalDateTime.parse(req.getParameter("dtUpdate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        //2022-11-13T19:36:28.025110 this way send dtUpdate param; you need send in this way
        try {
            PizzaInfoDTO createDTO = this.mapper.readValue(req.getInputStream(), PizzaInfoDTO.class);
            pizzaInfoService.update(id, dtUpdate, createDTO);
        } catch (IllegalArgumentException i) {
            System.out.println("Check out accuracy wrote data");
        }

    }

    //DELETE POSITION
    //need param id
    //need param version/date_update - optimistic lock
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

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

        pizzaInfoService.delete(id, dtUpdate);
    }
}

