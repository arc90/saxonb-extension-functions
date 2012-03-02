/**
 * 
 */
package com.arc90.saxonb.extension.exslt;

import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.YearMonth;
import org.joda.time.format.ISOPeriodFormat;

/**
 * This class implements extension functions in the http://exslt.org/dates-and-times 
 * namespace that are not implemented in SaxonB 9.1.0.8.
 *
 * @author Andrew Lewisohn
 */
public final class Date {

	private static final Pattern xsDateTime = Pattern.compile("\\d{2,4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d{0,3})?((-|\\+)\\d{2}:\\d{2})?");
	private static final Pattern xsDate = Pattern.compile("\\d{2,4}-\\d{2}-\\d{2}");
	private static final Pattern gMonth = Pattern.compile("\\d{2,4}-\\d{2}");
	private static final Pattern gYear = Pattern.compile("\\d{2,4}");
	
	/**
	 * The date:add function returns the date/time resulting from adding a 
	 * duration to a date/time. 
	 * 
	 * <p>The first argument must be right-truncated date/time strings in one 
	 * of the formats defined in [XML Schema Part 2: Datatypes]. The permitted 
	 * formats are as follows: 
	 * <ul>
	 * <li>xs:dateTime (CCYY-MM-DDThh:mm:ss)</li>
	 * <li>xs:date (CCYY-MM-DD)</li>
	 * <li>xs:gYearMonth (CCYY-MM)</li>
	 * <li>xs:gYear (CCYY)</li>
	 * </ul>
	 * </p>
	 * 
	 * <p>The second argument is a string in the format defined for xs:duration 
	 * in [3.2.6 duration] of [XML Schema Part 2: Datatypes].</p>
	 * 
	 * @param dateTime a string representing a date/time, or date/time fragment
	 * @param duration a string in xs:duration format
	 * @return a right-truncated date/time strings in one of the 
	 * 	formats defined in [XML Schema Part 2: Datatypes] and listed above. This 
	 * 	value is calculated using the algorithm described in 
	 * 	[Appendix E Adding durations to dateTimes] of 
	 * 	[XML Schema Part 2: Datatypes].
	 * @throws IllegalArgumentException if any null or empty arguments are passed
	 */
	public static String add(String dateTime, String duration) {
		if (dateTime == null || duration == null || duration.equals("")) {
			throw new IllegalArgumentException("Invalid arguments were passed.");
		}
		
		Period period = ISOPeriodFormat.standard().parsePeriod(duration);
		
		if (xsDateTime.matcher(dateTime).matches()) {
			DateTime dt = DateTime.parse(dateTime);
			String newDateTime = dt.plus(period).toString("yyyy-MM-dd'T'HH:mm:ss");
			return newDateTime;
		} else if (xsDate.matcher(dateTime).matches()) {
			LocalDate ld = LocalDate.parse(dateTime);
			return ld.plus(period).toString("yyyy-MM-dd");
		} else if (gMonth.matcher(dateTime).matches()) {
			YearMonth yearMonth = YearMonth.parse(dateTime);
			return yearMonth.plus(period).toString("yyyy-MM");
		} else if (gYear.matcher(dateTime).matches()) {
			YearMonth years = YearMonth.parse(dateTime);
			return years.plus(period).toString("yyyy");
		}

		return null;
	}
	
	private Date() {
	}
}
