package tech.cano.engine.formatter;

public class StringFormatter {

    public String format(String primaryString, String choice) {
        Integer idx = Formatters.nameMap.get(choice);
        int choiceIndex = (idx != null) ? idx : 0;

        Formatter formatter = Formatters.buildInFormatters[choiceIndex];
        return formatter.apply(primaryString);
    }
}
