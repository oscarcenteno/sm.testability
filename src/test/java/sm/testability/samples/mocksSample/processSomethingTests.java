package sm.testability.samples.mocksSample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        //Configure Stubs
        MyDto myDto = new MyDto();
        myDto.setName("Don Quijote");
        MyResponse response = new MyResponse();
        response.setHelloName("Hello Don Quijote");
        when(dao.retrieveSomething(myDto)).thenReturn(response);

        coordinator.processSomething(myDto);

        // Verify Mocks
        MyResponse expectedResponse = new MyResponse();
        expectedResponse.setHelloName("Hello Don Quijote!");
        verify(dao).sendSomething(expectedResponse);
    }
}
