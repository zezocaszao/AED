package stand;

public class Data {
	private int day;
	private int month;
	private int year;

	public Data(int d, int m, int y) {
		this.day = d;
		this.month = m;
		this.year = y;
	}

	public Data(String d) {
		String[] s = d.split("-");
		this.day = Integer.parseInt(s[0]);
		this.month = Integer.parseInt(s[1]);
		this.year = Integer.parseInt(s[2]);
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		String s = this.day + "-" + this.month + "-" + this.year;
		return s;
	}
}