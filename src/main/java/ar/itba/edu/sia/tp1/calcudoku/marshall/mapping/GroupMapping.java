package ar.itba.edu.sia.tp1.calcudoku.marshall.mapping;

import static ar.itba.edu.sia.tp1.utils.ObjectUtils.toStringBuilder;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.boon.json.annotations.JsonInclude;
import org.boon.json.annotations.JsonProperty;

import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Operator;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;

public class GroupMapping {
	@JsonInclude
	@JsonProperty("positions")
	private List<PositionMapping> positionMappings;

	@JsonProperty("operator")
	private Operator operator;

	@JsonInclude
	@JsonProperty("result")
	private int result;

	GroupMapping() {
	}

	public GroupMapping(List<PositionMapping> positions, Operator operator,
			int result) {
		this.positionMappings = positions;
		this.operator = operator;
		this.result = result;
	}

	public GroupMapping(Group group) {
		this(marshall(group.getPositions()), group.getOperator(), group
				.getResult());
	}

	private static List<PositionMapping> marshall(List<Position> positions) {
		return positions.stream().map(PositionMapping::new).collect(toList());
	}

	public List<PositionMapping> getPositionMappings() {
		return positionMappings;
	}

	public void setPositionMappings(List<PositionMapping> positionMappings) {
		this.positionMappings = positionMappings;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Group unmarshall() {
		List<Position> positions = unmarshall(positionMappings);
		return new Group(positions, operator, result);
	}

	private List<Position> unmarshall(List<PositionMapping> mappings) {
		return ListUtils.emptyIfNull(mappings).stream()
				.map(PositionMapping::unmarshall).collect(toList());
	}

	@Override
	public String toString() {
		return toStringBuilder(this).append("operator", operator)
				.append("result", result)
				.append("positionMappings", positionMappings).toString();
	}
}