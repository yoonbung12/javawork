package pay;

public class Pay {
	
		private int paycode;
		private int paymoney;
		private String carsize;
		
		public Pay(int paycode, int paymoney, String carsize) {
			this.paycode = paycode;
			this.paymoney = paymoney;
			this.carsize = carsize;
		}

		public int getPaycode() {
			return paycode;
		}

		public void setPaycode(int paycode) {
			this.paycode = paycode;
		}

		public int getPaymoney() {
			return paymoney;
		}

		public void setPaymoney(int paymoney) {
			this.paymoney = paymoney;
		}

		public String getCarsize() {
			return carsize;
		}

		public void setCarsize(String carsize) {
			this.carsize = carsize;
		}

		@Override
		public String toString() {
			return "Pay [paycode=" + paycode + ", paymoney=" + paymoney + ", carsize=" + carsize + "]";
		}

				
}
