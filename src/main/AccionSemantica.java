package main;

public abstract class AccionSemantica {
    protected static String buffer = "";

    public abstract Token run();
}
