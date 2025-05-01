import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pashutrack.NotificationAdapter
import com.example.pashutrack.NotificationItem
import com.example.pashutrack.R
import com.example.pashutrack.SignUpActivity

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve username from SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Guest")

        // Set welcome message
        val welcomeText: TextView = view.findViewById(R.id.WelcomeUser)
        val welcomeMessage = "<small>Welcome back</small><br><big><font color='#A40000'>$username</font></big>"
        welcomeText.text = Html.fromHtml(welcomeMessage, Html.FROM_HTML_MODE_COMPACT)

        // Set up notifications RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.notificationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.isNestedScrollingEnabled = false

        val notificationList = listOf(
            NotificationItem("Vaccination Due", "Your cow's vaccine is scheduled tomorrow."),
            NotificationItem("Market Update", "Milk prices increased in your area."),
            NotificationItem("Weather Alert", "Heavy rain expected this week.")
        )

        recyclerView.adapter = NotificationAdapter(notificationList)

        // Set up sign up button
        view.findViewById<Button>(R.id.goToSignUpButton).setOnClickListener {
            startActivity(Intent(requireActivity(), SignUpActivity::class.java))
        }
    }
}