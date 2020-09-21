package main;

public abstract class AccionSemantica {
    protected String buffer = "";

    public abstract Token run();
}
