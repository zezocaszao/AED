public class Date {
	private int month;
	private int day;
	private int year;

	public Date(int month, int day, int year) { // constructor
		if (!isValid(month, day, year)) {
			throw new IllegalArgumentException("Data invalida");
		}
		this.month = month;
		this.day = day;
		this.year = year;
	}

	private boolean isValid(int month, int day, int year) {
		if (month < 1 || month > 12)
			return false;

		if (year < 0)
			return false;

		if (day < 1 || day > daysInMonth(month, year))
			return false;

		return true;
	}

	private int daysInMonth(int month, int year) {
		if (month == 4 || month == 6 || month == 9 || month == 11)
			return 30;

		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}

		return 31;
	}

	private boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	public int month() { // get the month
		return month;
	}

	public int day() { // get the day
		return day;
	}

	public int year() { // get the year
		return year;
	}

	@Override
	public String toString() { // string representation
		return month() + "/" + day + "/" + year;
	}

	public boolean before(Date other) { // is this date before other?
		if (this.year() < other.year()) {
			return true;
		} else if (this.year() == other.year() && this.month() < other.month()) {
			return true;
		} else if (this.year() == other.year() && this.month() == other.month() && this.day() < other.day()) {
			return true;
		} else {
			return false;
		}
	}

	public int daysSinceBeginYear() { // number of days since the start of the year
		int diasMeses = 0;
		for (int i = 1; i < month; i++) {
			diasMeses += daysInMonth(i, year);
		}
		return diasMeses + day;
	}

	public int daysUntilEndYear() { // number of days until the end of the year
		return daysInYear(year) - daysSinceBeginYear();
	}

	private int daysInYear(int year) {
		if (isLeapYear(year)) {
			return 366;

		} else {
			return 365;
		}
	}

	public int daysBetween(Date other) {// number of days between this date and other
		int diasAntes = 0;
		int diasDepois = 0;
		int diasAnos = 0;
		int diasTotais = 0;

		Date before;
		Date after;

		if (this.before(other)) {
			before = this;
			after = other;
		} else {
			before = other;
			after = this;
		}

		if (after.year - before.year() > 0) {
			diasDepois = after.daysSinceBeginYear();
			diasAntes = before.daysUntilEndYear();
			System.out.println(diasDepois + " " + diasAntes);
		} else {
			diasDepois = -after.daysUntilEndYear();
			diasAntes = before.daysUntilEndYear();
			System.out.println(diasDepois + " " + diasAntes);

		}
		for (int y = before.year() + 1; y < after.year(); y++) {
			diasAnos += daysInYear(y);
			System.out.println(diasAnos);

		}
		diasTotais = diasAntes + diasDepois + diasAnos;
		return Math.abs(diasTotais);
	}

	public static void main(String[] args) {
		Date date = new Date(12, 1, 2006);// 11, 20, 2001
		Date other = new Date(11, 20, 2006);
		System.out.println(date);
		System.out.println(date.before(other));
		System.out.println(date.daysSinceBeginYear());
		System.out.println(date.daysUntilEndYear());
		System.out.println(date.daysBetween(other));
	}

}