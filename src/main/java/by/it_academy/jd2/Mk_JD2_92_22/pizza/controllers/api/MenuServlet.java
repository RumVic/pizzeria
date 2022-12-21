package by.it_academy.jd2.Mk_JD2_92_22.pizza.controllers.api;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.MenuDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenu;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IMenuService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.singletone.MenuServiceSingleton;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

//CRUD controller
//IMenu
@RestController
@RequestMapping ("/menu")
public class MenuServlet  { //extends HttpServlet

/*
    private final String CHARACTER_ENCODING = "UTF-8";              //characterEncoding
    private final String CONTENT_TYPE = "application/json";   //contentType
*/

    private IMenuService menuService;
    private ObjectMapper mapper;

    public MenuServlet() {
        this.menuService = MenuServiceSingleton.getInstance();
        this.mapper = new ObjectMapper();

        this.mapper = JsonMapper.builder()          //add module as mapper didn't read LocalDateTime
                .addModule(new JavaTimeModule())
                .build();
    }

    //Read POSITION
    //1) Read list
    //2) Read item (card) need id param
    @GetMapping
    @RequestMapping("/{id}")
    protected ResponseEntity<IMenu> get(@PathVariable() long id){
        IMenu read = menuService.read(id);
        return  ResponseEntity.ok(read);
        //   return  ResponseEntity.ok(menuService.read(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<List<IMenu>> get(){
        List<IMenu> list = menuService.get();
        return  ResponseEntity.ok(list);
    }

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();

        if (req.getParameter("id") != null) {
            IMenu positionById = menuService.read(Long.parseLong(req.getParameter("id")));
            writer.write(this.mapper.writeValueAsString(positionById));
        } else {
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
    @PostMapping
    public ResponseEntity<IMenu> doPost(@RequestBody MenuDTO menuDTO){
        IMenu created = this.menuService.create(menuDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
      */
    /*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IllegalArgumentException, ServletException, IOException {

        req.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setContentType(CONTENT_TYPE);

        IMenuDTO createDTO = this.mapper.readValue(req.getInputStream(), MenuDTO.class);
        try {
            resp.getWriter().write(this.mapper.writeValueAsString(menuService.create(createDTO)));
            //resp.setStatus(HttpServletResponse.SC_CONFLICT);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

     */

    //UPDATE POSITION
    //need param id
    //need param version/date_update - optimistic lock
    //body json
    @PostMapping("/{id}/{dt_update}")
    protected ResponseEntity<IMenu> doPut(@PathVariable long id,
                         @PathVariable("dt_update") long dtUpdateRow ,
                         @RequestBody MenuDTO data){
        LocalDateTime dtUpdate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(dtUpdateRow),
                ZoneId.of("UTS"));
        return ResponseEntity.ok(this.menuService.update(id,dtUpdate,data));
    }
}

    /*
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
        LocalDateTime dtUpdate = LocalDateTime.parse(req.getParameter("dtUpdate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        //2022-11-13T19:36:28.025110 this way send dtUpdate param; you need send in this way
        //2022-11-22T19:50:31.252
        try {
            MenuDTO createDTO = this.mapper.readValue(req.getInputStream(), MenuDTO.class);
            menuService.update(id, dtUpdate, createDTO);
        } catch (IllegalArgumentException i) {
            System.out.println("Check out accuracy wrote data");
        }
    }

     */


    //DELETE POSITION
    //need param id
    //need param version/date_update - optimistic lock
    /*
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

        menuService.delete(id, dtUpdate);
    }
}

     */
