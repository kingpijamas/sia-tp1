package ar.itba.edu.sia.tp1.calcudoku.marshall;

import java.io.OutputStream;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;

import ar.itba.edu.sia.tp1.calcudoku.Calcudoku;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuRule;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.marshall.mapping.CalcudokuStateMapping;
import ar.itba.edu.sia.tp1.gps.ProblemSerializer;

public class CalcudokuJsonSerializer implements
		ProblemSerializer<Calcudoku, CalcudokuRule, CalcudokuState> {
	private static final ObjectMapper mapper = JsonFactory
			.createUseAnnotations(false);

	private final OutputStream os;

	public CalcudokuJsonSerializer(OutputStream os) {
		this.os = os;
	}

	@Override
	public void serialize(CalcudokuState initialState) {
		CalcudokuStateMapping mapping = new CalcudokuStateMapping(initialState);
		mapper.writeValue(os, mapping);
	}
}
