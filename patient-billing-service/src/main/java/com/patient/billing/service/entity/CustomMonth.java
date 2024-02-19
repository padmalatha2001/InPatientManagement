package com.patient.billing.service.entity;


	public enum CustomMonth {
	    JANUARY("Jan", 1),
	    FEBRUARY("Feb", 2),
	    MARCH("Mar", 3),
	    APRIL("Apr", 4),
	    MAY("May", 5),
	    JUNE("Jun", 6),
	    JULY("Jul", 7),
	    AUGUST("Aug", 8),
	    SEPTEMBER("Sep", 9),
	    OCTOBER("Oct", 10),
	    NOVEMBER("Nov", 11),
	    DECEMBER("Dec", 12);

		 private final String abbreviation;
		    private final int value;

		    private CustomMonth(String abbreviation, int value) {
		        this.abbreviation = abbreviation;
		        this.value = value;
		    }

		    public String getAbbreviation() {
		        return abbreviation;
		    }

		    public int getValue() {
		        return value;
		    }

		    public static CustomMonth monthName(String abbreviation) {
		        for (CustomMonth month : values()) {
		            if (month.getAbbreviation().equalsIgnoreCase(abbreviation)) {
		                return month;
		            }
		        }
		        throw new IllegalArgumentException("No enum constant for abbreviation: " + abbreviation);
		    }

		    public static CustomMonth fullMonthName(String fullMonthName) {
		        for (CustomMonth month : values()) {
		            if (month.name().equalsIgnoreCase(fullMonthName)) {
		                return month;
		            }
		        }
		        throw new IllegalArgumentException("No enum constant for full month name: " + fullMonthName);
		    }
		
	}



