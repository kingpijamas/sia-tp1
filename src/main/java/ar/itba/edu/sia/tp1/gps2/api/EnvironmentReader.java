package ar.itba.edu.sia.tp1.gps2.api;

import ar.itba.edu.sia.tp1.gps.api.GPSState;

/**
 * Created by scamisay on 29/03/16.
 */
public interface EnvironmentReader {

    GPSState read();
}
