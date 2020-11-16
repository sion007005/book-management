package sion.bookmanagement.util.validator;

public class PlusNumberValidator implements Validator<Integer> {
	private HasValueValidator hasValueValidator = new HasValueValidator();

	@Override
	public void validate(Integer content) {
		hasValueValidator.validate(String.valueOf(content), "숫자");
		
		try {
			if(content < 0) {
				throw new ValidateException("올바르지 않은 숫자 입력");
			}
		} catch(Exception e) {
			throw new ValidateException("Exceptionk 발생! 이유는 찾아봐", e);
		}
	}

}
