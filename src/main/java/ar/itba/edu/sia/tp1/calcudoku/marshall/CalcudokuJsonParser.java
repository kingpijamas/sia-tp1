package ar.itba.edu.sia.tp1.calcudoku.marshall;

import java.io.InputStream;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.marshall.mapping.CalcudokuStateMapping;
import ar.itba.edu.sia.tp1.gps.ProblemParser;

public class CalcudokuJsonParser implements ProblemParser<CalcudokuState> {
    private static final ObjectMapper mapper = JsonFactory.createUseAnnotations(false);

    private final InputStream is;

    public CalcudokuJsonParser(InputStream is) {
        this.is = is;
    }

    @Override
    public CalcudokuState parse() {
        CalcudokuStateMapping stateMapping = mapper.readValue(is, CalcudokuStateMapping.class);
        return stateMapping.unmarshall();
    }
}
