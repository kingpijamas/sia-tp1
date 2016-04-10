package ar.itba.edu.sia.tp1.calcudoku.marshall.mapping;

import static ar.itba.edu.sia.tp1.util.ObjectUtils.toStringBuilder;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.boon.json.annotations.JsonInclude;
import org.boon.json.annotations.JsonProperty;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;

public class CalcudokuStateMapping {
	@JsonProperty("n")
	private int n;

	@JsonInclude
	@JsonProperty("groups")
	private List<GroupMapping> groupMappings;

	CalcudokuStateMapping() {
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
		return ListUtils.emptyIfNull(mappings).stream()
				.map(GroupMapping::unmarshall).collect(toList());
	}

	@Override
	public String toString() {
		return toStringBuilder(this).append("n", n)
				.append("groupMappings", groupMappings).toString();
	}
}