package hanu.gdsc;

import hanu.gdsc.core_like.domains.Action;
import hanu.gdsc.core_like.exceptions.InvalidActionException;
import hanu.gdsc.core_like.services.reactedObject.CreateReactedObjectService;
import hanu.gdsc.core_like.services.reactedObject.ReactService;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HanuojApplicationTests {
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
