package tech.cano.engine.formatter;

public abstract class Formatter {
    
    protected final String name;
    
    public Formatter(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract String apply(String primary);

}
