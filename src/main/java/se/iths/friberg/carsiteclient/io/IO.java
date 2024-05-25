package se.iths.friberg.carsiteclient.io;

public interface IO{
    void print(Object o);
    void println();
    void println(Object o);
    String next();
    String nextLine();
    Integer nextInt();
    int validNextInt(int min, int max);
}
