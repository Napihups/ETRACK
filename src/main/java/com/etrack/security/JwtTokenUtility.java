package com.etrack.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.etrack.login.model.EUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtility implements Serializable {

	
	private static final long serialVersionUID = -5071084238673901034L;
	
	public static final String CLAIM_KEY_USERNAME = "sub";
	public static final String CLAIM_KEY_AUDIENCE = "audience";
	public static final String CLAIM_KEY_EXPIRED = "exp";
	public static final String CLAIM_KEY_CREATED = "created";
	
    static final String AUDIENCE_UNKNOWN = "unknown";
    static final String AUDIENCE_WEB = "web";
    static final String AUDIENCE_MOBILE = "mobile";
    static final String AUDIENCE_TABLET = "tablet";
    
	
 
    private static final Long expiration = new Long(604800);
    private static final String secret = "mySecret";
    
    //UTILITY METHODS//
    /**
     * 
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token){
    	String username;
    	try{
    		final Claims claims = getClaimsFromToken(token);
    		username = claims.getSubject();
    	}catch(Exception e){
    		username = null;
    	}
    	return username;
    }
    
    /**
     * 
     * @param token
     * @return
     */
    public Date getCreatedDateFromToken(String token) {
    	Date created;
    	try{
    		final Claims claims = getClaimsFromToken(token);
    		created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
    	}catch(Exception e){
    		created = null;
    	}
    	return created;
    }
    
    /**
     * 
     * @param token
     * @return
     */
    public Date getExpDateFromToken(String token) {
    	Date expDate;
    	try{
    		final Claims claims = getClaimsFromToken(token);
    		expDate = claims.getExpiration();
    	}catch(Exception e){
    		expDate = null;
    	}
    	return expDate;
    }
    
    /**
     * 
     * @param token
     * @return
     */
    public String getAudienceFromToken(String token) {
    	String audience;
    	try{
    		final Claims claims = getClaimsFromToken(token);
    		audience = (String) claims.get(CLAIM_KEY_AUDIENCE);
    	}catch(Exception e){
    		audience = null;
    	}
    	return audience;
    }
    
    
    /**
     * 
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token){
    	Claims claims;
    	try{
    		claims = Jwts.parser()
    				.setSigningKey(secret)
    				.parseClaimsJws(token)
    				.getBody();
    	}catch(Exception e){
    		claims = null;
    	}
    	return claims;
    }
    
    /**
     * 
     * @param token
     * @return
     */
    private Boolean isTokenExpiration(String token) {
    	final Date exp = getExpDateFromToken(token);
    	return exp.before(new Date());
    }
    
    /**
     * 
     * @param created
     * @param lastPasswordReset
     * @return
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset){
    	return (lastPasswordReset != null && created.before(lastPasswordReset));
    }
    
    
    /**
     * 
     * @param device
     * @return
     */
    private String generateAudience(Device device){
    	String audience = AUDIENCE_UNKNOWN;
    	if(device.isNormal()){
    		audience = AUDIENCE_WEB;
    	} else if (device.isTablet()){
    		audience = AUDIENCE_TABLET;
    	} else if (device.isMobile()){
    		audience = AUDIENCE_MOBILE;
    	}
    	return audience;
    }
    
    /**
     * 
     * @param token
     * @return
     */
    private Boolean ignoreTokenExpiration(String token) {
    	String audience = getAudienceFromToken(token);
    	return (AUDIENCE_TABLET.equals(audience)) || AUDIENCE_MOBILE.equals(audience);
    }
    
    /**
     * 
     * @param userDetails
     * @param device
     * @return
     */
    public String generateToken(UserDetails userDetails, Device device){
    	Map<String, Object> claims = new HashMap<>();
    	
    	claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
    	claims.put(CLAIM_KEY_AUDIENCE, generateAudience(device));
    	
    	final Date createdDate = new Date();
    	claims.put(CLAIM_KEY_CREATED, createdDate);
    	return doGenerateToken(claims);
    }
    
    /**
     * 
     * @param claims
     * @return
     */
    private String doGenerateToken(Map<String, Object> claims) {
    	final Date createdDate = (Date) claims.get(CLAIM_KEY_CREATED);
    	final Date expDate = new Date(createdDate.getTime() + expiration * 1000);
    	
    	System.out.println("doGenerateToken " + createdDate);
    	
    	return Jwts.builder()
    			.setClaims(claims)
    			.setExpiration(expDate)
    			.signWith(SignatureAlgorithm.HS512, secret)
    			.compact();
    }
    
    /**
     * 
     * @param token
     * @param lastPasswordReset
     * @return
     */
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
    	final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }
    
    /**
     * 
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = doGenerateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }
    
    /**
     * 
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        EUserDetails user = (EUserDetails) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        //final Date expiration = getExpirationDateFromToken(token);	
        return (
                username.equals(user.getUsername())
                        && !isTokenExpired(token)
                        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordReset()));
    }
    
    /**
     * 
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
    /**
     * 
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
