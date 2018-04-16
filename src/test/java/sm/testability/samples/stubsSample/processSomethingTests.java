package sm.testability.samples.stubsSample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sm.testability.samples.stubsSample.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class processSomethingTests {
    @Mock
    MyDao dao;

    @InjectMocks
    CoordinationAlgorithm coordinator;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSendExpectedResponse() {
        MyResponse expected = new MyResponse();
        expected.setHelloName("Hello Don Quijote!");

        //Configure Stubs
        MyDto myDto = new MyDto();
        myDto.setName("Don Quijote");
        MyResponse response = new MyResponse();
        response.setHelloName("Hello Don Quijote");
        when(dao.retrieveSomething(myDto)).thenReturn(response);
        MyResponse actual = coordinator.processSomething(myDto);

        assertEquals(expected, actual);
    }
}
