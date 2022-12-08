package by.it_academy.jd2.Mk_JD2_92_22.pizza.controllers.api;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IOrder;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IOrderService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.singletone.OrderServiceSingleton;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



//CRUD controller
//IOrder
@WebServlet(name ="OrderServlet",urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    private final String CHARACTER_ENCODING = "UTF-8";              //characterEncoding
    private final String CONTENT_TYPE = "application/json";   //contentType

    private IOrderService orderService;
    private ObjectMapper mapper;

    public OrderServlet() {
        this.orderService = OrderServiceSingleton.getInstance();
        this.mapper = new ObjectMapper();

        this.mapper = JsonMapper.builder()          //add module as mapper didn't read LocalDateTime
                .addModule(new JavaTimeModule())
                .build();
    }



    //get List<MenuRow>
    //we get join two table - selected_item and menu_row
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        if(id != null){
            IOrder read = orderService.read(Long.parseLong(id));
            resp.getWriter().write(this.mapper.writeValueAsString(read));
        } else {
            List<IOrder> read = orderService.get();
            resp.getWriter().write(this.mapper.writeValueAsString(read));
        }
    }

    // we pass state parameter
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    // we update state parameter
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    //we delete order for something reason
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
