package ar.itba.edu.sia.tp1.calcudoku.marshall;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.boon.json.annotations.JsonInclude;
import org.boon.json.annotations.JsonProperty;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;

class CalcudokuStateMapping {
	@JsonProperty("n")
	private int n;

	@JsonInclude
	@JsonProperty("groups")
	private List<GroupMapping> groupMappings;

	public CalcudokuStateMapping() {
	}

	public CalcudokuStateMapping(CalcudokuState state) {
		this(state.getN(), marshall(state.getGroups()));
	}

	private static List<GroupMapping> marshall(List<Group> groups) {
		return groups.stream().map(GroupMapping::new).collect(toList());
	}

	public CalcudokuStateMapping(int n, List<GroupMapping> groupMappings) {
		this.n = n;
		this.groupMappings = groupMappings;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public List<GroupMapping> getGroupMappings() {
		return groupMappings;
	}

	public void setGroupMappings(List<GroupMapping> groupMappings) {
		this.groupMappings = groupMappings;
	}

	public CalcudokuState unmarshall() {
		return new CalcudokuState(n, unmarshall(groupMappings));
	}

	private List<Group> unmarshall(List<GroupMapping> mappings) {
		return mappings.stream().map(GroupMapping::unmarshall)
				.collect(toList());
	}
}