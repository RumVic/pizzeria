package by.it_academy.jd2.Mk_JD2_92_22.pizza.controllers.api;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.MenuRowDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IMenuRowDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenuRow;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IMenuRowService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.singletone.MenuRowServiceSingleton;
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


//CRUD controller
//IMenuRow
@WebServlet(name = "MenuPositionServlet", urlPatterns = "/menu/positions")
public class MenuRowServlet extends HttpServlet {

    private final String CE = "UTF-8";              //characterEncoding
    private final String CT = "aplication/json";    //contentType

    private IMenuRowService menuRowService;

    private ObjectMapper mapper = new ObjectMapper();

    public MenuRowServlet() {
        this.menuRowService = MenuRowServiceSingleton.getInstance();
        this.mapper = new ObjectMapper();
        this.mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }

    //Read POSITION
    //1) Read list
    //2) Read item (card) need id param
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    req.setCharacterEncoding(CE);
    resp.setContentType(CT);
    resp.setCharacterEncoding(CE);
    PrintWriter writer = resp.getWriter();


        if(req.getParameter("id")!=null){
        IMenuRow menuRowById =
                menuRowService.read(Long.parseLong(req.getParameter("id")));
        writer.write(this.mapper.writeValueAsString(menuRowById));
             }else
                 try {
        writer.write(this.mapper.writeValueAsString(menuRowService.get()));
        } catch (Exception e){
            System.out.println("We can't get MenuRow list");
        }

    }

    //CREATE POSITION
    //body json
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(CE);
        resp.setCharacterEncoding(CE);
        resp.setContentType(CT);

        IMenuRowDTO createDTO = this.mapper.readValue(req.getInputStream(), MenuRowDTO.class);
        try {
            resp.getWriter().write(this.mapper.writeValueAsString(menuRowService.create(createDTO)));
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(CE);
        resp.setCharacterEncoding(CE);
        resp.setContentType(CT);

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
            IMenuRowDTO createDTO = this.mapper.readValue(req.getInputStream(), MenuRowDTO.class);
            menuRowService.update(id, dtUpdate, createDTO);
        } catch (IllegalArgumentException i) {
            System.out.println("Check out accuracy wrote data");
        }

    }

    //DELETE POSITION
    //need param id
    //need param version/date_update - optimistic lock
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(CE);
        resp.setCharacterEncoding(CE);
        resp.setContentType(CT);

        if (req.getParameter("id").isEmpty()) {
            throw new IllegalArgumentException("You didn't pass id parameter");
        }
        if (req.getParameter("dtUpdate").isEmpty()) {
            throw new IllegalArgumentException("You didn't pass dtUpdate parameter");
        }

        long id = Long.parseLong(req.getParameter("id"));
        LocalDateTime dtUpdate = LocalDateTime.parse(req.getParameter("dtUpdate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        menuRowService.delete(id, dtUpdate);

    }
}
