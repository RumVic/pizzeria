package by.it_academy.jd2.Mk_JD2_92_22.pizza.controllers;

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
        //super.doGet(req, resp);

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("APPLICATION/JSON");
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


       /* try {
            String idParameter = req.getParameter("id");
            if(!idParameter.isEmpty()){
                long idLong = Long.parseLong(idParameter);
                PrintWriter writer = resp.getWriter();
                writer.write(this.mapper.writeValueAsString(menu.read(idLong)));
            }
        } catch (Exception e){
            System.out.println("something went wrong");
            System.out.println(e.getMessage());
        }*/
    }


    //CREATE POSITION
    //body json
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        //String  = req.getParameter()

    }

    //UPDATE POSITION
    //need param id
    //need param version/date_update - optimistic lock
    //body json
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPut(req, resp);
    }

    //DELETE POSITION
    //need param id
    //need param version/date_update - optimistic lock
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp);
    }
}
