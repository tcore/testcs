package tcore.service;

import tcore.dto.User;

import java.io.PrintStream;

public class UserProcessorImpl implements UserProcessor {

    private PrintStream out;

    public UserProcessorImpl(PrintStream out) {
        this.out = out;
    }

    @Override
    public void process(User user) {
        if (user.isActive()) {
            out.println("Sending email to " + user.getEmail());
        }
    }

}
