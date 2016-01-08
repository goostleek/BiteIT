package pl.jcommerce.biteit;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(exclude = {"age", "mood"})
@Builder
@AllArgsConstructor
public class BiteItParticipant {

	@NonNull
	private final String firstName;
	@NonNull
	private final String lastName;
	@NonNull
	private final LocalDate birthDate;
	private int age;
	@Setter
	private Mood mood;

	public int getAge() {
		if (age == 0) {
			log.debug("calculating age...");
			age = getBirthDate().until(LocalDate.now()).getYears();
		} else if (log.isDebugEnabled()) {
			log.debug("age already calculated to {}", age);
		}
		return age;
	}

	public static enum Mood {

		HAPPY("Wow! I'm participating in BiteIT :)"),
		OPTIMISTIC("We will deliver this sprint before deadline :)"),
		DEPRESSED("I don't want to live on this planet anymore :("),
		PESSIMISTIC("It won't work :("),
		UNCERTAIN("I dunno....");

		private final String description;

		private Mood(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

		@Override
		public String toString() {
			return name() + "(" + getDescription() + ")";
		}
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		return builder.append("BiteItParticipant [\n\tgetFirstName()=").append(getFirstName())
				.append(",\n\tgetLastName()=").append(getLastName())
				.append(",\n\tgetBirthDate()=").append(getBirthDate())
				.append(",\n\tgetMood()=").append(getMood())
				.append(",\n\tgetAge()=").append(getAge())
				.append("\n]")
				.toString();
	}

}
