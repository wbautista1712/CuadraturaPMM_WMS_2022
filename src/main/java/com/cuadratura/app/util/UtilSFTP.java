package com.cuadratura.app.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//import net.schmizz.sshj.SSHClient;
// import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

@Component
public class UtilSFTP {
	
	@Value("${sftp.ocqs.host}")
    private String host;
	
	@Value("${sftp.ocqs.username}")
    private String username;
	
	@Value("${sftp.ocqs.password}")
    private String password;
	
	@Value("${stfp.ocqs.remote}")
    private String remote;
	
	@Value("${dir.occa.url}")
    private String url;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemote() {
		return remote;
	}

	public void setRemote(String remote) {
		this.remote = remote;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/*
	public SSHClient setupSshj() throws IOException {
	    SSHClient client = new SSHClient();
	    client.addHostKeyVerifier(new PromiscuousVerifier());
	    client.connect(getHost());
	    client.authPassword(getUsername(), getPassword());
	    return client;
	}
	*/
}
