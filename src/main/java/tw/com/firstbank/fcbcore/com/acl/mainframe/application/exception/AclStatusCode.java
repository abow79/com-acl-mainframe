package tw.com.firstbank.fcbcore.com.acl.mainframe.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AclStatusCode {

	INVALID_USER_NAME("0001"), INVALID_USER_CREDIT("1002"), INVALID_USER_EMAIL(
			"1003"), INVALID_USER_DATA("1004");

	private final String code;

}
