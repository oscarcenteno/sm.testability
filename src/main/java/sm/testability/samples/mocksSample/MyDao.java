package sm.testability.samples.mocksSample;

public interface MyDao {
    public MyResponse retrieveSomething(MyDto myDto);
    public void sendSomething(MyResponse myResponse);
}
