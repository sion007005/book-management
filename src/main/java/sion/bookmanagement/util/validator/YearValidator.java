package sion.bookmanagement.util.validator;

public class YearValidator implements Validator<Integer> {
	private HasValueValidator hasValueValidator = new HasValueValidator();

	@Override
	public void validate(Integer content) {
		hasValueValidator.validate(String.valueOf(content), "year");
		
		try {
			if(content < 0 || content > 2020) {
				throw new ValidateException("올바르지 않은 년도 입력");
			}
		} catch(Exception e) {
			throw new ValidateException("Exceptionk 발생! 이유는 찾아봐", e);
		}
	}

}
