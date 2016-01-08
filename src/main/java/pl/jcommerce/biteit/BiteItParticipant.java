package pl.jcommerce.biteit;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(exclude = {"age", "mood"})
@Builder
@AllArgsConstructor
@ToString
public class BiteItParticipant {

	@NonNull
	private final String firstName;
	@NonNull
	private final String lastName;
	@NonNull
	private final LocalDate birthDate;
	@Setter
	private Mood mood;
	private int age;

	public int getAge() {
		if (age == 0) {
			log.debug("calculating age...");
			age = getBirthDate().until(LocalDate.now()).getYears();
		} else if (log.isDebugEnabled()) {
			log.debug("age already calculated to {}", age);
		}
		return age;
	}

	@AllArgsConstructor
	public static enum Mood {

		HAPPY("Wow! I'm participating in BiteIT :)"),
		OPTIMISTIC("We will deliver this sprint before deadline :)"),
		DEPRESSED("I don't want to live on this planet anymore :("),
		PESSIMISTIC("It won't work :("),
		UNCERTAIN("I dunno....");

		@Getter
		private final String description;

		@Override
		public String toString() {
			return name() + "(" + getDescription() + ")";
		}
	}

}
