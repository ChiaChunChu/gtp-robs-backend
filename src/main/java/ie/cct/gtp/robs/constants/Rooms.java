package ie.cct.gtp.robs.constants;

import java.math.BigDecimal;

public enum Rooms {

	COMPARTMENT {

		@Override
		public String toString() {
			return "Compartment Room";
		}

		@Override
		public Integer getNumer() {
			return Constant.COMPARTMENT_NUM;
		}

		@Override
		public BigDecimal getPricePerHour() {
			return Constant.COMPARTMENT_PRICE_HOUR;
		}

		@Override
		public BigDecimal getPricePerDay() {
			return Constant.COMPARTMENT_PRICE_DAY;
		}
		
	},
	
	L_MEETING {

		@Override
		public String toString() {
			return "Large Meeting Room";
		}

		@Override
		public Integer getNumer() {
			return Constant.L_MEETING_NUM;
		}

		@Override
		public BigDecimal getPricePerHour() {
			return Constant.L_MEETING_PRICE_HOUR;
		}

		@Override
		public BigDecimal getPricePerDay() {
			return Constant.L_MEETING_PRICE_DAY;
		}
		
	},
	
	M_MEETING {

		@Override
		public String toString() {
			return "Medium Meeting Room";
		}

		@Override
		public Integer getNumer() {
			return Constant.M_MEETING_NUM;
		}

		@Override
		public BigDecimal getPricePerHour() {
			return Constant.M_MEETING_PRICE_HOUR;
		}

		@Override
		public BigDecimal getPricePerDay() {
			return Constant.M_MEETING_PRICE_DAY;
		}
		
	},
	
	S_MEETING {

		@Override
		public String toString() {
			return "Small Meeting Room";
		}

		@Override
		public Integer getNumer() {
			return Constant.S_MEETING_NUM;
		}

		@Override
		public BigDecimal getPricePerHour() {
			return Constant.S_MEETING_PRICE_HOUR;
		}

		@Override
		public BigDecimal getPricePerDay() {
			return Constant.S_MEETING_PRICE_DAY;
		}
		
	};
	
	public abstract String toString();
	public abstract Integer getNumer();
	public abstract BigDecimal getPricePerHour();
	public abstract BigDecimal getPricePerDay();
}
