package by.it_academy.jd2.Mk_JD2_92_22.pizza.controllers;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IMenuService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//CRUD controller
//IMenuRow
@WebServlet(name = "MenuPositionServlet", urlPatterns = "/menu/positions")
public class MenuPositionServlet extends HttpServlet {

    private final String CE = "UTF-8";              //characterEncoding
    private final String CT = "aplication/json";    //contentType

    private IMenuService menuService;

    private ObjectMapper mapper = new ObjectMapper();

    //Read POSITION
    //1) Read list
    //2) Read item (card) need id param
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doGet(req, resp);
   /* req.setCharacterEncoding(CE);
    resp.setContentType(CT);
    resp.setCharacterEncoding(CE);

    String idParameter = req.getParameter("id");

    if(!idParameter.isEmpty()){
        long idLong = Long.parseLong(idParameter);
             toString(menuService.read(idLong));

             }
       }
*/
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
