package tcore.service;

import tcore.dto.User;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CsvUserIterator implements Iterator<User> {

    private BufferedReader bufferedReader;

    private String separator;

    private User next;

    public CsvUserIterator(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        separator = ",";
    }

    @Override
    public boolean hasNext() {
        if (null != next) {
            next = next();
        } else {
            try {
                next = next();

                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        return true;
    }

    @Override
    public User next() {
        if (null != next) {
            User current = next;
            next = null;

            return current;
        }

        try {
            String line = bufferedReader.readLine();
            if (null == line) {
                throw new NoSuchElementException();
            }
            String[] items = line.split(separator);

            return new User(items[0], Boolean.parseBoolean(items[1].trim()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
