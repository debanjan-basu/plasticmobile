package HtmlScrapper.Common;

public class ScrapedData {
	
	private String url;
	private Value val;
	
	public class Value{
		private Long timestamp;
		private Object val;
		
		public Long getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(Long timestamp) {
			this.timestamp = timestamp;
		}
		public Object getVal() {
			return val;
		}
		public void setVal(Object val) {
			this.val = val;
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Value getVal() {
		return val;
	}

	public void setVal(Value val) {
		this.val = val;
	}
}
