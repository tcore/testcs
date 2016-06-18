package tcore.service;

import org.junit.Before;
import org.junit.Test;
import tcore.dto.User;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class UserProcessorImplTest {

    private PrintStream stream;

    private UserProcessorImpl userProcessor;

    @Before
    public void setUp() {
        stream = mock(PrintStream.class);
        userProcessor = new UserProcessorImpl(stream);
    }

    @Test
    public void testProcessActiveUser() {
        userProcessor.process(new User("test", true));
        verify(stream, times(1)).println("Sending email to test");
    }

    @Test
    public void testProcessNotActiveUser() {
        userProcessor.process(new User("test", false));
        verify(stream, times(0)).println("Sending email to test");
    }
}
