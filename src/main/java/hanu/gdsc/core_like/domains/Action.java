package hanu.gdsc.core_like.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hanu.gdsc.core_like.exceptions.InvalidActionException;
import hanu.gdsc.core_like.json.ActionDeserializer;
import hanu.gdsc.core_like.json.ActionSerializer;
import hanu.gdsc.share.exceptions.InvalidInputException;

@JsonSerialize(using = ActionSerializer.class)
@JsonDeserialize(using = ActionDeserializer.class)
public enum Action {
    LIKE, UNLIKE, DISLIKE, UNDISLIKE;

    public static Action from(String val) throws InvalidInputException {
        switch (val) {
            case "LIKE" : 
                return Action.LIKE;
            case "UNLIKE" : 
                 return Action.UNLIKE;
            case "DISLIKE" : 
                return Action.DISLIKE;
            case "UNDISLIKE" : 
                return Action.UNDISLIKE;
            default :
                throw new InvalidInputException(
                    "Invalid action, valid " +
                        "values are: [LIKE, UNLIKE, DISLIKE, UNDISLIKE]."
                    );
        }
    }
}
