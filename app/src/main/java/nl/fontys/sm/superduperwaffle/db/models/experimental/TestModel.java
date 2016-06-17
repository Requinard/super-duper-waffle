package nl.fontys.sm.superduperwaffle.db.models.experimental;

import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Key;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Save;

/**
 * Created by David on 6/2/2016.
 */
public class TestModel extends Model {
    @Key
    @Save
    private String test = "kek";

    @Save
    private String plsno = "wat";



}
