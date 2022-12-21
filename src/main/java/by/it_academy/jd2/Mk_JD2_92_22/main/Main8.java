package by.it_academy.jd2.Mk_JD2_92_22.main;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IPizzaInfo;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IPizzaInfoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

public class Main8 {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("pizza.xml");
        IPizzaInfoService service  =  context.getBean(IPizzaInfoService.class);
        List<IPizzaInfo> iPizzaInfoList = service.get();
        for (IPizzaInfo pi : iPizzaInfoList) {
            System.out.println(pi.toString());
        }



    }
}
