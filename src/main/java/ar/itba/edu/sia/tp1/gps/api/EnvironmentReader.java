package ar.itba.edu.sia.tp1.gps.api;

/**
 * Created by scamisay on 29/03/16.
 */
@FunctionalInterface
public interface EnvironmentReader {

    GPSState read();
}
