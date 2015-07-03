package pt.ist.socialsoftware.blendedworkflow.domain;

public class LogRecord extends LogRecord_Base {

    public LogRecord(String date, String action, String value, String author) {
        setDate(date);
        setAction(action);
        setValue(value);
        setAuthor(author);
    }

}
