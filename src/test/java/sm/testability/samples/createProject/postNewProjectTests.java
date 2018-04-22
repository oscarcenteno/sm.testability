package sm.testability.samples.createProject;

import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sm.testability.samples.createProject.requests.*;
import sm.testability.samples.createProject.responses.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class postNewProjectTests {
    @Mock
    private
    DataSource dataSource;

    @InjectMocks
    private
    CoordinationAlgorithm coordinator;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSendGeneratedProjectToTheDatabase() {
        //Configure Stubs
        LocalDateTime date = LocalDateTime.of(2018, 04, 15, 12, 55);
        when(dataSource.getCurrentDateTime()).thenReturn(date);

        ArrayList names = new ArrayList();
        when(dataSource.getExistingProjectNames()).thenReturn(names);

        Project generatedProject = new Project("Jupiter", date);
        Project createdProject = new Project(9, "Jupiter", date);
        when(dataSource.saveProject(generatedProject)).thenReturn(createdProject);

        NewProject newProject = new NewProject("Jupiter");
        coordinator.postNewProject(newProject);

        // Verify Mocks
        verify(dataSource).saveProject(generatedProject);
    }

    @Test
    public void basicPathShouldReturnCreatedProjectResponse() {
        LocalDateTime date = LocalDateTime.of(2018, 04, 15, 12, 55);
        Project createdProject = new Project(9, "Jupiter", date);
        ResponseEntity<Object> expected = new ResponseEntity<>(createdProject, HttpStatus.OK);

        //Configure Stubs
        when(dataSource.getCurrentDateTime()).thenReturn(date);

        List<String> names = new ArrayList();
        when(dataSource.getExistingProjectNames()).thenReturn(names);

        Project generatedProject = new Project("Jupiter", date);
        Project newlyCreatedProject = new Project(9, "Jupiter", date);
        when(dataSource.saveProject(generatedProject)).thenReturn(newlyCreatedProject);

        NewProject newProject = new NewProject("Jupiter");
        ResponseEntity<Object> actual = coordinator.postNewProject(newProject);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnErrorForInvalidRequests() {
        LocalDateTime date = LocalDateTime.of(2018, 04, 15, 12, 55);
        NewProject newProject = new NewProject("Jupiter");
        ResponseEntity<Object> expected = new ResponseEntity<>(newProject, HttpStatus.BAD_REQUEST);

        //Configure Stubs
        when(dataSource.getCurrentDateTime()).thenReturn(date);

        List<String> names = new ArrayList();
        names.add("Jupiter");
        when(dataSource.getExistingProjectNames()).thenReturn(names);

        Project generatedProject = new Project("Jupiter", date);
        Project newlyCreatedProject = new Project(9, "Jupiter", date);
        when(dataSource.saveProject(generatedProject)).thenReturn(newlyCreatedProject);

        ResponseEntity<Object> actual = coordinator.postNewProject(newProject);

        assertEquals(expected, actual);
    }
}