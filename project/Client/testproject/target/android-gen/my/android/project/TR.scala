package my.android.project

import scala.language.implicitConversions
import android.app.{Activity,Dialog}
import android.view.{View,ViewGroup,LayoutInflater}

case class TypedResource[A](id: Int)
case class TypedLayout[A](id: Int)

object TR {
  val encode = TypedResource[android.widget.Button](R.id.encode)
  val username = TypedResource[android.widget.EditText](R.id.username)
  val create = TypedResource[android.widget.Button](R.id.create)
  val submit = TypedResource[android.widget.Button](R.id.submit)
  val progressbar = TypedResource[android.widget.ProgressBar](R.id.progressbar)
  val decode = TypedResource[android.widget.Button](R.id.decode)
  val inform = TypedResource[android.widget.TextView](R.id.inform)
  val textview2 = TypedResource[android.widget.TextView](R.id.textview2)
  val textview = TypedResource[android.widget.TextView](R.id.textview)
  val progressBar1 = TypedResource[android.widget.ProgressBar](R.id.progressBar1)
  val editTextResult = TypedResource[android.widget.EditText](R.id.editTextResult)
  val login = TypedResource[android.widget.Button](R.id.login)
  val theListView = TypedResource[android.widget.ListView](R.id.theListView)
  val button1 = TypedResource[android.widget.Button](R.id.button1)
  val password = TypedResource[android.widget.EditText](R.id.password)

  object layout {
    val main = TypedLayout[android.widget.LinearLayout](R.layout.main)
    val a_main2 = TypedLayout[android.widget.RelativeLayout](R.layout.a_main2)
    val check = TypedLayout[android.widget.LinearLayout](R.layout.check)
    val login = TypedLayout[android.widget.LinearLayout](R.layout.login)
    val a_main = TypedLayout[android.widget.LinearLayout](R.layout.a_main)
    val user = TypedLayout[android.widget.LinearLayout](R.layout.user)
  }
}

trait TypedViewHolder {
  def findViewById(id: Int): View
  def findView[A](tr: TypedResource[A]): A =
    findViewById(tr.id).asInstanceOf[A]
}

trait TypedView extends View with TypedViewHolder
trait TypedActivityHolder extends TypedViewHolder
trait TypedActivity extends Activity with TypedActivityHolder
trait TypedDialog extends Dialog with TypedViewHolder

class TypedLayoutInflater(l: LayoutInflater) {
  def inflate[A](tl: TypedLayout[A], c: ViewGroup, b: Boolean) =
    l.inflate(tl.id, c, b).asInstanceOf[A]
  def inflate[A](tl: TypedLayout[A], c: ViewGroup) =
    l.inflate(tl.id, c).asInstanceOf[A]
}

object TypedResource {
  implicit def viewToTyped(v: View) = new TypedViewHolder {
    def findViewById(id: Int) = v.findViewById(id)
  }
  implicit def activityToTyped(a: Activity) = new TypedViewHolder {
    def findViewById(id: Int) = a.findViewById(id)
  }
  implicit def dialogToTyped(d: Dialog) = new TypedViewHolder {
    def findViewById(id: Int) = d.findViewById(id)
  }
  implicit def layoutInflaterToTyped(l: LayoutInflater) =
    new TypedLayoutInflater(l)
}
