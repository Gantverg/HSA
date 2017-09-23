package tel_ran.hsa.protocols;

public class ProtocolEntity {
	private String header;
	private String body;

	public ProtocolEntity(String header, String body) {
		super();
		this.header = header;
		this.body = body;
	}

	public ProtocolEntity() {
	}

	public String getHeader() {
		return header;
	}

	public String getBody() {
		return body;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
