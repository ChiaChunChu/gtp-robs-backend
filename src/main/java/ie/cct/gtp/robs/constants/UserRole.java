package ie.cct.gtp.robs.constants;

public enum UserRole {
	
	ADMIN {

		@Override
		public String toString() {
			return "Admin";
		}
		
	},
	CUSTOMER {

		@Override
		public String toString() {
			return "Customer";
		}
		
	};
	
	public abstract String toString();
	
}
