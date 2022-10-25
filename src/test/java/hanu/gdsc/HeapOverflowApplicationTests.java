package hanu.gdsc;

import hanu.gdsc.domain.core_like.models.Action;
import hanu.gdsc.domain.core_like.exceptions.InvalidActionException;
import hanu.gdsc.domain.core_like.services.reactedObject.CreateReactedObjectService;
import hanu.gdsc.domain.core_like.services.reactedObject.ReactService;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HeapOverflowApplicationTests {
	@Autowired
	ReactService reactService;
	@Autowired
	CreateReactedObjectService createReactedObjectService;

	@Test
	void contextLoads() throws InvalidActionException, NotFoundException {
		Id objectId = createReactedObjectService.create(new CreateReactedObjectService.Input(
				"TEST"
		));
		Id coderId = Id.generateRandom();
		reactService.react(new ReactService.Input(
				objectId,
				coderId,
				Action.LIKE,
				"TEST"
		));
		reactService.react(new ReactService.Input(
				objectId,
				coderId,
				Action.DISLIKE,
				"TEST"
		));
		reactService.react(new ReactService.Input(
				objectId,
				coderId,
				Action.UNDISLIKE,
				"TEST"
		));
	}

}
