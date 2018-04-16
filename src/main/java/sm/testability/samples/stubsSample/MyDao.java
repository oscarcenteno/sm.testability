package sm.testability.samples.stubsSample;

public interface MyDao {
    public MyResponse retrieveSomething(MyDto myDto);
    public void sendSomething(MyResponse myResponse);
}
