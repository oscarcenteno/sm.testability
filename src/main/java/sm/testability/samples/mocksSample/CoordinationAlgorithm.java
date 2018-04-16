package sm.testability.samples.mocksSample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/something")
public class CoordinationAlgorithm {

    @Autowired private MyDao myDao;

    public void processSomething(MyDto myDto){
        MyResponse myResponse = myDao.retrieveSomething(myDto);

        String name = myResponse.getHelloName() + "!";
        myResponse.setHelloName(name);

        myDao.sendSomething(myResponse);
    }
}