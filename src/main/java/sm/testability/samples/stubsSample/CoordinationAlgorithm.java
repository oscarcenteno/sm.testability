package sm.testability.samples.stubsSample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/something")
public class CoordinationAlgorithm {

    @Autowired private MyDao myDao;

    public MyResponse processSomething(MyDto myDto){
        // Retrieve form external data source
        MyResponse myResponse = myDao.retrieveSomething(myDto);

        // Business Logic
        String name = myResponse.getHelloName() + "!";
        myResponse.setHelloName(name);

        return myResponse;
    }
}