package my.android.project

import android.os.Build.VERSION_CODES.JELLY_BEAN
import my.android.project.R
import my.android.project.login
import android.view.View
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.scalatest.{Matchers, FlatSpec, FeatureSpec, RobolectricSuite}
import org.robolectric.{ RuntimeEnvironment, Robolectric }
import org.robolectric.annotation.Config
import org.scalatest.{FeatureSpec, Matchers, RobolectricSuite}
import org.junit._
import org.junit.Test
import org.junit.Assert._
import android.widget.Button

@Config(manifest = "/src/main/AndroidManifest.xml")
class   ApplicationTest extends FlatSpec with Matchers with RobolectricSuite {

  @Test
  def secondActivityStartedOnClick() {
//  val activity = Robolectric.setupActivity(classOf[login])
//val b = activity.findViewById(R.id.login).asInstanceOf[Button]
//val buttonText = b.getText().toString()
	println("jey")
   // val expectedIntent = new Intent(activity, classOf[main])
   // val shadowActivity = Shadows.shadowOf(activity)
    //val actualIntent = shadowActivity.getNextStartedActivity
    //assertTrue(actualIntent.filterEquals(expectedIntent))
  }
}