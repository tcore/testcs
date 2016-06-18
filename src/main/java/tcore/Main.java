package tcore;

import tcore.dto.User;
import tcore.service.CsvUserIterator;
import tcore.service.UserProcessor;
import tcore.service.UserProcessorImpl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Iterator<User> iterator = new CsvUserIterator(new BufferedReader(new InputStreamReader(
                new FileInputStream(args[0])
        )));
        UserProcessor userProcessor = new UserProcessorImpl(System.out);

        Stream<User> stream = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED),
                true
        );
        stream.forEach(userProcessor::process);
    }
}
