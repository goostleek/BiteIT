package pl.jcommerce.biteit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import pl.jcommerce.biteit.BiteItParticipant.Mood;

public class BiteItParticipantTest {

	@Rule
	public final ExpectedException npe = ExpectedException.none();

	@Test
	public void shouldGetProperAge() {
		final BiteItParticipant participant = createBiteItParticipant();
		assertThat(participant.getAge(), is(35));
	}

	@Test
	public void shouldThrowNpeOnNullFirstName() {
		npe.expect(NullPointerException.class);
		npe.expectMessage(containsString("firstName"));
		BiteItParticipant.builder().build();
	}

	@Test
	public void shouldThrowNpeOnNullLastName() {
		npe.expect(NullPointerException.class);
		npe.expectMessage(containsString("lastName"));
		BiteItParticipant.builder()
			.firstName("Marcin")
			.build();
	}

	@Test
	public void shouldThrowNpeOnNullBirthDate() {
		npe.expect(NullPointerException.class);
		npe.expectMessage(containsString("birthDate"));
		BiteItParticipant.builder()
			.firstName("Marcin")
			.lastName("Kłopotek")
			.build();
	}

	@Test
	public void shouldEqualTwoParticipants() {

		final BiteItParticipant happyMarcin = createBiteItParticipant(Mood.HAPPY);
		final BiteItParticipant hisOptimisticClone = createBiteItParticipant(Mood.OPTIMISTIC);

		assertThat(happyMarcin, is(hisOptimisticClone));
		assertThat(happyMarcin.hashCode(), equalTo(hisOptimisticClone.hashCode()));
	}

	@Test
	public void shouldEqualToSelf() {
		final BiteItParticipant marcin = createBiteItParticipant();
		assertThat(marcin, is(marcin));
	}

	@Test
	public void shouldNotEqualTwoParticipants() {

		final BiteItParticipant marcin = createBiteItParticipant();

		final BiteItParticipant artur = BiteItParticipant.builder()
				.firstName("Artur")
				.lastName("Kłopotek")
				.birthDate(LocalDate.of(1985, 7, 10))
				.mood(Mood.OPTIMISTIC)
				.build();

		assertThat(marcin, is(not(artur)));
		assertThat(marcin.hashCode(), is(not(artur.hashCode())));
	}

	@Test
	public void shouldNotEqualNullParticipant() {
		final BiteItParticipant marcin = createBiteItParticipant();
		assertThat(marcin, not(equalTo(null)));
	}

	@Test
	public void shouldNotEqualNotParticipant() {
		final BiteItParticipant marcin = createBiteItParticipant();
		assertThat(marcin, not(equalTo(new Object())));
	}

	@Test
	public void shouldReturnStringRepresentation() {
		final BiteItParticipant happyMarcin = createBiteItParticipant();
		happyMarcin.setMood(Mood.HAPPY);
		assertThat(happyMarcin.toString(), stringContainsInOrder(
			Arrays.asList(
				happyMarcin.getClass().getSimpleName(),
				happyMarcin.getFirstName(),
				happyMarcin.getLastName(),
				happyMarcin.getBirthDate().toString(),
				happyMarcin.getMood().name(),
				happyMarcin.getMood().getDescription(),
				String.valueOf(happyMarcin.getAge())
				)
			)
		);
	}

	private BiteItParticipant createBiteItParticipant() {
		return createBiteItParticipant(null);
	}

	private BiteItParticipant createBiteItParticipant(Mood mood) {
		return BiteItParticipant.builder()
			.firstName("Marcin")
			.lastName("Kłopotek")
			.birthDate(LocalDate.of(1980, 8, 11))
			.mood(mood)
			.build();
	}

}
