package sion.bookmanagement.controller.member;
import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.util.validator.EmailValidator;
import sion.bookmanagement.util.validator.GenderValidator;
import sion.bookmanagement.util.validator.NameValidator;
import sion.bookmanagement.util.validator.PhoneNumberValidator;
import sion.bookmanagement.util.validator.PlusNumberValidator;
import sion.bookmanagement.util.validator.Validator;

public class MemberValidator implements Validator<Member>{
	private NameValidator nameValidator = new NameValidator();
	private GenderValidator genderValidator = new GenderValidator();
	private EmailValidator emailValidator = new EmailValidator();
	private PlusNumberValidator plusNumberValidator = new PlusNumberValidator();
	private PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
	
	@Override
	public void validate(Member member) {
		nameValidator.validate(member.getName());
		genderValidator.validate(member.getGender());
		emailValidator.validate(member.getEmail());
		plusNumberValidator.validate(member.getAge());
		phoneNumberValidator.validate(member.getPhone());
	}
}
