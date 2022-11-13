package by.it_academy.jd2.Mk_JD2_92_22.pizza.controllers;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.MenuDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenu;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IMenuService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.singletone.MenuServiceSingleton;
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

//CRUD controller
//IMenu
@WebServlet(name = "MenuServlet", urlPatterns = "/menu")
public class MenuServlet extends HttpServlet {

    private final String CE = "UTF-8";              //characterEncoding
    private final String CT = "application/json";   //contentType

    private IMenuService menuService;
    private ObjectMapper mapper;

    public MenuServlet(){
        this.menuService = MenuServiceSingleton.getInstance();
        this.mapper = new ObjectMapper();

        this.mapper = JsonMapper.builder()          //add module as mapper didn't read LocalDateTime
                .addModule(new JavaTimeModule())
                .build();
    }



    //Read POSITION
    //1) Read list
    //2) Read item (card) need id param
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(CE);
        resp.setCharacterEncoding(CE);
        resp.setContentType(CT);
        PrintWriter writer = resp.getWriter();

        if (req.getParameter("id") != null) {
            IMenu positionById = menuService.read(Long.parseLong(req.getParameter("id")));
            writer.write(this.mapper.writeValueAsString(positionById));
        }else {
            try {
                List<IMenu> menuList = menuService.get();
                writer.write(this.mapper.writeValueAsString(menuList));
            } catch (Exception e) {
                System.out.println("Something went wrong in try block");
            }
        }
    }


    //CREATE POSITION
    //body json
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IllegalArgumentException ,ServletException, IOException {

        req.setCharacterEncoding(CE);
        resp.setCharacterEncoding(CE);
        resp.setContentType(CT);

        MenuDTO createDTO = this.mapper.readValue(req.getInputStream(),MenuDTO.class);
        try {
            resp.getWriter().write(this.mapper.writeValueAsString(menuService.create(createDTO)));
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

        if(!req.getParameter("id").isEmpty()){
            throw new IllegalArgumentException("You didn't pass id parameter");
        }
        if(!req.getParameter("dtUpdate").isEmpty()){
            throw new IllegalArgumentException("You didn't pass dtUpdate parameter");
        }

        long id = Long.parseLong(req.getParameter("id"));
        LocalDateTime dtUpdate = LocalDateTime.parse(req.getParameter("dtUpdate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        //2022-11-13T19:36:28.025110 this way send dtUpdate param; you need send in this way
        try {
            MenuDTO createDTO = this.mapper.readValue(req.getInputStream(),MenuDTO.class);
            menuService.update(id,dtUpdate,createDTO);
        }catch (IllegalArgumentException i){
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

        if(!req.getParameter("id").isEmpty()){
            throw new IllegalArgumentException("You didn't pass id parameter");
        }
        if(!req.getParameter("dtUpdate").isEmpty()){
            throw new IllegalArgumentException("You didn't pass dtUpdate parameter");
        }

        long id = Long.parseLong(req.getParameter("id"));
        LocalDateTime dtUpdate = LocalDateTime.parse(req.getParameter("dtUpdate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        menuService.delete(id,dtUpdate);
    }
}
