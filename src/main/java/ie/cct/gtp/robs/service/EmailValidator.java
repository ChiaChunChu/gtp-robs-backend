package ie.cct.gtp.robs.service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {

	@Override
	public boolean test(String t) {

		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		
		return pattern.matcher(t).matches();
	}

}
