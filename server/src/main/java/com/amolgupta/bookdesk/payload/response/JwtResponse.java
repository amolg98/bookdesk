package com.amolgupta.bookdesk.payload.response;

import java.util.List;

public class JwtResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private String id;
    private String username;
    private String email;
    private List<String> roles;
    /**
     * @param token
     * @param type
     * @param id
     * @param username
     * @param email
     * @param roles
     */
    public JwtResponse(String accessToken, String id, String username, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
    
    /**
     * @return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }
    /**
     * @param accessToken the accessToken to set
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    /**
     * @return the tokenType
     */
    public String getTokenType() {
        return tokenType;
    }
    /**
     * @param tokenType the tokenType to set
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the roles
     */
    public List<String> getRoles() {
        return roles;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString() {
		return "JwtResponse [accessToken=" + accessToken + ", email=" + email + ", id=" + id + ", roles=" + roles
				+ ", tokenType=" + tokenType + ", username=" + username + "]";
	} 
    
}
