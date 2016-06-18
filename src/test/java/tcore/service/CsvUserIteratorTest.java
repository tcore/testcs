package tcore.service;

import org.junit.Test;
import tcore.dto.User;
import tcore.exception.BadInputDataException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CsvUserIteratorTest {
    @Test
    public void testIterator() throws IOException {
        String email = "test@email.com";
        boolean active = true;

        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine())
                .thenReturn(email + ", " + Boolean.toString(active))
                .thenReturn(null);
        CsvUserIterator csvUserIterator = new CsvUserIterator(bufferedReader);
        assertTrue(csvUserIterator.hasNext());
        assertTrue(csvUserIterator.hasNext());
        User user = csvUserIterator.next();
        assertEquals(email, user.getEmail());
        assertEquals(active, user.isActive());

        assertFalse(csvUserIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmpty() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine())
                .thenReturn(null);
        CsvUserIterator csvUserIterator = new CsvUserIterator(bufferedReader);
        csvUserIterator.next();
    }

    @Test(expected = BadInputDataException.class)
    public void testBrokenData() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine())
                .thenReturn("some broken data");
        CsvUserIterator csvUserIterator = new CsvUserIterator(bufferedReader);
        csvUserIterator.next();
    }
}
