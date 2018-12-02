package ru.unn.agile.InfixToPostfixNotationConverter.viewmodel;

import java.util.List;

public interface ILogger extends AutoCloseable {
    void log(String message);
    List<String> getMessages();
}
