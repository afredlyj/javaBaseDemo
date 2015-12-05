package afred.demo.spring_schema;

/**
 * Created by winnie on 15/12/5.
 */
public class DateFormat {

    private String pattern;

    private boolean lenient;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isLenient() {
        return lenient;
    }

    public void setLenient(boolean lenient) {
        this.lenient = lenient;
    }

    @Override
    public String toString() {
        return "DateFormat{" +
                "pattern='" + pattern + '\'' +
                ", lenient=" + lenient +
                '}';
    }
}
