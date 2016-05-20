import android.app.Activity
import android.os.Bundle
import nl.fontys.sm.superduperwaffle.R

/**
 * Created by David on 5/20/2016.
 */
class KotlinHomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
    }
}