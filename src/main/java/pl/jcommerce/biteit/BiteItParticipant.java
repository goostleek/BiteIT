package pl.jcommerce.biteit;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Getter
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

	public BiteItParticipant(String firstName, String lastName, LocalDate birthDate, Mood mood) {
		this(firstName, lastName, birthDate);
		setMood(mood);
	}

	public int getAge() {
		if (age == 0) {
			log.debug("calculating age...");
			age = getBirthDate().until(LocalDate.now()).getYears();
		} else if (log.isDebugEnabled()) {
			log.debug("age already calculated to {}", age);
		}
		return age;
	}

	public static BiteItParticipantBuilder builder() {
		return new BiteItParticipantBuilder();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getBirthDate().hashCode();
		result = prime * result + getFirstName().hashCode();
		result = prime * result + getLastName().hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BiteItParticipant)) {
			return false;
		}
		final BiteItParticipant other = (BiteItParticipant) obj;
		if (!getBirthDate().equals(other.getBirthDate())) {
			return false;
		}
		if (!getFirstName().equals(other.getFirstName())) {
			return false;
		}
		if (!getLastName().equals(other.getLastName())) {
			return false;
		}
		return true;
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

	public static class BiteItParticipantBuilder {

		private String firstName;
		private String lastName;
		private LocalDate birthDate;
		private Mood mood;

		public BiteItParticipantBuilder() {
		}

		public BiteItParticipantBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public BiteItParticipantBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public BiteItParticipantBuilder birthDate(LocalDate birthDate) {
			this.birthDate = birthDate;
			return this;
		}

		public BiteItParticipantBuilder mood(Mood mood) {
			this.mood = mood;
			return this;
		}

		public BiteItParticipant build() {
			if (mood == null) {
				return new BiteItParticipant(firstName, lastName, birthDate);
			} else {
				return new BiteItParticipant(firstName, lastName, birthDate, mood);
			}
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
