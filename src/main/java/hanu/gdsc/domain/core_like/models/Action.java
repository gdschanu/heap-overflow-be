package hanu.gdsc.domain.core_like.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hanu.gdsc.infrastructure.core_like.json.ActionDeserializer;
import hanu.gdsc.infrastructure.core_like.json.ActionSerializer;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;

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
