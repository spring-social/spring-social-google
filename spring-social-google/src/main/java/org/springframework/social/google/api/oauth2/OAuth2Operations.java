package org.springframework.social.google.api.oauth2;

import org.springframework.social.google.api.plus.Person;

/**
 * Defines operations for Google Accounts Authentification and Authorization. Requires OAuth scope(s)
 * from the following:
 * <ul>
 * <li>openid/li>
 * <li>profile</li>
 * <li>email</li>
 * </ul>
 * See <a
 * href="https://developers.google.com/accounts/docs/OpenIDConnect">https://developers.google.com/accounts/docs/OpenIDConnect</a> for details about the different scopes
 * 
 * @author Charles Maheu
 * 
 */
public interface OAuth2Operations {

	/**
	 * Retrieves the authenticated user's Google user.
	 * 
	 * @return the retrieved {@link Person}
	 */
	UserInfo getUserinfo();
}
