package pay;

public class Pay {
	
		private int paycode;
		private int paymoney;
		private String paysucc;
		private int rentcode;
		
		public Pay(int paycode, int paymoney, String paysucc, int rentcode) {
			this.paycode = paycode;
			this.paymoney = paymoney;
			this.paysucc = paysucc;
			this.rentcode = rentcode;
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

		public String getPaysucc() {
			return paysucc;
		}

		public void setPaysucc(String paysucc) {
			this.paysucc = paysucc;
		}

		public int getRentcode() {
			return rentcode;
		}

		public void setRentcode(int rentcode) {
			this.rentcode = rentcode;
		}

		@Override
		public String toString() {
			return "결제 [페이코드=" + paycode + ", 결제금액=" + paymoney + ", 결제성공=" + paysucc + ", 렌트코드="
					+ rentcode + "]";
		}
		
}
