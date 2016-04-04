package ar.itba.edu.sia.tp1.utils;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ObjectUtils {
	static {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public static ToStringBuilder toStringBuilder(Object obj) {
		return new ToStringBuilder(obj);
	}
}
