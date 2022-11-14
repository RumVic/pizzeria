package by.it_academy.jd2.Mk_JD2_92_22.pizza.controllers;

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
import java.util.ArrayList;
import java.util.List;

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
       // super.doGet(req, resp);
    req.setCharacterEncoding(CE);
    resp.setContentType(CT);
    resp.setCharacterEncoding(CE);
    PrintWriter writer = resp.getWriter();


        if(req.getParameter("id")!=null){
        IMenuRow menuRowById =
                menuRowService.read(Long.parseLong(req.getParameter("id")));
        writer.write(this.mapper.writeValueAsString(menuRowById));
             }else try {
            List<IMenuRow> iMenuRowList = new ArrayList<>();
            iMenuRowList = menuRowService.get();
        writer.write(this.mapper.writeValueAsString(iMenuRowList));
        } catch (Exception e){
            System.out.println("We can't get MenuRow list");
        }

    }

    //CREATE POSITION
    //body json
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
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
